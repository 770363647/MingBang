package com.mingbang.mingbang.mingbang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.bean.ContactsItemBean;

import java.util.List;

/**
 * @author: zhaojy
 * @data:On 2018/1/21.
 */

public class ContactsItemAdapter extends RecyclerView.Adapter<ContactsItemAdapter.ViewHolder> implements
        View.OnClickListener {
    private List<ContactsItemBean> data;
    private Context context;

    private ContactsItemAdapter.OnItemClickListener mOnItemClickListener = null;

    public ContactsItemAdapter(List<ContactsItemBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ContactsItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contacts_item, parent,
                false);
        ContactsItemAdapter.ViewHolder viewHolder = new ContactsItemAdapter.ViewHolder(view);
        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactsItemAdapter.ViewHolder holder, int position) {
        holder.name.setText(data.get(position).getName());
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
        TextView name;

        private ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
        }
    }

    public void setOnItemClickListener(ContactsItemAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * TODO:item事件监听接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
