package com.example.Moonkey_store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CallRiderActivity extends AppCompatActivity {

    private  int amount;
    private String address,  strname, straddr;
    private TextView pname, addr, total, storename, storeaddr;
    private Button searchrider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_type);

        Intent intent = getIntent();
        address = intent.getStringExtra("address");
        amount = intent.getIntExtra("amount",0);

        strname = intent.getStringExtra("strname");
        straddr = intent.getStringExtra("straddr");

        pname=findViewById(R.id.pname);
        addr=findViewById(R.id.address);
        total=findViewById(R.id.total);
        storename=findViewById(R.id.store_name);
        storeaddr=findViewById(R.id.store_address);
        searchrider=findViewById(R.id.search_rider);

        addr.setText(address);
        total.setText(Integer.toString(amount));
        storename.setText(strname);
        storeaddr.setText(straddr);

        //공고 등록
        searchrider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "등록되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
