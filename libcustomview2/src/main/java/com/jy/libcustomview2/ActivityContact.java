package com.jy.libcustomview2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/*
 * created by Cherry on 2019-12-09
 **/
public class ActivityContact extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


    }



    public void onClick(View view){
        if(view.getId() == R.id.button1){
            Log.d("Test", "button1 onclick");
        }else{
            Log.d("Test", "button2 onclick");

        }
    }
}
