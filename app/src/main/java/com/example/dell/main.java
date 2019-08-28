package com.example.dell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class main extends AppCompatActivity {
    static int res=0;
    static String str="";
    static int totalcost=0;
    static int max=0;
    static int rem=0;
    String url="";
    TextView t1;
    static ArrayList<String> al;
    ArrayList<result> rse;
    ArrayList<String> cos;
    Button bt,bt1;
    SwipeRefreshLayout sp;
//    static Intent inten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        t1 = (TextView) findViewById(R.id.cost);
        bt = (Button) findViewById(R.id.bt);
        bt1 = (Button) findViewById(R.id.ref);
        // Instantiate the RequestQueue.
        al = new ArrayList<String>();
        rse = new ArrayList<>();
        cos = new ArrayList<>();


//        if(input_variables.getComponents().equals("LAPTOP")){
//            int x1=0,x2=0,x3=0,x4=0,x5=0,x6=0,x7=0;
////            int rem=0;
//            x1=(int)(input_variables.getCost()*0.379);
//            x2=(int)(input_variables.getCost()*0.110);
//            x3=(int)(input_variables.getCost()*0.0664);
//            x4=(int)(input_variables.getCost()*0.166);
//            x5=(int)(input_variables.getCost()*0.029);
//            x6=(int)(input_variables.getCost()*0.020);
//            x7=(int)(input_variables.getCost()*0.227);
////            for(int i=1;i<=7;i++){
//
//                url ="http://10.6.0.55:5000/"+x1+"/";
//                url += 1;
//            res1(url);
//
////                x2+=rem;
//            String url1 ="http://10.6.0.55:5000/"+x2+"/";
//            url1 += 2;
//            res1(url1);
//
////            x3+=rem;
//            url ="http://10.6.0.55:5000/"+x3+"/";
//            url += 3;
//            res1(url);
//
////            x4+=rem;
//            url ="http://10.6.0.55:5000/"+x4+"/";
//            url += 4;
//            res1(url);
//
////            x5+=rem;
//            url ="http://10.6.0.55:5000/"+x5+"/";
//            url += 5;
//            res1(url);
//
////            x6+=rem;
//            url ="http://10.6.0.55:5000/"+x6+"/";
//            url += 6;
//            res1(url);

//            x7+=rem;
//            url ="http://10.6.0.55:5000/"+input_variables.getCost()+"/";
//            url += 7;
//            res1(url);



//        }
//        else{
            url ="http://10.6.0.55:5000/"+input_variables.getCost()+"/";
            url += res;
            res1(url);

//        }
// Request a string response from the provided URL.

        /*RequestQueue rq = Volley.newRequestQueue(this);*/

       /* String URL = "http://10.6.0.55:5000/4000/1";
        JsonObjectRequest jor = new JsonObjectRequest(
                Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Rest response",response.toString());
                Toast.makeText(main.this, "Worked!!!", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest response",error.toString());
                    }
                }
        );

        rq.add(jor);*/
//       try{
//       Thread.sleep(5000);}
//       catch(Exception e){
//
//       }
        this.simpleAdapterListView();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main.this,Payment.class);
                startActivity(intent);
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main.this,main.class);
                startActivity(intent);
            }
        });

    }
    public void res1(String url){
//        str="";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        main.str = response.toString();

//                        Toast.makeText(main.this, str+" YAY", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), "Nay", Toast.LENGTH_SHORT).show();
            }
        });
        al.add(main.str+"");
//        String s1 = main.str+"";
//        Toast.makeText(this,s1.split(" ").length+" ",Toast.LENGTH_LONG).show();

//        result rs = new result();
//        String[] s1 = str.split(" ");
////        for(int i=1;i<s1.length;i++){
////            String[] s2 = s1[1].split(" ");
//            rs.setComponents(s1[3]);
//        al.add(rs.getComponents());
////        }
////        s2 = s1[2].split(" ");
//        rs.setPriority1(s1[5]);
////        s2 = s1[3].split(" ");
//        rs.setCost(Integer.parseInt(s1[7]));
////        s2 = s1[4].split(" ");
//        rs.setSpr(Integer.parseInt(s1[11]));
////        s2 = s1[5].split(" ");
//        rs.setCpr(Integer.parseInt(s1[15]));
//        rse.add(rs);
//        cos.add(Integer.toString(rs.getCost()));

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


    private void simpleAdapterListView()
    {
//        setTitle("dev2qa.com - SimpleAdapter List View Example");

//        String[] titleArr = { "LAPTOP","MONITOR", "HDD", "CPU", "RAM","KEYBOARD","MOUSE","GRAPHICS CARD"};
//        String[] descArr = { "Jerry", "Male", "43", "Singapore", "webmaster@dev2qa.com" };

        ArrayList<Map<String,String>> itemDataList = new ArrayList<Map<String,String>>();

//        int titleLen = al.size();
        Toast.makeText(this,al.size()+" ",Toast.LENGTH_SHORT).show();
        for(int i =0; i < al.size(); i++) {
            Map<String,String> listItemMap = new HashMap<String,String>();
            listItemMap.put("title", al.get(i)+"");
            listItemMap.put("description", "");
            itemDataList.add(listItemMap);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,itemDataList,android.R.layout.simple_list_item_2,
                new String[]{"title","description"},new int[]{android.R.id.text1,android.R.id.text2});

        ListView listView = (ListView)findViewById(R.id.listViewExample1);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Object clickItemObj = adapterView.getAdapter().getItem(index);
//                input_variables.setComponents(clickItemObj.toString().split("=")[2].substring(0,clickItemObj.toString().split("=")[2].length()-1));
//                String str = input_variables.getComponents();
//                    Cart.rs=rse.get(index);
//                Toast.makeText(Component_Selection.this, "You clicked " + clickItemObj.toString().split("=")[2].substring(0,clickItemObj.toString().split("=")[2].length()-1), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(main.this,Cart.class);
                startActivity(intent);
            }
        });

    }
}
