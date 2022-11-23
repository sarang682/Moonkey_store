package com.example.Moonkey_store;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MenuListView extends LinearLayout {
    TextView text_name;
    TextView text_price;
    TextView text_comment;


    public MenuListView(Context context) {
        super(context);
        init(context);
    }

    public MenuListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_menulist, this, true);
        text_price = (TextView) findViewById(R.id.menu_price);
        text_name = (TextView) findViewById(R.id.menu_name);
        text_comment=(TextView) findViewById(R.id.menu_comment);
    }
    public void setName(String name) {
        text_name.setText(name);
    }
    public void setPrice(String price){ text_price.setText(price);}
    public void setComment(String comment){
        if(comment.length()==0||comment.equals("null")){
            text_comment.setText("");
        }
        else{text_comment.setText(comment);}
    }

}

