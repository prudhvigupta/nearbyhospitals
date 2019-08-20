package app.com.nearbyhospital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PeopleMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_menu);
    }

    public void Deases(View v)
    {
        Intent nextScreen = new Intent(PeopleMenu.this, DieasesDetailMenu.class);
        startActivity(nextScreen);

    }

    public void Event(View v)
    {
        Intent nextScreen = new Intent(PeopleMenu.this, Event.class);
        startActivity(nextScreen);

    }
    public void Queries(View v)
    {
        Intent nextScreen = new Intent(PeopleMenu.this, Queries.class);
        startActivity(nextScreen);

    }




}
