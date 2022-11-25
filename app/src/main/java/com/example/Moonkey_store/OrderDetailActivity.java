package com.example.Moonkey_store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OrderDetailActivity extends AppCompatActivity {

    private  int amount;
    private String address;
    private TextView name, addr, total;
    private Button call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptorder);

        Intent intent = getIntent();
        address = intent.getStringExtra("address");
        amount = intent.getIntExtra("amount",0);

        String strname = intent.getStringExtra("strname");
        String straddr = intent.getStringExtra("straddr");

        name=findViewById(R.id.name);
        addr=findViewById(R.id.address);
        total=findViewById(R.id.amount);
        call=findViewById(R.id.call_rider);

        addr.setText(address);
        total.setText(Integer.toString(amount));


        //라이더 요청
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),CallRiderActivity.class);
                intent.putExtra("address",address);
                intent.putExtra("amount",amount);

                intent.putExtra("strname",strname);
                intent.putExtra("straddr",straddr);
                startActivity(intent);
            }
        });


    }
}