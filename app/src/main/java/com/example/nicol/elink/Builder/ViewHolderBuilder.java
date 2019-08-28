package com.example.nicol.elink.Builder;

import android.app.Activity;

import com.example.nicol.elink.UI.ViewHolder.ViewHolder;

public interface ViewHolderBuilder {

    public void createViewHolder(Activity context);
    public void createText(String text);
    public void createButtons();
    public ViewHolder getViewHolder();

}
