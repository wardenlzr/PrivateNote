package com.yb.privatenote.ui.act;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yb.privatenote.R;
import com.yb.privatenote.adapter.RecyclerAdapter;
import com.yb.privatenote.base.BaseAct;
import com.yb.privatenote.bean.Note;
import com.yb.privatenote.util.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainAct extends BaseAct {


    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.parent)
    View parent;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;

    private List<Note> data;

    @Override
    public int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initUi() {
        try {
            data = new ArrayList<Note>();
            for (int i = 0; i < 15; i++) {
                data.add(new Note("攀比一下" + i, L.getTime()));
            }
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setItemAnimator(new DefaultItemAnimator());
            rv.setAdapter(new RecyclerAdapter(mContext, data));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
