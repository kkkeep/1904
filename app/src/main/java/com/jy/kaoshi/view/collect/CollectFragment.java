package com.jy.kaoshi.view.collect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.navi.view.PoiInputResItemWidget;
import com.jy.R;
import com.jy.kaoshi.KaoShiContract;
import com.jy.kaoshi.entiry.Data;
import com.jy.kaoshi.presenter.CollectPresenter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/*
 * created by Cherry on 2019-12-02
 **/
public class CollectFragment extends Fragment implements KaoShiContract.ICollectView {

    private RecyclerView mRecyclerView;
    private CollectAdapter mCollectAdapter;

    private CheckBox mCbAllSelect;
    private TextView mTvSelectCount;
    private Button mBthDelete;

    private CollectPresenter mCollectPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCollectPresenter = new CollectPresenter();

        mCollectPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mCollectPresenter != null) {
            mCollectPresenter.detachView();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.kaoshi_fragment_collect, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.kaoshi_collect_rv);
        mCollectAdapter = new CollectAdapter(null);

        mCollectAdapter.setmOnItemSelectChangerListener(new CollectAdapter.OnItemSelectChangerListener() {
            @Override
            public void onSelect(Data data, boolean isSelect) {
                onSelectItemChanged();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mCollectAdapter);

        mCbAllSelect = view.findViewById(R.id.kaoshi_collect_cb_all_select);
        mTvSelectCount = view.findViewById(R.id.kaoshi_collect_tv_select_count);
        mBthDelete = view.findViewById(R.id.kaoshi_collect_btn_delete);

        mTvSelectCount.setText("0/0");

        mBthDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Data> data = mCollectAdapter.getSelectedData();
                if (data != null && data.size() > 0) {
                    mCollectPresenter.deleteDataList(data);
                }
            }
        });


        mCbAllSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mCollectAdapter.selectAll();
                } else {
                    mCollectAdapter.unSelectAll();
                }

                onSelectItemChanged();
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mCollectPresenter.getCollectData();
        }
    }


    public void onSelectItemChanged() {
        ArrayList<Data> data = mCollectAdapter.getSelectedData();
        if (data != null && data.size() > 0) {
            mTvSelectCount.setText(data.size() + "/" + mCollectAdapter.getItemCount());
            mBthDelete.setEnabled(true);
            if (data.size() == mCollectAdapter.getItemCount()) {
                mCbAllSelect.setText("取消全选");
            }
        } else {
            mTvSelectCount.setText("0/" + mCollectAdapter.getItemCount());
            mBthDelete.setEnabled(false);
            mCbAllSelect.setText("全选");
        }

    }

    @Override
    public void onDataResult(List<Data> data, String msg) {
        if (data != null) {
            mCollectAdapter.setmList(data);
            mTvSelectCount.setText("0/" + data.size());
        } else {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onDeleteDataResult(List<Data> datas, String msg) {
        if(datas != null){
            mCollectAdapter.deleteSelectedData();
            onSelectItemChanged();
        }
    }
}
