package com.mingbang.mingbang.mingbang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingbang.mingbang.mingbang.R;

import java.util.List;

/**
 * @author: zhaojy
 * @data:On 2018/1/20.
 */

public class ApproveItemAdapter extends RecyclerView.Adapter<ApproveItemAdapter.ViewHolder> implements
        View.OnClickListener {
    private List data;
    private Context context;

    private ApproveItemAdapter.OnItemClickListener mOnItemClickListener = null;

    public ApproveItemAdapter(List data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ApproveItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.approve_item, parent,
                false);
        ApproveItemAdapter.ViewHolder viewHolder = new ApproveItemAdapter.ViewHolder(view);
        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ApproveItemAdapter.ViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.logo.setImageResource(R.mipmap.order);
            holder.title.setText(context.getResources()
                    .getString(R.string.indentApproval));
        } else {
            holder.logo.setImageResource(R.mipmap.adjust_price);
            holder.title.setText(context.getResources()
                    .getString(R.string.adjustPrice));
        }
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
        ImageView logo;
        TextView title;

        private ViewHolder(View view) {
            super(view);
            logo = view.findViewById(R.id.logo);
            title = view.findViewById(R.id.title);
        }
    }

    public void setOnItemClickListener(ApproveItemAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * TODO:item事件监听接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}

