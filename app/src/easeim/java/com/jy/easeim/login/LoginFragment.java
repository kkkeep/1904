package com.jy.easeim.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.jy.R;
import com.jy.base.BaseFragment;
import com.jy.easeim.EaseHomeActivity;
import com.jy.easeim.EaseSplashActivity;
import com.jy.easeim.LoginRegisterContract;

import java.util.logging.Handler;

/*
 * created by Cherry on 2019-12-04
 **/
public class LoginFragment extends BaseFragment<LoginRegisterContract.ILoginPresenter> implements LoginRegisterContract.ILoginView {

    private EditText mEtUserName;
    private EditText mEtPassword;

    private Button mBtnLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }


    @Override
    public void initView(View root) {
        mEtPassword = root.findViewById(R.id.easeim_login_et_password);
        mEtUserName = root.findViewById(R.id.easeim_login_et_username);
        mBtnLogin = root.findViewById(R.id.easeim_login_btn_login);

        mBtnLogin.setOnClickListener(v -> {
            String userName = mEtUserName.getText().toString().trim();
            if(TextUtils.isEmpty(userName)){
                showToast("用户名不能为空");
                return;
            }

            String password = mEtPassword.getText().toString().trim();
            if(TextUtils.isEmpty(password)){
                showToast("密码不能为空");
                return;
            }


            mPresenter.login(userName,password);
        });



    }

    @Override
    public void initData() {
        showToast(EMClient.getInstance().getCurrentUser());

    }

    @Override
    public void onLoginSuccess() {
        EMClient.getInstance().groupManager().loadAllGroups();
        EMClient.getInstance().chatManager().loadAllConversations();
        getActivity().runOnUiThread(() -> {
            showToast("登录成功 " +  EMClient.getInstance().getCurrentUser());
            startActivity(new Intent(getContext(), EaseHomeActivity.class));
        });

    }

    @Override
    public void onLoginError(int code, String msg) {
        getActivity().runOnUiThread(() -> {
            showToast(msg);
        });


    }

    @Override
    public LoginRegisterContract.ILoginPresenter createPresenter() {
        return new LoginPresenter() ;
    }
}
