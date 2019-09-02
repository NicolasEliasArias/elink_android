package com.example.nicol.elink.Builder;


import android.app.Activity;

import com.example.nicol.elink.UI.ViewHolder.ViewHolder;

public class ViewHolderBuilder implements IViewHolderBuilder {

    private ViewHolder viewHolder;

    @Override
    public void createViewHolder(Activity context) {
        viewHolder = new ViewHolder(context);
    }

    @Override
    public void createText(String text) {
        viewHolder.setTitle(text);
    }

    @Override
    public void createButtons() {

    }

    @Override
    public ViewHolder getViewHolder() {
        return viewHolder;
    }
}
