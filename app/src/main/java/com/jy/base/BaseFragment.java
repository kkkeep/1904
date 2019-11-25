package com.jy.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*
 * created by Cherry on 2019-11-22
 **/
public abstract class BaseFragment<P extends IBasePresenter> extends Fragment implements IBaseView<P>{

    protected  P mPresenter;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mPresenter = createPresenter();
        if(mPresenter != null){
            mPresenter.attachView(this);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View  view = LayoutInflater.from(getContext()).inflate(getLayoutId(), container, false);

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresenter != null){
            mPresenter.detachView();
        }

    }


    protected abstract int getLayoutId();


}
