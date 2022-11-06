package com.example.ui2_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MenuDetailActivity extends AppCompatActivity {

    private TextView Contents;
    private ImageView Image;
    private EditText Name, Price, Comment, OptName, OptPrice;
    private Button File, OptAdd, OptDelete, Delete, Complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_productinfo);

        int price = getIntent().getIntExtra("price",0);
        String name = getIntent().getStringExtra("name");
        String comment = getIntent().getStringExtra("comment");

        Name=findViewById(R.id.modify_menu_name);
        Price=findViewById(R.id.modify_menu_cost);
        Comment=findViewById(R.id.modify_menu_explan);
        OptName=findViewById(R.id.optionname);
        OptPrice=findViewById(R.id.optioncost);

        File=findViewById(R.id.attached_file_btn);
        OptAdd=findViewById(R.id.btnAdd);
        OptDelete=findViewById(R.id.btnDelete);
        Delete=findViewById(R.id.delete_menu);
        Complete=findViewById(R.id.complete);

        Price.setText(Integer.toString(price));
        Name.setText(name);
        Comment.setText(comment);


        File.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "파일찾기", Toast.LENGTH_SHORT).show();
            }
        });

        OptAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "추가되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        OptDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuDetailActivity.this);
                builder.setMessage("삭제하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MenuDetailActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("아니오",null);
                builder.create().show();

            }
        });

        Complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "수정되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });


    }
}