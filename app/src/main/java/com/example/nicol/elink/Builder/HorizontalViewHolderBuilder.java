package com.example.nicol.elink.Builder;
import android.app.Activity;

public class HorizontalViewHolderBuilder extends ViewHolderBuilder {
    @Override
    public void createViewHolder(Activity context) {
        super.createViewHolder(context);
        getViewHolder().setOrientation(getViewHolder().HORIZONTAL);
    }
}
