package com.jy.kaoshi2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.R;
import com.jy.base.BaseFragment;

import java.util.ArrayList;
import java.util.Map;

/*
 * created by Cherry on 2019-12-18
 **/
public class KaoShiFragment extends BaseFragment<KSContract.KSPresenter> implements KSContract.KSView {


    private RecyclerView mRvNewsList;
    private Button mBtnEdit, mBtnDelete;

    private KaoShiAdapter mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_kaoshi;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRvNewsList = view.findViewById(R.id.kaoshi_rv_news);
        mBtnEdit = view.findViewById(R.id.kaoshi_btn_edit);
        mBtnDelete = view.findViewById(R.id.kaoshi_btn_delete);

        mBtnDelete.setVisibility(View.INVISIBLE);


        mBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mode = mAdapter.switchMode();
                if (mode == KaoShiAdapter.MODE_VIEW) {
                    mBtnEdit.setText(R.string.text_edit);
                    mBtnDelete.setVisibility(View.INVISIBLE);
                }else{
                    mBtnEdit.setText(R.string.text_cancel);
                }
            }
        });

        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.deleteSelectedNews();
            }
        });

        mAdapter = new KaoShiAdapter();
        mAdapter.setOnItemClickListener(new KaoShiAdapter.OnItemClickListener() {
            @Override
            public void onClick() {

            }

            @Override
            public void onItemSelect(boolean selected) {
                mBtnDelete.setVisibility(selected ? View.VISIBLE  : View.INVISIBLE);
            }
        });
        mRvNewsList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvNewsList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRvNewsList.setAdapter(mAdapter);


        mPresenter.getNews("android", "zh");

    }

    @Override
    public KSContract.KSPresenter createPresenter() {
        return new KaoShiPresenter();
    }

    @Override
    public void onResultBack(ArrayList<KaoShiBean.Article> articles, String msg) {

        if (articles != null && msg == null) {
            mAdapter.setList(articles);
            mAdapter.notifyDataSetChanged();
        } else {
            showToast(msg);
        }
    }
}
