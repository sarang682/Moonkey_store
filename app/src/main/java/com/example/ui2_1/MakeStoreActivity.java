package com.example.ui2_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ui2_1.R;

public class MakeStoreActivity extends AppCompatActivity {

    Button approval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_make_store);

        approval=findViewById(R.id.wait_approval_btn);
        approval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MakeStoreActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}