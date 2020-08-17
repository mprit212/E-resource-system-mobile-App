package com.example.e_resourcessystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class splashscreenActivity extends AppCompatActivity {

    private  int sleeptime=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);
        getSupportActionBar().hide();

        LOGOLanuch lg=new LOGOLanuch();
        lg.start();


    }
    private  class  LOGOLanuch extends  Thread
    {
        public  void  run()
        {

            try
            {
                sleep(1000 * sleeptime);

            }

            catch (InterruptedException e)
            {

                e.printStackTrace();
            }

            Intent i=new Intent(getApplicationContext(),Login.class);
            startActivity(i);
            splashscreenActivity.this.finish();
        }
    }
}

