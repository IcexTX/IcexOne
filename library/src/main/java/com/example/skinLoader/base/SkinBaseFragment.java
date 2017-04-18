package com.example.skinLoader.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.example.skinLoader.attr.DynamicAttr;
import com.example.skinLoader.listener.IDynamicNewView;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName：IcexOne
 * Describe：
 * Author：Icex
 * CreationTime：2017/1/12
 */

public class SkinBaseFragment extends Fragment implements IDynamicNewView {

    private IDynamicNewView iDynamicNewView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            iDynamicNewView = (IDynamicNewView) context;
        } catch (ClassCastException e) {
            iDynamicNewView = null;
        }
    }

    @Override
    public void dynamicAddView(View view, List<DynamicAttr> pDAttrs) {
        if (iDynamicNewView == null) {
            throw new RuntimeException("IDynamicNewView should be implements !");
        } else {
            iDynamicNewView.dynamicAddView(view, pDAttrs);
        }
    }

    public void dynamicAddSkinView(View view, String attrName, int attrValueResId) {
        List<DynamicAttr> pDAttrs = new ArrayList<>();
        pDAttrs.add(new DynamicAttr(attrName, attrValueResId));
        dynamicAddView(view, pDAttrs);
    }

    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        LayoutInflater result = getActivity().getLayoutInflater();
        return result;
    }
}
