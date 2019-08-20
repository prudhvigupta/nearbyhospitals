package app.com.nearbyhospital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
    }

    public void Queries(View v)
    {
        Intent nextScreen = new Intent(AdminMenu.this, MessageMenu.class);
        startActivity(nextScreen);

    }

    public void Event(View v)

    {
        Intent nextScreen = new Intent(AdminMenu.this, EventMenu.class);
        startActivity(nextScreen);

    }

    public void Diseas(View v)

    {
        Intent nextScreen = new Intent(AdminMenu.this, DieasesDetailMenu.class);
        startActivity(nextScreen);

    }

}
