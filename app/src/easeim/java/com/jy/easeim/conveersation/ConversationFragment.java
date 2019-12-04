package com.jy.easeim.conveersation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.adapter.EMAConversation;
import com.jy.R;
import com.jy.base.BaseFragment;
import com.jy.base.IBasePresenter;

import java.util.List;
import java.util.Map;

/*
 * created by Cherry on 2019-12-04
 **/
public class ConversationFragment extends BaseFragment {


    private RecyclerView mRecyclerView;

    private ConversationAdapter adapter;


    @Override
    public void initView(View root) {

        mRecyclerView  = root.findViewById(R.id.easeim_home_conversation_list);

        List<EMConversation> allConversations = EMClient.getInstance().chatManager().getConversationsByType(EMConversation.EMConversationType.Chat);


        ConversationAdapter conversationAdapter = new ConversationAdapter(allConversations);
        adapter = conversationAdapter;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.setAdapter(conversationAdapter);
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_conversation;
    }


    @Override
    public IBasePresenter createPresenter() {
        return null;
    }
}
