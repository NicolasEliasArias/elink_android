package com.example.nicol.elink.Director;

import android.app.Activity;
import com.example.nicol.elink.Builder.ViewHolderBuilder;
import com.example.nicol.elink.UI.ViewHolder.ViewHolder;

public class DirectorViewHolder {

    private ViewHolderBuilder pvhBuilder;

    public DirectorViewHolder(){}
    public DirectorViewHolder(ViewHolderBuilder pvhBuilder){
        this.pvhBuilder = pvhBuilder;
    }

    public ViewHolder createViewHolder(Activity context, String text){
        pvhBuilder.createViewHolder(context);
        pvhBuilder.createButtons();
        pvhBuilder.createText(text);
        return pvhBuilder.getViewHolder();
    }

    public ViewHolderBuilder getBuilder() {
        return pvhBuilder;
    }

    public void setBuilder(ViewHolderBuilder pvhBuilder) {
        this.pvhBuilder = pvhBuilder;
    }
}
