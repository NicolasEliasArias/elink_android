package com.example.nicol.elink.Director;

import android.app.Activity;
import com.example.nicol.elink.Builder.IViewHolderBuilder;
import com.example.nicol.elink.UI.ViewHolder.ViewHolder;

public class DirectorViewHolder {

    private IViewHolderBuilder vhBuilder;

    public DirectorViewHolder(){}
    public DirectorViewHolder(IViewHolderBuilder pvhBuilder){
        this.vhBuilder = pvhBuilder;
    }

    public ViewHolder createViewHolder(Activity context, String text){
        vhBuilder.createViewHolder(context);
        vhBuilder.createButtons();
        vhBuilder.createText(text);
        return vhBuilder.getViewHolder();
    }

    //Getters y Setters ----------------------------------------------------
    public IViewHolderBuilder getBuilder() {
        return vhBuilder;
    }

    public void setBuilder(IViewHolderBuilder pvhBuilder) {
        this.vhBuilder = pvhBuilder;
    }
}
