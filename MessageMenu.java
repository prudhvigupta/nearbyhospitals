package app.com.nearbyhospital;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.com.nearbyhospital.Define.Constant;
import app.com.nearbyhospital.Http.HttpGetData;


public class MessageMenu extends AppCompatActivity implements View.OnClickListener{

    TextView etname,etregion,etproblem,etphone;


    Button btnSend;

    String Name,UName,Address,Phone,Offer,Mail,Pass;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    JSONArray jarr = null;
    JSONObject json;

    String resultdata = "data";

    FetchMessage objUserData;

    ArrayList<FetchMessage> objarrUserData=null;

    ArrayList<FetchMessage> objarrTempUserData=null;


    int count=0;

    String name=null;
    String region=null;
    String problem=null;

    String phone=null;
    //String pass=null;
    //String address=null;    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_menu);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        etname = (TextView)findViewById(R.id.etname);
        etregion = (TextView)findViewById(R.id.etregion);
        etphone = (TextView)findViewById(R.id.etphone);
        etproblem = (TextView)findViewById(R.id.etproblem);

        btnSend = (Button) findViewById(R.id.btnSend);
        objUserData=new FetchMessage();

        objarrUserData=new ArrayList<FetchMessage>();
        try {
            btnSend.setOnClickListener(this);

            json = HttpGetData.makeHttpRequest(Constant.TEMP_MESSAGE,"GET",getApplicationContext());
            ArrayList<FetchMessage> objList=getJsonData(json);

            objarrTempUserData=new ArrayList<FetchMessage>();
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
      //  int count=0;

        String name=null;
        String region=null;
        String problem=null;

        String phone=null;
        int tempcount=1;

       // int len= objarrUserData.size();
        count++;
        for (FetchMessage arr: objarrUserData)
        {
            name=arr.getName();
            region=arr.getRegion().trim();
            problem=arr.getRegion();
            phone=arr.getPhone();
            int len= objarrUserData.size();

            int temcunt=count-1;
            if(len==count)
            {
                count=0;
            }

            if(count==tempcount)
            {
                etname.setText(name);
                etregion.setText(region);
                etphone.setText(phone);

                etproblem.setText(problem);


            }
            tempcount++;

            Log.i("Record "+count,name+" , "+phone+" , "+problem);

        }
        return bstatus;
    }


    public void back(View v)
    {
        Intent qr4 = new Intent(MessageMenu.this, DoctorMenu.class);
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

            String name=null;
            String region=null;
            String problem=null;

            String phone=null;


            for (int i = 0; i < jarr.length(); i++)
            {
                // objTutorData=new TutorFetchData();
                objUserData=new FetchMessage();

                JSONObject c = jarr.getJSONObject(i);

                name= c.getString("name");
                region=c.getString("region");
                //name=c.getString("name");
                phone= c.getString("phone");
                problem= c.getString("problem");
                //address=c.getString("address");

                //objUserData.setStrusername(strusername);
                objUserData.setName(name);
                objUserData.setRegion(region);
                objUserData.setPhone(phone);
                objUserData.setProblem(problem);

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
