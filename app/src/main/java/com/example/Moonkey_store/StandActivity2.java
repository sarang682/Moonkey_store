package com.example.Moonkey_store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.Moonkey_store.R;

public class StandActivity2 extends AppCompatActivity {

    Button make_store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standing_point);

        make_store=findViewById(R.id.make_store_btn);
        make_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StandActivity2.this, MakeStoreActivity.class);
                startActivity(intent);
            }
        });

    }
}