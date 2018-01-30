package com.mingbang.mingbang.mingbang.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.bean.StaffInforBean;

import java.util.List;

/**
 * @author: zhaojy
 * @data:On 2018/1/22.
 */

public class StaffInforItemAdapter extends RecyclerView.Adapter<StaffInforItemAdapter.ViewHolder> implements
        View.OnClickListener {
    private List<StaffInforBean> data;
    private Context context;

    private StaffInforItemAdapter.OnItemClickListener mOnItemClickListener = null;

    public StaffInforItemAdapter(List<StaffInforBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public StaffInforItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.person_infor_item, parent,
                false);
        StaffInforItemAdapter.ViewHolder viewHolder = new StaffInforItemAdapter.ViewHolder(view);
        view.setOnClickListener(this);

        return viewHolder;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(StaffInforItemAdapter.ViewHolder holder, int position) {
        holder.index.setText(data.get(position).getIndex());
        holder.name.setText(data.get(position).getName());
        if (!data.get(position).getIndexShow()) {
            holder.index.setVisibility(View.INVISIBLE);
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

        TextView index;
        ImageView avatar;
        TextView name;

        private ViewHolder(View view) {
            super(view);
            index = view.findViewById(R.id.index);
            avatar = view.findViewById(R.id.avatar);
            name = view.findViewById(R.id.name);
        }
    }

    public void setOnItemClickListener(StaffInforItemAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * TODO:item事件监听接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}