package app.com.nearbyhospital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Admin extends AppCompatActivity {
    Button bt1,bt2;
    EditText ed1,ed2;
    String n,p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin);
        bt1=(Button)findViewById(R.id.btn1);
        bt2=(Button)findViewById(R.id.back);
        ed1=(EditText) findViewById(R.id.na);
        ed2=(EditText) findViewById(R.id.na1);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n=ed1.getText().toString();
                p=ed2.getText().toString();
                if(n.contentEquals("admin")&&p.contentEquals("1234"))
                {
                    Intent u1 = new Intent(Admin.this, AdminMenu.class);
                    startActivity(u1);
                    finish();
                }

            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent u2 = new Intent(Admin.this, MainMenu.class);
                startActivity(u2);
                finish();
            }
        });
    }
}

