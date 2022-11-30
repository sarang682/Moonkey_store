package com.example.Moonkey_store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OrderDetailActivity extends AppCompatActivity {

    private TextView  addr, total;
    private Button call;
    private StoreItem storeInfo;
    private OrderItem orderItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptorder);

        Intent intent = getIntent();
        orderItem= (OrderItem) intent.getSerializableExtra("orderItem");
        storeInfo= (StoreItem) intent.getSerializableExtra("storeInfo");
        String token = intent.getStringExtra("token");
        String uid = intent.getStringExtra("uid");

        addr=findViewById(R.id.address);
        total=findViewById(R.id.amount);
        call=findViewById(R.id.call_rider);

        addr.setText(orderItem.getAddress());
        total.setText(Integer.toString(orderItem.getAmount()));


        //라이더 요청
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),CallRiderActivity.class);
//                intent.putExtra("address",address);
//                intent.putExtra("amount",amount);
                intent.putExtra("orderItem",orderItem);
//                intent.putExtra("strname",strname);
//                intent.putExtra("straddr",straddr);
                intent.putExtra("storeInfo",storeInfo);
                intent.putExtra("token",token);
                intent.putExtra("uid",uid);
                startActivity(intent);
            }
        });


    }
}