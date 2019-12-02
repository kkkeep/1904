package com.jy.kaoshi;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jy.R;
import com.jy.kaoshi.view.collect.CollectFragment;
import com.jy.kaoshi.view.home.HomeFragment;

/*
 * created by Cherry on 2019-12-02
 **/
public class KaoShiActivity extends AppCompatActivity {


    private HomeFragment mHomeFragment;
    private CollectFragment mCollectFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaoshi);

        mHomeFragment = new HomeFragment();
        mCollectFragment = new CollectFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.kaoshi_fragment_container, mHomeFragment, null);
        fragmentTransaction.add(R.id.kaoshi_fragment_container, mCollectFragment, null);

        fragmentTransaction.commit();


        showCurrentAndHideOther(mHomeFragment, mCollectFragment);

    }


    public void onClick(View view){
        if(view.getId() == R.id.kaoshi_btn_home){
            showCurrentAndHideOther(mHomeFragment, mCollectFragment);
        }else{
            showCurrentAndHideOther(mCollectFragment,mHomeFragment);
        }
    }

    private void showCurrentAndHideOther(Fragment current,Fragment otherFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.show(current);
        fragmentTransaction.hide(otherFragment);

        fragmentTransaction.commit();


    }

    private void initGreendao(){

    }
}
