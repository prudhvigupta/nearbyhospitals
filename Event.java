package app.com.nearbyhospital;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import app.com.nearbyhospital.Define.Constant;
import app.com.nearbyhospital.Http.HttpGetData;

public class Event extends AppCompatActivity implements View.OnClickListener{

    TextView etaddress,etcamp,etarea,etphone;


    Button btnSend;

    String address,camp,area,Phone,Offer,Mail,Pass;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    JSONArray jarr = null;
    JSONObject json;

    String resultdata = "data";

    EventDetail objUserData;

    ArrayList<EventDetail> objarrUserData=null;

    ArrayList<EventDetail> objarrTempUserData=null;


    int count=0;

    String name=null;
    String region=null;
    String problem=null;

    String phone=null;    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        etaddress = (TextView)findViewById(R.id.etaddress);
        etcamp = (TextView)findViewById(R.id.etcamp);
        etphone = (TextView)findViewById(R.id.etphone);
        etarea = (TextView)findViewById(R.id.etarea);

        btnSend = (Button) findViewById(R.id.btnSend);
        objUserData=new EventDetail();

        count=0;

        objarrUserData=new ArrayList<EventDetail>();
        try {
            btnSend.setOnClickListener(this);

            json = HttpGetData.makeHttpRequest(Constant.TEMP_EVENT_LIST,"GET",getApplicationContext());
            ArrayList<EventDetail> objList=getJsonData(json);



            objarrTempUserData=new ArrayList<EventDetail>();
            // objarrTempUserData.add(objList);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v)
    {
        getView();

    }

    public boolean getView()
    {

        boolean bstatus=true;
       // int count=0;

        String address,camp,area,phone,Offer,Mail,Pass;
        int tempcount=0;

        // int len= objarrUserData.size();
        count++;

        for (EventDetail arr: objarrUserData)
        {
            camp=arr.getCamp();
            address=arr.getAddress();
            area=arr.getArea();
            phone=arr.getPhone();
            int len= objarrUserData.size();

            int temcunt=count-1;
            if(len==count)
            {
                count=0;
            }

            if(count==tempcount)
            {
                etaddress.setText(address);
                etcamp.setText(camp);
                etphone.setText(phone);

                etarea.setText(area);


            }
            tempcount++;

            Log.i("Record count "+count+" ,tempcount "+tempcount,name+" , "+phone+" , "+problem);

        }
        return bstatus;
    }


    public void back(View v)
    {
        Intent qr4 = new Intent(Event.this, PeopleMenu.class);
        startActivity(qr4);
        finish();
    }


    public ArrayList getJsonData(JSONObject jobj)
    {

        try {
            // Getting Array of Contacts
            Log.i("json", "" + json);
            jarr = jobj.getJSONArray(resultdata);
            objarrUserData.clear();
            Log.i("array", "" + jarr.length());

            String address,camp,area,phone,Offer,Mail,Pass;


            for (int i = 0; i < jarr.length(); i++)
            {
                // objTutorData=new TutorFetchData();
                objUserData=new EventDetail();

                JSONObject c = jarr.getJSONObject(i);

                address= c.getString("address");
                camp=c.getString("camp");
                //name=c.getString("name");
                phone= c.getString("phone");
                area= c.getString("area");
                //address=c.getString("address");

                //objUserData.setStrusername(strusername);
                objUserData.setCamp(camp);
                objUserData.setAddress(address);
                objUserData.setPhone(phone);
                objUserData.setArea(area);

                //objUserData.setAddress(address);

                objarrUserData.add(objUserData);

                //objTutorData.setName(strusername);

                Log.i("data", "" + name+" "+region+" "+phone+" "+problem);
            }
        }catch (Exception e)
        {
            e.getMessage();
        }

        return objarrUserData;
    }

}
