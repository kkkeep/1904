package com.jy.kaoshi.view.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jy.R;
import com.jy.kaoshi.entiry.Data;

import java.util.List;

/*
 * created by Cherry on 2019-12-02
 **/
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeDataHolder> {

    private List<Data> mLists;

    private  OnItemClickListener onItemClickListener;

    public HomeAdapter(List<Data> ists) {
        this.mLists = mLists;
    }

    @NonNull
    @Override
    public HomeDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeDataHolder (LayoutInflater.from(parent.getContext()).inflate(R.layout.kaoshi_item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeDataHolder holder, int position) {
        holder.setData(mLists.get(position));
    }

    @Override
    public int getItemCount() {
        return  mLists == null ? 0 : mLists.size();
    }

    public void setLists(List<Data> lists) {
        this.mLists = lists;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class HomeDataHolder extends RecyclerView.ViewHolder{

        private ImageView pic;
        private TextView tvDate;
        private Button btnCollect;

        public HomeDataHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.kaoshi_home_iv_pic);
            tvDate = itemView.findViewById(R.id.kaoshi_home_tv_date);
            btnCollect = itemView.findViewById(R.id.kaoshi_home_btn_collect);

            btnCollect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onCollectClick(mLists.get(getAdapterPosition()));
                    }
                }
            });


            pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onPicClick(mLists.get(getAdapterPosition()));
                    }
                }
            });

        }


        public void setData(Data data){
            Glide.with(itemView).load(data.getUrl()).into(pic);
            tvDate.setText(data.getDesc());

        }
    }

    public interface  OnItemClickListener{
        void onPicClick(Data data);
        void onCollectClick(Data data);
    }
}
