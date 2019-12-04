package com.jy.easeim;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jy.R;
import com.jy.easeim.login.LoginFragment;
import com.jy.utils.FragmentUtils;

/*
 * created by Cherry on 2019-12-04
 **/
public class LoginRegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lgoin_register);


        FragmentUtils.addOrShowFragment(getSupportFragmentManager(), LoginFragment.class, R.id.easeim_login_register_fragment_container, null);
    }
}
