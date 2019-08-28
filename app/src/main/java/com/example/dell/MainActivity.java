package com.example.dell;

//import android.support.v7.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    GoogleSignInOptions gso;
    GoogleSignInClient mgooglesignInClient;
    private static final int RC_SIGN_IN=2;
    Button bt,bt1;
    FirebaseDatabase f;
    DatabaseReference myRef;
    User Users = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }
        mAuth = FirebaseAuth.getInstance();
        f = FirebaseDatabase.getInstance();

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        bt=(Button) findViewById(R.id.google);
        bt1=(Button) findViewById(R.id.signup);
        mgooglesignInClient = GoogleSignIn.getClient(this,gso);
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                googleLogProcess();

            }
        });

        bt1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                create();
            }
        });

    }

    private void SignIn(){
        Intent intent = mgooglesignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
//                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
        Intent intent = new Intent(MainActivity.this,Component_Selection.class);
        startActivity(intent);}
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
//        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        myRef = f.getReference("Users");
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
                            final FirebaseUser user = mAuth.getCurrentUser();
                            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.hasChild(user.getEmail().split("@")[0])){
                                        Users.setEmailId(user.getEmail());
                                        Users.setPhone(dataSnapshot.child(user.getEmail().split("@")[0]).child("Phone").getValue().toString());
                                        Users.setPincode(dataSnapshot.child(user.getEmail().split("@")[0]).child("Pincode").getValue().toString());
                                        Users.setCity(dataSnapshot.child(user.getEmail().split("@")[0]).child("City").getValue().toString());
                                        Users.setState(dataSnapshot.child(user.getEmail().split("@")[0]).child("State").getValue().toString());
                                        Users.setName(dataSnapshot.child(user.getEmail().split("@")[0]).child("Name").getValue().toString());
                                        Users.setAddress(dataSnapshot.child(user.getEmail().split("@")[0]).child("Address").getValue().toString());
                                        Intent intent = new Intent(MainActivity.this,Component_Selection.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        Users.setName(user.getDisplayName());
                                        String phone = user.getPhoneNumber();
                                        if(phone!=null){
                                            Users.setPhone(phone);
                                        }
                                        Users.setEmailId(user.getEmail());

                                        Intent intent  = new Intent(MainActivity.this,Create.class);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG).show();
                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    protected void googleLogProcess(){
        SignIn();
    }

    protected void create(){
        Users.setGuest(true);
        Intent intent = new Intent(MainActivity.this,Component_Selection.class);
        startActivity(intent);
    }


}
