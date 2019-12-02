package com.jy.kaoshi.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.R;
import com.jy.kaoshi.KaoShiContract;
import com.jy.kaoshi.entiry.Data;
import com.jy.kaoshi.presenter.HomePresenter;

import java.util.List;

/*
 * created by Cherry on 2019-12-02
 **/
public class HomeFragment extends Fragment implements KaoShiContract.IHomeView {

    private RecyclerView mRecyclerView;

    private HomeAdapter mHomeAdapter;

    private HomePresenter iHomePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iHomePresenter = new HomePresenter();

        iHomePresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.kaoshi_fragment_home, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.kaoshi_rv_home);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mHomeAdapter = new HomeAdapter(null);

        mHomeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onPicClick(Data data) {

            }

            @Override
            public void onCollectClick(Data data) {
                iHomePresenter.collect(data);
            }
        });
        mRecyclerView.setAdapter(mHomeAdapter);
        loadData();
    }



    private void loadData(){
        iHomePresenter.getHomeData();
    }

    @Override
    public void onDataResult(List<Data> data, String msg) {
            if(data != null){
                mHomeAdapter.setLists(data);
            }else{
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    public void onCollectResult(Data data, String msg) {
        if(data != null){
            Toast.makeText(getContext(), "收藏成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(iHomePresenter != null){
            iHomePresenter.detachView();
        }
    }
}
