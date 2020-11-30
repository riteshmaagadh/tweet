package com.kaviriteshgupta.tweet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginRegisterActivity extends AppCompatActivity {

    private static final String TAG = "LoginRegisterActivity";
    public static final int AUTH_UI_REQUEST_CODE = 6712;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(LoginRegisterActivity.this,MainActivity.class));
            this.finish();
        }
    }

    public void handleLoginSignUp(View view) {

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build()
        );

        Intent intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTosAndPrivacyPolicyUrls("www.riteshmaagadh.blogspot.com","kaviriteshgupta.blogspot.com")
                .setLogo(R.drawable.tweetur_logo)
                .setIsSmartLockEnabled(false)
                .setAlwaysShowSignInMethodScreen(true)
                .build();

        startActivityForResult(intent,AUTH_UI_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTH_UI_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                // We have a new user
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user.getMetadata().getCreationTimestamp() == user.getMetadata().getLastSignInTimestamp()){
                    Toast.makeText(this, "Welcome new user", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Welcome back again", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(LoginRegisterActivity.this,MainActivity.class);
                startActivity(intent);
                this.finish();
            }else {
                IdpResponse idpResponse = IdpResponse.fromResultIntent(data);
                if (idpResponse == null){
                    Toast.makeText(this, "Sign in cancelled", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "onActivityResult: " + idpResponse.getError());
                }
            }
        }
    }


}