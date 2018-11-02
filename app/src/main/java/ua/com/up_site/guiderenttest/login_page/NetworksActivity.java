package ua.com.up_site.guiderenttest.login_page;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

import ua.com.up_site.guiderenttest.MainActivity;
import ua.com.up_site.guiderenttest.R;
import ua.com.up_site.guiderenttest.UserInfo;

public class NetworksActivity extends AppCompatActivity {
    private static final String EMAIL = "email";
    LoginButton loginButton;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networks);



        loginButton = findViewById(R.id.login_fb_button);


        loginButton.setReadPermissions(Arrays.asList(EMAIL));

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject user, GraphResponse response) {
                                        if (user != null) {

                                            UserInfo.setName(user.optString("first_name"));
                                            UserInfo.setLastName(user.optString("last_name"));
                                            UserInfo.setEmail(user.optString("email"));

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


        //вход, например с помощью элемента OnClickListener индивидуально настроенной кнопки:
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        Intent activityChangeIntent = new Intent(NetworksActivity.this, MainActivity.class);
        NetworksActivity.this.startActivity(activityChangeIntent);
    }
}
