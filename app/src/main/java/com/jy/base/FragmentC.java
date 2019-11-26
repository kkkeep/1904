package com.jy.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jy.R;
import com.jy.main.video.VideoContract;
import com.jy.utils.FragmentUtils;

/*
 * created by Cherry on 2019-11-26
 **/
public class FragmentC extends BaseFragment<VideoContract.IVideoPresenter>implements View.OnClickListener {

    private Button mButton;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_c;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mButton = view.findViewById(R.id.btn_fragment_c);
        mButton.setOnClickListener(this);
    }

    @Override
    public VideoContract.IVideoPresenter createPresenter() {
        return null;
    }


    public void  onClick(View v){
        FragmentUtils.addOrShowFragment(getFragmentManager(), FragmentA.class, R.id.main_fragment_container, null);
    }

    @Override
    public boolean isAddBackStack() {
        return true;
    }
}
