package com.laserkoala.htf.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.laserkoala.htf.R;
import com.laserkoala.htf.Service.LocationTrackService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void onClick(View view) {
        TextView textview = (TextView) findViewById(R.id.userNameText);

        SharedPreferences sharedPref = getBaseContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("user_id", Integer.valueOf(textview.getText().toString()));
        editor.commit();


        startService(new Intent(this, LocationTrackService.class));

        Intent intent = new Intent(getApplicationContext(), AdminUserTrackActivity.class);
        startActivity(intent);
        finish();
    }
}
