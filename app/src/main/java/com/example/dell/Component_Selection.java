package com.example.dell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.*;


public class Component_Selection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component__selection);
        this.simpleAdapterListView();
    }

    private void simpleAdapterListView()
    {
//        setTitle("dev2qa.com - SimpleAdapter List View Example");

        String[] titleArr = { "LAPTOP","MONITOR", "HDD", "CPU", "RAM","KEYBOARD","MOUSE","GRAPHICS CARD"};
//        String[] descArr = { "Jerry", "Male", "43", "Singapore", "webmaster@dev2qa.com" };

        ArrayList<Map<String,String>> itemDataList = new ArrayList<Map<String,String>>();;

        int titleLen = titleArr.length;
        for(int i =0; i < 7; i++) {
            Map<String,String> listItemMap = new HashMap<String,String>();
            listItemMap.put("title", titleArr[i]);
            listItemMap.put("description", "");
            itemDataList.add(listItemMap);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,itemDataList,android.R.layout.simple_list_item_2,
                new String[]{"title","description"},new int[]{android.R.id.text1,android.R.id.text2});

        ListView listView = (ListView)findViewById(R.id.listViewExample);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Object clickItemObj = adapterView.getAdapter().getItem(index);
                input_variables.setComponents(clickItemObj.toString().split("=")[2].substring(0,clickItemObj.toString().split("=")[2].length()-1));
                String str = input_variables.getComponents();
                if(str.equals("LAPTOP")){
                    Priority.min=21800;
                    main.res=0;
                    Priority.max=58100;

                }
                else if(str.equals("MONITOR")){
                    Priority.min=3400;
                    main.res=4;
                    Priority.max=10000;
                }
                else if(str.equals("HDD")){
                    Priority.min=3000;
                    main.res=2;
                    Priority.max=6000;
                }
                else if(str.equals("RAM")){
                    Priority.min=1500;
                    main.res=3;
                    Priority.max=4000;
                }
                else if(str.equals("CPU")){
                    Priority.min=10000;
                    main.res=1;
                    Priority.max=19700;
                }
                else if(str.equals("KEYBOARD")){
                    Priority.min=500;
                    main.res=5;
                    Priority.max=2000;
                }
                else if(str.equals("MOUSE")){
                    Priority.min=200;
                    main.res=6;
                    Priority.max=1500;
                }
                else if(str.equals("GRAPHICS CARD")){
                    Priority.min=3200;
                    main.res=7;
                    Priority.max=14900;
                }
//                Toast.makeText(Component_Selection.this, "You clicked " + clickItemObj.toString().split("=")[2].substring(0,clickItemObj.toString().split("=")[2].length()-1), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Component_Selection.this,Priority.class);
                startActivity(intent);
            }
        });

    }
}
