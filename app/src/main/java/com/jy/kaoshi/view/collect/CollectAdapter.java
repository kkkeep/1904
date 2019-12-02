package com.jy.kaoshi.view.collect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jy.R;
import com.jy.kaoshi.entiry.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/*
 * created by Cherry on 2019-12-02
 **/
public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.CollectHolder>{

    private List<Data> mList;

    private OnItemSelectChangerListener mOnItemSelectChangerListener;

    public CollectAdapter(List<Data> list) {
        this.mList = list;
    }


    public void setmList(List<Data> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public ArrayList<Data> getSelectedData(){
        ArrayList<Data> selectedList = new ArrayList<>();
        for(Data data : mList){
            if(data.getIsSelected()){
                selectedList.add(data);
            }
        }

        return selectedList;
    }

    public void selectAll(){
        for(Data data : mList){
            data.setIsSelected(true);
        }

        notifyDataSetChanged();
    }
    public void unSelectAll(){
        for(Data data : mList){
            data.setIsSelected(false);
        }

        notifyDataSetChanged();
    }

    public void deleteSelectedData(){

        Iterator<Data> iterable = mList.iterator();
        Data data;
        while (iterable.hasNext()){
            data = iterable.next();
            if(data.getIsSelected()){
                iterable.remove();
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CollectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CollectHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.kaoshi_itme_collect, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CollectHolder holder, int position) {
        holder.setData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setmOnItemSelectChangerListener(OnItemSelectChangerListener mOnItemSelectChangerListener) {
        this.mOnItemSelectChangerListener = mOnItemSelectChangerListener;
    }

    public class CollectHolder extends RecyclerView.ViewHolder{

        private CheckBox cb;
        private ImageView pic;
        public CollectHolder(@NonNull View itemView) {
            super(itemView);

            cb = itemView.findViewById(R.id.kaoshi_collect_cb_select);
            pic = itemView.findViewById(R.id.kaoshi_collect_iv_pic);

            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isChecked = cb.isChecked();
                    mList.get(getAdapterPosition()).setIsSelected(isChecked);
                    //notifyItemChanged(getAdapterPosition());
                    if(mOnItemSelectChangerListener != null){
                        mOnItemSelectChangerListener.onSelect(mList.get(getAdapterPosition()),isChecked);
                    }
                }
            });
           /* cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }
            });*/
        }

        public void setData(Data data){
            cb.setChecked(data.getIsSelected());
            Glide.with(itemView).load(data.getUrl()).into(pic);
        }
    }

    public interface OnItemSelectChangerListener{

        void onSelect(Data data,boolean isSelect);
    }
}
