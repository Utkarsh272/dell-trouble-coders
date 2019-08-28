package com.example.dell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Create extends AppCompatActivity {
    TextView name,phone,Address,state,city,country,pincode,emailId;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        name = findViewById(R.id.name);
        Address = findViewById(R.id.add);
        phone = findViewById(R.id.phone);
        state = findViewById(R.id.state);
        city = findViewById(R.id.city);
        pincode = findViewById(R.id.pincode);
        emailId = findViewById(R.id.email);
        submit = findViewById(R.id.submit);
        if(User.getName()!=null){
            name.setText(User.getName());
            name.setEnabled(false);
        }

        if(User.getPhone()!=null){
            phone.setText(User.getPhone());
            phone.setEnabled(false);
        }
        if(User.getEmailId()!=null){
            emailId.setText(User.getEmailId());
            emailId.setEnabled(false);
        }

        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                boolean a = verify();
                if(a){
                    User.setAddress(Address.getText().toString());
                    User.setPhone(phone.getText().toString());
                    User.setName(name.getText().toString());
                    User.setState(state.getText().toString());
                    User.setCity(city.getText().toString());
                    User.setPincode(pincode.getText().toString());
                    User.setEmailId(emailId.getText().toString());
                    FirebaseDatabase f = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = f.getReference("Users");
                    HashMap<String,String> map = new HashMap<>();
                    map.put("Phone",User.getPhone());
                    map.put("Name",User.getName());
                    map.put("State",User.getState());
                    map.put("City",User.getCity());
                    map.put("Pincode",User.getPincode());
                    map.put("Address",User.getAddress());
                    Map<String,Object> tomap = new HashMap<>();
                    tomap.put("/"+User.getEmailId(),map);
                    myRef.updateChildren(tomap);
                    Intent intent  = new Intent(Create.this,Main2Activity.class);
                    startActivity(intent);
                    //start a new Activity
                }
            }
        });


    }

    private boolean verify(){
        if(name.getText().length()==0 || Address.getText().length()==0 || phone.getText().length()==0 || state.getText().length()==0 || city.getText().length()==0 || pincode.getText().length()==0 || emailId.getText().length()==0 || submit.getText().length()==0){
            //JOptionPane.s
            Toast.makeText(Create.this,"Some Entries are empty",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(phone.getText().length()<10 || phone.getText().length()>10){
            Toast.makeText(Create.this,"Phone Number is wrong "+phone.getText(),Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
