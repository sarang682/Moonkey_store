package com.example.ui2_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MyPageActivity extends AppCompatActivity {

    private Button orderlist, modify;
    private TextView name, classification, address, callnum, likenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        orderlist=findViewById(R.id.orderlist);
        modify=findViewById(R.id.modify);
        name=findViewById(R.id.store_name);
        classification=findViewById(R.id.store_classification);
        address=findViewById(R.id.store_address);
        callnum=findViewById(R.id.store_callnum);
        likenum=findViewById(R.id.store_likenum);


        orderlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),OrderListActivity.class);
                startActivity(intent);
            }
        });
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),EditAccountActivity.class);
                startActivity(intent);
            }
        });


    }
}