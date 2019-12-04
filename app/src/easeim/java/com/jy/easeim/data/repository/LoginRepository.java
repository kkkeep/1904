package com.jy.easeim.data.repository;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.jy.base.IBaseCallBack;
import com.jy.easeim.LoginRegisterContract;

/*
 * created by Cherry on 2019-12-04
 **/
public class LoginRepository implements LoginRegisterContract.ILoginMode {


    @Override
    public void login(String username, String password, IBaseCallBack<Void> callBack) {

        EMClient.getInstance().login(username, password, new EMCallBack() {
            @Override
            public void onSuccess() {
                callBack.onSuccess(null);
            }

            @Override
            public void onError(int i, String s) {
                callBack.onFail(s);
            }

            @Override
            public void onProgress(int i, String s) {
            }
        });
    }
}
