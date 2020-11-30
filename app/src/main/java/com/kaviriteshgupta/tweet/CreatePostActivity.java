package com.kaviriteshgupta.tweet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreatePostActivity extends AppCompatActivity {

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    EditText editTextPost;
    Button buttonPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        editTextPost = findViewById(R.id.post_edit_text);
        buttonPost = findViewById(R.id.post_button);


        editTextPost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                String et_string = editTextPost.getText().toString();
                if (et_string.equals("")){
                    buttonPost.setEnabled(false);
                } else if (charSequence.toString().trim().length() == 0){
                    buttonPost.setEnabled(false);
                } else {
                    buttonPost.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String et_string = editTextPost.getText().toString();
                if (et_string.equals("")){
                    buttonPost.setEnabled(false);
                } else if (charSequence.toString().trim().length() == 0){
                    buttonPost.setEnabled(false);
                } else {
                    buttonPost.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String et_string = editTextPost.getText().toString();
                if (et_string.equals("")){
                    buttonPost.setEnabled(false);
                } else {
                    buttonPost.setEnabled(true);
                }
            }
        });
    }

    public void post(View view) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Uri pic = user.getPhotoUrl();
        String name = user.getDisplayName();
        String body = editTextPost.getText().toString();

        String picString = pic.toString();
        String uID = user.getUid();

        PostModel post = new PostModel(name,picString,body,uID);
        firestore.collection("posts")
                .add(post)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(CreatePostActivity.this, "Posted!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreatePostActivity.this, "An Error Occurred!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }


}