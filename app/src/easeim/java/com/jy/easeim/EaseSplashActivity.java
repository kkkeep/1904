package com.jy.easeim;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hyphenate.chat.EMClient;
import com.jy.R;

/*
 * created by Cherry on 2019-12-04
 **/
public class EaseSplashActivity extends AppCompatActivity {






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.easeim_activity_spalsh);




        new Thread(){
            @Override
            public void run() {
                //

                if(EMClient.getInstance().isLoggedInBefore()){
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    startActivity(new Intent(EaseSplashActivity.this,EaseHomeActivity.class));
                }else{
                    startActivity(new Intent(EaseSplashActivity.this,LoginRegisterActivity.class));
                }



                finish();

            }
        }.start();
    }



}
