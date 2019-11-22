package com.jy.base;

import android.content.Context;
import androidx.annotation.NonNull;
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresenter != null){
            mPresenter.detachView();
        }

    }
}
