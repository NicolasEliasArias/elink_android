package com.example.nicol.elink.Builder;

import android.app.Activity;
import android.widget.TextView;

import com.example.nicol.elink.UI.ViewHolder.ViewHolder;

public class ActualProyectViewHolderBuilder implements ViewHolderBuilder {

    private ViewHolder textViewHolder;

    @Override
    public void createViewHolder(Activity context) {
        textViewHolder = new ViewHolder(context);
    }

    @Override
    public void createText(String text) {
        TextView textView = new TextView(textViewHolder.getContext());
        textView.setText(text);
        textViewHolder.setContent(textView);
    }


    @Override
    public void createButtons() {
    }

    @Override
    public ViewHolder getViewHolder() {
        return textViewHolder;
    }

}
