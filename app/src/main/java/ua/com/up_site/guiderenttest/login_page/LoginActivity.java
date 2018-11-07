package ua.com.up_site.guiderenttest.login_page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import java.util.Arrays;

import ua.com.up_site.guiderenttest.MainActivity;
import ua.com.up_site.guiderenttest.R;
import ua.com.up_site.guiderenttest.UserInfo;
import ua.com.up_site.guiderenttest.test.UserGoogleAccount;

public class LoginActivity extends AppCompatActivity {

    public static final int RC_SIGN_IN = 0;
    LoginButton loginButton;
    CallbackManager callbackManager;
    private static final String EMAIL = "email";

    ImageView facebookIV;
    com.google.android.gms.common.SignInButton googleIV;
    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FacebookSdk.sdkInitialize(LoginActivity.this);
        facebookIV = findViewById(R.id.facebookIcon);
        googleIV = findViewById(R.id.googleIcon);
        loginButton = findViewById(R.id.login_fb_button);



        facebookIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.callOnClick();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                UserInfo.setName("LogButton_OnClick");
                loginButton.setReadPermissions(Arrays.asList(EMAIL));

                callbackManager = CallbackManager.Factory.create();
                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                    UserInfo.setName("OnSuccess");

                        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject user, GraphResponse response) {
                                        if (user != null) {

                                            UserInfo.setName(user.optString("first_name"));
                                            UserInfo.setLastName(user.optString("last_name"));
                                            UserInfo.setEmail(user.optString("email"));
                                            UserInfo.setId(user.optString("id"));



                                        }
                                    }
                                });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,first_name,last_name,email");
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        UserInfo.setName("Cancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        UserInfo.setName(error.getMessage());
                    }
                });

                //AccessToken.getCurrentAccessToken можно загрузить вместе с SDK из кэша или из закладки приложения при холодном запуске. Проверить его действительность можно в методе onCreateActivity:
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
                if (isLoggedIn) {
                    String user_id = accessToken.getUserId();
                   UserInfo.setName(user_id);
                }

                //вход, например с помощью элемента OnClickListener индивидуально настроенной кнопки:
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));
               /* Intent activityChangeIntent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(activityChangeIntent);*/
            }
        });

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Toast.makeText(this, gso.toString(), Toast.LENGTH_SHORT).show();

       //  UserGoogleAccount.client = mGoogleSignInClient;

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
     //   GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
      //  UserGoogleAccount.account = account;
      //  updateUI(account);
    }

    void updateUI(GoogleSignInAccount account) {
        if (account != null) {
            Toast.makeText(this, account.getIdToken(), Toast.LENGTH_LONG).show();
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
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        Intent activityChangeIntent = new Intent(NetworksActivity.this, MainActivity.class);
        NetworksActivity.this.startActivity(activityChangeIntent);
    }*/
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Auth", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this, "signInResult:failed code=" + e.getStatusCode(), Toast.LENGTH_LONG).show();
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
