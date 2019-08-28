package com.example.nicol.elink.UI.ViewHolder;

import android.app.Activity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ViewHolder extends LinearLayout {

    private TextView content;
    private Activity context;
    private LinearLayout buttonsLayout;
    private List<Button> buttons;

    public ViewHolder(Activity context) {
        super(context);
        this.context = context;
        this.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        this.setOrientation(VERTICAL);
        buttonsLayout = new LinearLayout(context);
        buttonsLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        buttons = new ArrayList<>();
        this.addView(buttonsLayout,0);
    }

    public Button addButton(){
        Button btn = new Button(getContext());
        getButtons().add(btn);
        getButtonsLayout().addView(btn);
        return  btn;
    }

    public TextView getContent(){
        return content;
    }

    public void setContent(TextView content){
        this.content = content;
        this.addView(content,0);
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public LinearLayout getButtonsLayout() {
        return buttonsLayout;
    }

    public void setButtonsLayout(LinearLayout buttonsLayout) {
        this.buttonsLayout = buttonsLayout;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    public void setText(String text){
        content.setText(text);
    }
    public String getText(){
        return  content.getText().toString();
    }
}
