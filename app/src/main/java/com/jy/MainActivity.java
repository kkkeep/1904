package com.jy;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jy.base.FragmentA;
import com.jy.main.video.VideoFragment;
import com.jy.utils.FragmentUtils;
import com.jy.utils.Logger;

/*
 * created by Cherry on 2019-11-25
 **/
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private String name  = "张三";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        FragmentUtils.addOrShowFragment(getSupportFragmentManager(), FragmentA.class, R.id.main_fragment_container, null);

    }
}
