package com.example.mymovies.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymovies.MainActivity;
import com.example.mymovies.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.email_id)
    EditText email;
    @BindView(R.id.password_id)
    EditText password;
    @BindView(R.id.btn_loging_id)
    Button login;

    private static final String EMAIL = "email" ;
    private static final String PASSWORD="password";
    private static final String LOG = "FireBase";
    DocumentReference mDocRef = FirebaseFirestore.getInstance().document("sampleData/users");
   // private FirebaseFirestore db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _email = email.getText().toString();
                String _password = password.getText().toString();
                if(_email.isEmpty() || _password.isEmpty()){
                    return;
                }
                Map<String , Object> dataToSave = new HashMap<>();
                dataToSave.put(EMAIL , _email);
                dataToSave.put(PASSWORD , _password);
                mDocRef.set(dataToSave)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(LOG , "Success to save in fireStore ");
                                Toast.makeText(getApplicationContext() , "Success to save in fireStore" , Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(LOG , "fail to save in fireStore ");
                        Toast.makeText(getApplicationContext() , "fail to save in fireStore " , Toast.LENGTH_LONG).show();
                    }
                });
//                db = FirebaseFirestore.getInstance();
//                db.collection("users")
//                        .add(dataToSave)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                Toast.makeText(getApplicationContext() , "Success to save in fireStore" , Toast.LENGTH_LONG).show();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(getApplicationContext() , e.getLocalizedMessage() + "fail to save in fireStore " , Toast.LENGTH_LONG).show();
//                            }
//                        });
                Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                startActivity(intent);
            } // end onClick()
        });
    } // end onCreate()
} // end class
