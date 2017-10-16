package com.example.fungwahtools.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.fungwahtools.bean.ListViewHolder;

import java.util.List;

/**
 * 先新建一个ViewHolder类实现ListViewHolder
 * 再新建本类对象，传入相应的list数据和上下文资源对象
 * <p>
 * 实现getLayoutId()方法:返回一个R.id的id
 * 实现setView()方法:进行绑定View后的相应数据设置等操作
 * <p>
 * Created by 区枫华 on 2017/9/6.
 */

public abstract class BaseListAdapter<ViewHolder extends ListViewHolder> extends BaseAdapter {

    /**
     * 数据项的list
     */
    protected List list;
    /**
     * 上下文资源对象
     */
    protected Context context;

    public BaseListAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(getLayoutId(), parent, false);
            viewHolder = initViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        setView(viewHolder, position);
        return convertView;
    }

    protected abstract ViewHolder initViewHolder(View convertView);

    /**
     * 设置item 对应view的id
     *
     * @return 返回一个R.id的id
     */
    protected abstract int getLayoutId();

    /**
     * 用于设置各个相应View的数据等操作的方法
     *
     * @param viewHolder 相对应的viewHolder
     * @param position   当前视图的位置
     */
    protected abstract void setView(ViewHolder viewHolder, int position);

}
