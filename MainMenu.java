package app.com.nearbyhospital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
   }

    public void Doctor(View v)
    {
        Intent nextScreen = new Intent(MainMenu.this, DoctorLogin.class);
        startActivity(nextScreen);
    }

    public void Pepole(View v)
    {
        Intent nextScreen = new Intent(MainMenu.this, PeopleLogin.class);
        startActivity(nextScreen);

    }

    public void Admin(View v)

    {
        Intent nextScreen = new Intent(MainMenu.this, Admin.class);
        startActivity(nextScreen);

    }


}
