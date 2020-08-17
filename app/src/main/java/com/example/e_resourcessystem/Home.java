package com.example.e_resourcessystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    TextView tvName;
    RadioGroup RG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvName=findViewById(R.id.tvName);
        RG=findViewById(R.id.RG);

        getSupportActionBar().setTitle("Home");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences obj= getSharedPreferences("unm",MODE_PRIVATE);

        String name=obj.getString("EMAIL"," ");
        tvName.setText("Welcome "+ name +" , How May I Help You ? ");

        RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if (checkedId == R.id.rb1)
                {
                    Intent i =new Intent(getApplicationContext(),UploadResources.class);
                    startActivity(i);
                        //   Toast.makeText(getApplicationContext(),"UPLOADE RESOURCES",Toast.LENGTH_LONG).show();
                }
                if (checkedId == R.id.rb2)
                {
                    Intent i =new Intent(getApplicationContext(),View_Resources.class);
                    startActivity(i);
                    //Toast.makeText(getApplicationContext(),"VIEW RESOURCES",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.account :
               // Toast.makeText(this,"MY PROFILE",Toast.LENGTH_LONG).show();
                Intent intent1=new Intent(getApplicationContext(),Update_Profile.class);
                startActivity(intent1);
                return  true;

            case R.id.logout :
                SharedPreferences preferences=getSharedPreferences("unm",MODE_PRIVATE);
                SharedPreferences.Editor editor =preferences.edit();
                editor.clear();
                editor.commit();
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
               // Toast.makeText(this,"LOGOUT",Toast.LENGTH_LONG).show();
                return  true;

            case R.id.aboutus :
                Toast.makeText(this,"ABOUT US",Toast.LENGTH_LONG).show();
                return  true;

             default:
                 return super.onOptionsItemSelected(item);


        }

    }
}
