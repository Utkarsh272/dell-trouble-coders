package com.example.dell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;

public class Priority extends AppCompatActivity {
    SeekBar s1,s2,s3;
    TextView t1,t2,t3;
    Button bt;
    static int min=0,max=0;
//    String comp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority);
        s1 = (SeekBar) findViewById(R.id.seekbar1);
//        s2 = (SeekBar) findViewById(R.id.seekbar2);
//        s3 = (SeekBar) findViewById(R.id.seekbar3);
        t1 = (TextView) findViewById(R.id.textView1);
        t2 = (TextView) findViewById(R.id.note);
        t2.setText("Note: Minimum value possible is "+min);
//        t3 = (TextView) findViewById(R.id.textView3);
        s1.setMax(max);
//        s2.setMax(10);
//        s3.setMax(10);
//        s1.setMin(0);
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int prog=0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                prog=i+min;
                input_variables.setCost(prog);
                t1.setText(prog+"");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                t1.setText(prog+"");
                input_variables.setCost(prog);
//                Toast.makeText(Priority.this,prog+"",Toast.LENGTH_LONG).show();

            }
        });
        bt = (Button) findViewById(R.id.but);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Priority.this,main.class);
                startActivity(intent);
            }
        });




    }


}
