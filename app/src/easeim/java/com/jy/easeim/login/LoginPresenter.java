package com.jy.easeim.login;

import android.icu.lang.UScript;

import com.jy.base.BasePresenter;
import com.jy.base.IBaseCallBack;
import com.jy.easeim.LoginRegisterContract;
import com.jy.easeim.data.repository.LoginRepository;

/*
 * created by Cherry on 2019-12-04
 **/
public class LoginPresenter extends BasePresenter<LoginRegisterContract.ILoginView> implements LoginRegisterContract.ILoginPresenter {

    private LoginRegisterContract.ILoginMode mLoginMode;


    public LoginPresenter() {
        this.mLoginMode = new LoginRepository();
    }

    @Override
    public void login(String username, String password) {

        mLoginMode.login(username, password, new IBaseCallBack<Void>() {
            @Override
            public void onSuccess(Void data) {
                if(mView != null){
                    mView.onLoginSuccess();
                }
            }

            @Override
            public void onFail(String msg) {
                if(mView != null){
                    mView.onLoginError(-1, msg);
                }
            }
        });
    }
}
