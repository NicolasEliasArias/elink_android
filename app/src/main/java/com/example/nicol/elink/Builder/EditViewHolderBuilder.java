package com.example.nicol.elink.Builder;
import android.widget.EditText;

public class EditViewHolderBuilder extends ViewHolderBuilder {

    @Override
    public void createText(String text) {
        EditText editText = new EditText(getViewHolder().getContext());
        editText.setText(text);
        getViewHolder().setTitleTextView(editText);
    }

}
