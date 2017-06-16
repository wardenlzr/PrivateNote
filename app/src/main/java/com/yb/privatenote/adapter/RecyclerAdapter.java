package com.yb.privatenote.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.yb.privatenote.R;
import com.yb.privatenote.bean.Note;

import java.util.List;

/**
 * Created by yb on 2017/6/15 15:38.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<Note> data;
    private MyItemClickListener mListener;

    public void setOnItemClickListener(MyItemClickListener listener){
        this.mListener = listener;
    }

    public RecyclerAdapter(Context mContext, List<Note> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_main_list, parent, false);
        inflate.setOnClickListener(this);
        return new MyViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvContent.setText(data.get(position).getContent());
        holder.tvTime.setText(data.get(position).getTime());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            int position = (int)v.getTag();
            //注意这里使用getTag方法获取position
            mListener.onItemClick(v,(int)v.getTag());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvContent;
        public TextView tvTime;

        public MyViewHolder(View view) {
            super(view);
            tvContent = (TextView) view.findViewById(R.id.tvContent);
            tvTime = (TextView) view.findViewById(R.id.tvTime);
        }


    }
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }
}
