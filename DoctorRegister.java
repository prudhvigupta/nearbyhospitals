package app.com.nearbyhospital;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
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

public class DoctorRegister extends AppCompatActivity implements View.OnClickListener{



    EditText etname;

    EditText  etusername;

    EditText etpass;

    EditText etclinicname;

    EditText etadd;

    EditText etphone;

    EditText etregion;

    EditText etaddress;

    // EditText etname, etusername, etphone, etaddress, etpass, etemail;
    Button btnSend;

    String Name, UName, Address, Phone, Region, Mail, Pass, Add,clinic;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    JSONArray jarr = null;
    JSONObject json;

    TelephonyManager tel;
    TextView imei;

    String imeis, androidid;
    public static boolean isMultiSimEnabled = false;
    public static List<SubscriptionInfo> subInfoList;
    public static ArrayList<String> numbers;
    private SubscriptionManager subscriptionManager;
    static final Integer PHONESTATS = 0x1;
   // private final String TAG = PeopleRegister.class.getSimpleName();
    TempStorage objTempStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        objTempStorage=new TempStorage(getApplicationContext());

        etname = (EditText) findViewById(R.id.etname);
        etusername = (EditText) findViewById(R.id.etusername);
        etadd=(EditText) findViewById(R.id.etadd);
        etclinicname=(EditText) findViewById(R.id.etclinicname);
        etphone = (EditText) findViewById(R.id.etphone);
        etaddress = (EditText) findViewById(R.id.etaddress);
        etregion = (EditText) findViewById(R.id.etregion);
        etpass = (EditText) findViewById(R.id.etpass);

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
        Name=etname.getText().toString().trim();
        UName=etusername.getText().toString().trim();

        clinic=etclinicname.getText().toString().trim();

        Address=etaddress.getText().toString().trim();
        Phone=etphone.getText().toString().trim();

        Pass=etpass.getText().toString().trim();

        Mail=etadd.getText().toString().trim();

        Region=etregion.getText().toString().trim();


        // Imei=imei.getText().toString().trim();

        // String Type="Atm";
        /// Log.i("Data entry validation","success") ;

        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
        params1.add(new BasicNameValuePair("name", Name));
        params1.add(new BasicNameValuePair("username", UName));
        params1.add(new BasicNameValuePair("address", Address));
        params1.add(new BasicNameValuePair("phone", Phone));
        params1.add(new BasicNameValuePair("password", Pass));
        params1.add(new BasicNameValuePair("mail", Mail));
        params1.add(new BasicNameValuePair("region", Region));
        params1.add(new BasicNameValuePair("clinic", clinic));


        json = HttpCommunication.makeHttpRequest(Constant.TEMP_DOCTOR_REGISTER, "GET", params1,getApplicationContext());
        try {
            //  Log.i("Jsonconvert",getPostDataString(json));
            if (json != null) {

                String message = json.getString(TAG_MESSAGE);
                int success = json.getInt(TAG_SUCCESS);
                if(success==1)
                {
                    objTempStorage.setAllClear();
                    objTempStorage.SetPhone(Phone);


                    Toast.makeText(getApplicationContext(), "Data  Registered Succesfull", Toast.LENGTH_LONG).show();
                    Intent i1 = new Intent(DoctorRegister.this, DoctorLogin.class);
                    startActivity(i1);
                    finish();


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
