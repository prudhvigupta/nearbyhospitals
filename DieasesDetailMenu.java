package app.com.nearbyhospital;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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


public class DieasesDetailMenu extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String Name, UName, Address, Phone, Region, diseas, symptomps, Add,clinic;

    //String Regions[]={};

    ArrayList Regions;

    String semail, spass;

    String strRegion;

    JSONArray jarr = null;
    JSONObject json;

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    String resultdata = "data";
    DiseasDetail objDiseas=null;

    ArrayList<DiseasDetail> objarrDiseas=null;

    int id=0;

    Spinner etregion;

    ArrayAdapter<CharSequence> adapter_et_region;

    TextView name,diseass,symptompss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dieases_detail_menu);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        objarrDiseas=new ArrayList<DiseasDetail>();

        etregion = (Spinner) findViewById(R.id.etregion);

        name= (TextView) findViewById(R.id.name);
        diseass= (TextView) findViewById(R.id.diseas);
        symptompss= (TextView) findViewById(R.id.symptomps);

       // etregion=new Spinner();

        Regions=new ArrayList();

        DiseasList();

    }


    public void DiseasList()
    {
        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
        // params1.add(new BasicNameValuePair("driver_name", strName));
        // params1.add(new BasicNameValuePair("phone", strMobile));

        json = HttpCommunication.makeHttpRequest(Constant.TEMP_DISEAS_UPDATE_LIST, "GET", params1,getApplicationContext());
        objarrDiseas.clear();
        Regions.clear();

        try {

            // Log.i("Jsonconvert",getPostDataString(json));
            if (json != null) {
                jarr = json.getJSONArray(resultdata);

                Log.i("array", "" + jarr.length());

                for (int i = 0; i < jarr.length(); i++) {
                    JSONObject c = jarr.getJSONObject(i);
                    //       String name,driver,phone,sourcetime,desttime,sourceplace,destplace;
                    id=c.getInt("id");
                    Name= c.getString("name");
                    diseas= c.getString("diseas");
                    symptomps= c.getString("symptomps");
                    Phone=c.getString("phone");
                    Region=c.getString("region");

                    //Regions[i]=Region;

                    Regions.add(Region);

                    Log.i("DiseasDetail",id+","+Name+","+diseas+","+symptomps+","+Phone+","+Region);

                    objDiseas=new DiseasDetail();

                    objDiseas.setName(Name);
                    objDiseas.setDiseas(diseas);
                    objDiseas.setRegion(Region);
                    objDiseas.setSymptomps(symptomps);
                    objDiseas.setPhone(Phone);
                    objDiseas.setId(id);

                    objarrDiseas.add(objDiseas);
                }

            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, Regions);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            etregion.setAdapter(dataAdapter);

           // adapter_et_region = ArrayAdapter.createFromResource(this,Regions,android.R.layout.simple_spinner_item);
          //  adapter_et_region.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

           // etregion.setAdapter(adapter_et_region);
            etregion.setOnItemSelectedListener(this);



        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Erorr"+e.getMessage().toString(),Toast.LENGTH_LONG).show();
            e.getMessage();
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()) {

            case R.id.etregion:

                strRegion = etregion.getSelectedItem().toString();

                Toast.makeText(getApplicationContext(), strRegion,Toast.LENGTH_LONG).show();
                getDetails(strRegion);

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean getDetails(String regions)
    {

        boolean bstatus=true;
        String Name, UName, Address, Phone, Region, diseas, symptomps, Add,clinic;

        int count=0;

        for (DiseasDetail arr : objarrDiseas)
        {

            Name=arr.getName();
            diseas=arr.getDiseas();
            symptomps=arr.getSymptomps();
            Phone=arr.getPhone();
            Region=arr.getRegion().trim();

            if(Region==regions || Region.equals(regions) ) {
                name.setText(Name);
                diseass.setText(diseas);
                symptompss.setText(symptomps);
            }

            count++;
            Log.i("Record "+count,symptomps+" , "+Phone+" , "+Region);

        }
        return bstatus;
    }


}
