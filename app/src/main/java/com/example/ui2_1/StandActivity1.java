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

public class StandActivity1 extends AppCompatActivity {

    Button standing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howtostanding);

        standing=findViewById(R.id.standing_point_btn);
        standing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StandActivity1.this, StandActivity2.class);
                startActivity(intent);
            }
        });

    }
}