package com.example.Moonkey_store;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FindAccountActivity extends AppCompatActivity {

    Button btn_find;
    EditText name,phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchid);

        name=findViewById(R.id.SearchName);
        phoneNumber=findViewById(R.id.PhoneNumber);

        btn_find=findViewById(R.id.SearchIdbtn);
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}