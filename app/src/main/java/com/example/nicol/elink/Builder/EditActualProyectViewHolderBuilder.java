package com.example.nicol.elink.Builder;

import android.app.Activity;
import android.widget.EditText;

import com.example.nicol.elink.UI.ViewHolder.ViewHolder;

public class EditActualProyectViewHolderBuilder implements ViewHolderBuilder {

    private ViewHolder inputViewHolder;

    @Override
    public void createViewHolder(Activity context) {
        inputViewHolder = new ViewHolder(context);
    }

    @Override
    public void createText(String text) {
        EditText editText = new EditText(inputViewHolder.getContext());
        inputViewHolder.setContent(editText);
        editText.setText(text);
    }

    @Override
    public void createButtons() {

    }

    @Override
    public ViewHolder getViewHolder() {
        return inputViewHolder;
    }

}
