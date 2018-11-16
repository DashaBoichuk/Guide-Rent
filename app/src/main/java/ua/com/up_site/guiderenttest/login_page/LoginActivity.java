package ua.com.up_site.guiderenttest.login_page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.io.IOException;

import ua.com.up_site.guiderenttest.MainActivity;
import ua.com.up_site.guiderenttest.R;
import ua.com.up_site.guiderenttest.api.APIWorker;
import ua.com.up_site.guiderenttest.test.UserGoogleAccount;

public class LoginActivity extends AppCompatActivity {

    public static final int RC_SIGN_IN = 0;
    private static final String TAG = "Login";

    boolean updateErrorFlag = true;
    ImageView facebookIV;
    com.google.android.gms.common.SignInButton googleIV;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        facebookIV = findViewById(R.id.facebookIcon);
        googleIV = findViewById(R.id.googleIcon);

        facebookIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(LoginActivity.this, NetworksActivity.class);
                LoginActivity.this.startActivity(activityChangeIntent);
            }
        });

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        UserGoogleAccount.client = mGoogleSignInClient;


        googleIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(activityChangeIntent);
            }
        });

        Button registrationButton = findViewById(R.id.registration_button);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(LoginActivity.this, NetworksActivity.class);
                LoginActivity.this.startActivity(activityChangeIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                    if (account != null) {
                    UserGoogleAccount.account = account;
                    updateErrorFlag = APIWorker.validateGoogleToken(account.getIdToken());
                    Log.i(TAG, "updateErrorFlag = " + updateErrorFlag);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!updateErrorFlag) {
                                updateUI(UserGoogleAccount.account);
                            }
                            else {
                                signIn();
                            }
                        }
                    }); }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //Этот метод изменяет UI после успешного входа с помощью Google
    void updateUI(GoogleSignInAccount account) {
        if (account != null) {
          //  Toast.makeText(this, account.getIdToken(), Toast.LENGTH_LONG).show();
            Intent activityChangeIntent = new Intent(LoginActivity.this, MainActivity.class);
            LoginActivity.this.startActivity(activityChangeIntent);
        }
        // else Toast.makeText(this, "account is null!", Toast.LENGTH_LONG).show();
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                        UserGoogleAccount.account = account;
                        updateErrorFlag = APIWorker.validateGoogleToken(account.getIdToken());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!updateErrorFlag) {
                                    updateUI(UserGoogleAccount.account);
                                }
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code =" + e.getStatusCode());
            Toast.makeText(this, "signInResult:failed code =" + e.getStatusCode(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
            updateUI(null);
        }
        // //Button fbButton = findViewById(R.id.facebookIcon);
        // fbButton.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         Intent activityChangeIntent = new Intent(LoginActivity.this, NetworksActivity.class);
        //         LoginActivity.this.startActivity(activityChangeIntent);
        //     }
        // });

    }


}
