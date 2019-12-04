package com.jy.easeim.conveersation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.adapter.EMAConversation;
import com.hyphenate.chat.adapter.message.EMAMessage;
import com.hyphenate.chat.adapter.message.EMAMessageBody;
import com.hyphenate.chat.adapter.message.EMATextMessageBody;
import com.jy.R;
import com.jy.utils.SimpleDateFormatUtils;

import java.util.List;

/*
 * created by Cherry on 2019-12-04
 **/
public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.Holder> {

    private List<EMConversation> mList;


    public ConversationAdapter(List<EMConversation> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.easeis_item_conversation, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.setData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        private TextView tvUserNmae;
        private TextView tvDate;
        private TextView tvContent;


        public Holder(@NonNull View itemView) {
            super(itemView);

            tvUserNmae = itemView.findViewById(R.id.easeim_conversation_item_tv_username);
            tvDate = itemView.findViewById(R.id.easeim_conversation_item_tv_date);
            tvContent = itemView.findViewById(R.id.easeim_conversation_item_tv_content);
        }



        public void setData(EMConversation data){
            tvUserNmae.setText(data.conversationId());

            EMMessage lastMessage = data.getLastMessage();
            if(lastMessage != null){
                tvDate.setText(SimpleDateFormatUtils.formatFromLong(lastMessage.getMsgTime()));
            }else{
                tvDate.setText("");
            }


                EMMessageBody emaMessageBody = lastMessage.getBody();
                if(emaMessageBody != null){
                    EMMessage.Type type = lastMessage.getType();
                    if(type == EMMessage.Type.TXT){
                        tvContent.setText(((EMTextMessageBody)emaMessageBody).getMessage());
                        return;
                    }
            }

            tvContent.setText("空消息");

        }
    }
}
