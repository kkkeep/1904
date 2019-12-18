package com.jy.kaoshi2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jy.R;
import com.jy.utils.FragmentUtils;

/*
 * created by Cherry on 2019-12-18
 **/
public class KoaShiActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kaoshi2);

        FragmentUtils.addOrShowFragment(getSupportFragmentManager(), KaoShiFragment.class, android.R.id.content,null);
    }
}
