package com.jy.kaoshi2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.R;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * created by Cherry on 2019-12-18
 **/
public class KaoShiAdapter extends RecyclerView.Adapter<KaoShiAdapter.NewsHolder> {

    public static final int MODE_VIEW = 0x00000000;
    public static final int MODE_EDIT = 0x00000001;


    private ArrayList<KaoShiBean.Article> list;

    private OnItemClickListener mOnItemClickListener;

    private int currentMode = MODE_VIEW;

    private int count;

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("Test", "new holder count = " + ++count);
        NewsHolder newsHolder = new NewsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kaoshi_news, parent, false));
        newsHolder.setName(count+"");
        return newsHolder;
    }


    public int switchMode(){
        currentMode = ~currentMode;
        if(currentMode == MODE_VIEW){
            clearState();
        }
        notifyDataSetChanged();
        return currentMode;
    }
    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        Log.d("Test", " 把 position =  " + position +  " 的新闻数据绑定到  " + holder.getName() );

        holder.setData(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setList(ArrayList<KaoShiBean.Article> list) {
        this.list = list;
    }


    public void deleteSelectedNews(){
        Log.d("Test", "before delete count = " + list.size());
        Iterator<KaoShiBean.Article> iterator = list.iterator();
        KaoShiBean.Article article;
        while (iterator.hasNext()){
            article = iterator.next();
            if(article.isSelect()){
                iterator.remove();
            }
        }
        Log.d("Test", "after delete count = " + list.size());
        notifyDataSetChanged();
    }


    private void clearState(){
        for(KaoShiBean.Article article : list){
            article.setSelect(false);
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public class NewsHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle;
        private TextView tvTime;
        private CheckBox cbSelect;

        private String name;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);


            tvTitle = itemView.findViewById(R.id.kaoshi_item_tv_title);
            tvTime = itemView.findViewById(R.id.kaoshi_item_tv_time);
            cbSelect = itemView.findViewById(R.id.kaoshi_item_cb_select);

            cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    KaoShiBean.Article article = list.get(getAdapterPosition());
                    article.setSelect(isChecked);

                    int count = 0;
                    for(KaoShiBean.Article article1 : list){
                        if(article1.isSelect()){
                            count = 1;
                            break;
                        }
                    }
                    if(mOnItemClickListener != null){
                        mOnItemClickListener.onItemSelect(count > 0);
                    }

                }
            });


        }

        public void setName(String name){
            this.name = "我是Holder :  " + name;
        }

        public String getName() {
            return name;
        }

        public void setData(KaoShiBean.Article data){
            tvTime.setText(itemView.getContext().getString(R.string.text_read_time, data.getTime()));
            tvTitle.setText(data.getTheme());
            cbSelect.setChecked(data.isSelect());
            cbSelect.setVisibility(currentMode == MODE_VIEW ? View.INVISIBLE : View.VISIBLE);


        }
    }


    public interface OnItemClickListener{

        void onClick();

        void onItemSelect(boolean selected);
    }
}
