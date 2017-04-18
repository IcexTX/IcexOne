package com.example.library.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 所有RecyclerView适配器的基类
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> dataList = new ArrayList<>();
    protected Context context;
    protected LayoutInflater inflater;

    /**
     * 设置layout
     *
     * @return
     */
    protected abstract int setView(int viewType);

    /**
     * 初始化控件id
     */
    protected abstract void initView();

    /**
     * 绑定数据到Item控件上
     *
     * @param holder
     * @param bean
     * @param position
     */
    protected abstract void onBindDataToView(BaseViewHolder holder, T bean, int position);

    public BaseAdapter(Context context, List<T> list) {
        this.dataList = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(setView(viewType), parent, false);
        BaseViewHolder holder = new BaseViewHolder(view);
        initView();
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        onBindDataToView(holder, dataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return dataList.isEmpty() ? 0 : dataList.size();
    }

    /**
     * 设置数据源
     */
    public void setDataList(List<T> list) {
        this.dataList = list;
        notifyDataSetChanged();
    }

    /**
     * 清楚数据后再添加数据
     *
     * @param list
     * @param isClear
     */
    public void addDataList(List<T> list, boolean isClear) {
        if (isClear) {
            dataList.clear();
        }
        addDataList(list);

    }

    /**
     * 添加数据
     *
     * @param list
     */
    public void addDataList(List<T> list) {
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 清楚数据
     */
    public void clear() {
        dataList.clear();
        notifyDataSetChanged();
    }

    protected String getValues(String value) {
        return TextUtils.isEmpty(value) ? "" : value;
    }

    /**
     * 获取图片
     *
     * @param id
     * @return
     */
    protected final Drawable getDraw(int id) {
        return ContextCompat.getDrawable(context, id);
    }

}
