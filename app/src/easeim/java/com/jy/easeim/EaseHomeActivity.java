package com.jy.easeim;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jy.R;
import com.jy.easeim.conveersation.ConversationFragment;
import com.jy.utils.FragmentUtils;

/*
 * created by Cherry on 2019-12-04
 **/
public class EaseHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.actitivty_easeim_home);
        FragmentUtils.addOrShowFragment(getSupportFragmentManager(), ConversationFragment.class, R.id.easeim_home_fragment_container, null);
    }
}
