package com.example.icex.icexone.adapter;

import android.content.Context;
import android.view.View;

import com.example.icex.icexone.R;
import com.example.icex.icexone.adapter.Able.BookAble;
import com.example.icex.icexone.util.ImgLoadUtil;
import com.example.library.base.BaseAdapter;
import com.example.library.base.BaseViewHolder;
import com.example.library.bean.BookBean;

import java.util.List;

/**
 * ProjectName：IcexOne
 * Describe：书籍适配器
 * Author：Icex
 * CreationTime：2017/4/25
 */

public class BookAdapter extends BaseAdapter<BookBean.BooksEntity> {

    private int img_book;
    private int tv_book_name;
    private int layout_book;
    private BookAble callBack;

    public BookAdapter(Context context, List<BookBean.BooksEntity> list, BookAble able) {
        super(context, list);
        this.callBack = able;
    }

    @Override
    protected int setView(int viewType) {
        return R.layout.layout_book_item;
    }

    @Override
    protected void initView() {
        layout_book = R.id.layout_book;
        img_book = R.id.img_book;
        tv_book_name = R.id.tv_book_name;
    }

    @Override
    protected void onBindDataToView(BaseViewHolder holder, final BookBean.BooksEntity bean, int position) {
        holder.setText(tv_book_name, getValues(bean.getTitle()));
        //2代表使用图书默认图像
        ImgLoadUtil.displayEspImage(bean.getImage(), holder.getImageId(img_book), 2,context);
        holder.getLayoutId(layout_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.SelectBook(bean);
                }
            }
        });
    }


}
