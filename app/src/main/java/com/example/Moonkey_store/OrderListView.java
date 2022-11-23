package com.example.Moonkey_store;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class OrderListView extends LinearLayout {
    TextView text_price;
    TextView text_num;
    TextView text_address;


    public OrderListView(Context context) {
        super(context);
        init(context);
    }

    public OrderListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_orderlist, this, true);
        text_num = (TextView) findViewById(R.id.tv_num);
        text_address = (TextView) findViewById(R.id.tv_address2);
        text_price = (TextView) findViewById(R.id.tv_price);
    }
    public void setNum(String num) {
        text_num.setText("인원 : "+num+"명");
    }
    public void setPrice(String price) {
        text_price.setText("가격 : "+price+"원");
    }
    public void setAddress(String address){ text_address.setText("주소 : "+address);}

}