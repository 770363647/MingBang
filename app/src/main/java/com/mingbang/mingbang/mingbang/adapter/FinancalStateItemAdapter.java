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

public class FinancalStateItemAdapter extends RecyclerView.Adapter<FinancalStateItemAdapter.ViewHolder> implements
        View.OnClickListener {
    private List data;
    private Context context;

    private FinancalStateItemAdapter.OnItemClickListener mOnItemClickListener = null;

    public FinancalStateItemAdapter(List data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public FinancalStateItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.financalstate_item, parent,
                false);
        FinancalStateItemAdapter.ViewHolder viewHolder = new FinancalStateItemAdapter.ViewHolder(view);
        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FinancalStateItemAdapter.ViewHolder holder, int position) {
        int pos = position % 6;
        if (pos == 0) {
            holder.logo.setImageResource(R.mipmap.daily);
            holder.title.setText(context.getResources()
                    .getString(R.string.daily));
        } else if (pos == 1) {
            holder.logo.setImageResource(R.mipmap.weekly);
            holder.title.setText(context.getResources()
                    .getString(R.string.weekly));
        } else if (pos == 2) {
            holder.logo.setImageResource(R.mipmap.monthly);
            holder.title.setText(context.getResources()
                    .getString(R.string.monthly));
        } else if (pos == 3) {
            holder.logo.setImageResource(R.mipmap.quarterly);
            holder.title.setText(context.getResources()
                    .getString(R.string.quarterly));
        } else if (pos == 4) {
            holder.logo.setImageResource(R.mipmap.semiyearly);
            holder.title.setText(context.getResources()
                    .getString(R.string.semiyearly));
        } else if (pos == 5) {
            holder.logo.setImageResource(R.mipmap.yearly);
            holder.title.setText(context.getResources()
                    .getString(R.string.yearly));
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

    public void setOnItemClickListener(FinancalStateItemAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * TODO:item事件监听接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
