package com.jy.easeim;

import com.jy.base.IBaseCallBack;
import com.jy.base.IBasePresenter;
import com.jy.base.IBaseView;

/*
 * created by Cherry on 2019-12-04
 **/
public interface LoginRegisterContract {



    public interface ILoginView extends IBaseView<ILoginPresenter>{
        void onLoginSuccess();
        void onLoginError(int code,String msg);
    }


    public interface ILoginPresenter extends IBasePresenter<ILoginView>{

        void login(String username,String password);
    }


    public interface ILoginMode {
        void login(String username, String password, IBaseCallBack<Void> callBack);
    }


    public interface IRegisterView extends IBaseView<IRegisterPresenter>{

    }


    public interface IRegisterPresenter extends IBasePresenter<IRegisterView>{

    }


    public interface IRegisterMode {

    }
}
