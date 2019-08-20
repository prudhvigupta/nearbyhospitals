package app.com.nearbyhospital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DoctorMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_menu);
    }

    public void Deases(View v)
    {
        Intent nextScreen = new Intent(DoctorMenu.this, DieasesMenu.class);
        startActivity(nextScreen);

    }

    public void Suggection(View v)
    {
        Intent nextScreen = new Intent(DoctorMenu.this, SuggectionMenu.class);
        startActivity(nextScreen);

    }
    public void Message(View v)
    {
        Intent nextScreen = new Intent(DoctorMenu.this, MessageMenu.class);
        startActivity(nextScreen);

    }

}
