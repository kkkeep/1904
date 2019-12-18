package com.jy.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
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
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = LayoutInflater.from(getContext()).inflate(getLayoutId(), container, false);
        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    public void initView(View root){

    }


    public void initData(){

    }

    public void showToast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    public void showToast(@StringRes  int sId){
        Toast.makeText(getContext(), sId, Toast.LENGTH_LONG).show();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresenter != null){
            mPresenter.detachView();
        }

    }

    public boolean isAddBackStack(){
        return true;
    }
    public Action getAction(){
        return Action.Hide;
    }

    public enum Action{

        Remove,Detach,Hide
    }
    protected abstract int getLayoutId();



}
