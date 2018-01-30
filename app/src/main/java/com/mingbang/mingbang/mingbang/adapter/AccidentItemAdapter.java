package com.mingbang.mingbang.mingbang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingbang.mingbang.mingbang.R;

import java.util.List;

/**
 * @author: zhaojy
 * @data:On 2018/1/20.
 */

public class AccidentItemAdapter extends RecyclerView.Adapter<AccidentItemAdapter.ViewHolder> implements
        View.OnClickListener {
    private List data;
    private Context context;

    private AccidentItemAdapter.OnItemClickListener mOnItemClickListener = null;

    public AccidentItemAdapter(List data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public AccidentItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.accident_item, parent,
                false);
        AccidentItemAdapter.ViewHolder viewHolder = new AccidentItemAdapter.ViewHolder(view);
        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AccidentItemAdapter.ViewHolder holder, int position) {

        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view, (Integer) view.getTag());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        private ViewHolder(View view) {
            super(view);

        }
    }

    public void setOnItemClickListener(AccidentItemAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * TODO:item事件监听接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
