package com.mingbang.mingbang.mingbang.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.bean.InforItemBean;

import java.util.List;

/**
 * @author: zhaojy
 * @data:On 2018/1/19.
 */

public class InforItemAdapter extends RecyclerView.Adapter<InforItemAdapter.ViewHolder> implements
        View.OnClickListener {
    private List<InforItemBean> data;
    private Context context;

    private OnItemClickListener mOnItemClickListener = null;

    public InforItemAdapter(List<InforItemBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.infor_item, parent,
                false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);

        return viewHolder;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.logo.setImageResource(data.get(position).getLogo());
        holder.title.setText(data.get(position).getTitle());
        holder.content.setText(data.get(position).getContent());
        holder.time.setText(data.get(position).getTime());

        if (data.get(position).getTitle() == "事故处理") {
            Drawable drawable = context.getDrawable(R.drawable.infor_item_logo_red_shape);
            holder.logoBox.setBackground(drawable);
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
        TextView time;
        TextView content;
        LinearLayout logoBox;

        private ViewHolder(View view) {
            super(view);
            logo = view.findViewById(R.id.logo);
            title = view.findViewById(R.id.title);
            time = view.findViewById(R.id.time);
            content = view.findViewById(R.id.content);
            logoBox = view.findViewById(R.id.logoBox);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * TODO:item事件监听接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
