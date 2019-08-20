package app.com.nearbyhospital;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.com.nearbyhospital.Define.Constant;
import app.com.nearbyhospital.Http.HttpCommunication;

public class EventMenu extends AppCompatActivity implements View.OnClickListener{
    EditText etcamp,etarea,etphone,etaddress;

    Button btnSend;

    String Name,UName,Address,Phone,Offer,Mail,Pass;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    JSONArray jarr = null;
    JSONObject json;

    String resultdata = "data";



    int count=0;

    String camp=null;
    String area=null;
   // String problem=null;

    String phone=null;

     String address=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_menu);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

      //  objTempStorage=new TempStorage(getApplicationContext());

        etcamp = (EditText) findViewById(R.id.etcamp);
        etarea=(EditText) findViewById(R.id.etarea);
        etphone = (EditText) findViewById(R.id.etphone);

        etaddress=(EditText) findViewById(R.id.etaddress);
       // etphone = (EditText) findViewById(R.id.etregion);


        btnSend = (Button) findViewById(R.id.btnSend);
        try {
            btnSend.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {
        getData();
    }


    public void getData()
    {
        camp=etcamp.getText().toString().trim();

        area=etarea.getText().toString().trim();

        phone=etphone.getText().toString().trim();

        // Pass=etpass.getText().toString().trim();

        // Mail=etadd.getText().toString().trim();

        address=etaddress.getText().toString().trim();

       // Imei=imei.getText().toString().trim();

        // String Type="Atm";
        /// Log.i("Data entry validation","success") ;

        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
        params1.add(new BasicNameValuePair("camp", camp));
        params1.add(new BasicNameValuePair("area", area));
        params1.add(new BasicNameValuePair("phone", phone));
        params1.add(new BasicNameValuePair("address", address));

        json = HttpCommunication.makeHttpRequest(Constant.TEMP_EVENT, "GET", params1,getApplicationContext());
        try {
            //  Log.i("Jsonconvert",getPostDataString(json));
            if (json != null) {

                String message = json.getString(TAG_MESSAGE);
                int success = json.getInt(TAG_SUCCESS);
                if(success==1)
                {
                    Toast.makeText(getApplicationContext(), "Data  Registered Succesfull", Toast.LENGTH_LONG).show();
                }
                if(success==0)
                {
                    Toast.makeText(getApplicationContext(), "Data  not Register", Toast.LENGTH_LONG).show();
                }
                Log.i("success status : " + success, message);
            }
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Erorr"+e.getMessage().toString(),
                    Toast.LENGTH_LONG).show();

            e.getMessage();
        }

    }

}

