package com.jy.kaoshi;

import com.jy.kaoshi.entiry.Data;

import java.util.List;

/*
 * created by Cherry on 2019-12-02
 **/
public interface KaoShiContract {


    public interface  IHomeView{

        void onDataResult(List<Data> data, String msg);
        void onCollectResult(Data data,String msg);
    }

    public interface  IHomePresenter{

        void getHomeData();
        void collect(Data data);
    }

    public interface  IHomeMode{

        void getHomeData(IBaseCallBack<List<Data>> iBaseCallBack);
        void collect(Data data,IBaseCallBack<Data> iBaseCallBack);
    }


    public interface  ICollectView{
        void onDataResult(List<Data> data,String msg);

        void onDeleteDataResult(List<Data> datas,String msg);
    }

    public interface  ICollectPresenter{
        void getCollectData();

        void deleteDataList(List<Data> list);
    }

    public interface  ICollectMode{
        void getCollectData(IBaseCallBack<List<Data>> iBaseCallBack);

        void deleteDataList(List<Data> list,IBaseCallBack<List<Data>> iBaseCallBack);
    }


}
