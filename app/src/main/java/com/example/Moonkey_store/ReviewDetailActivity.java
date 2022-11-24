package com.example.Moonkey_store;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReviewDetailActivity extends AppCompatActivity {

    private TextView Star, Contents;
    private ImageView Image;
    private EditText Comment;
    private Button Delete, Complete;
    private ListView comment_lv;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        String score = getIntent().getStringExtra("score");
        String content = getIntent().getStringExtra("content");

        Star=findViewById(R.id.review_star);
        Image=findViewById(R.id.review_img);
        Contents=findViewById(R.id.cus_menu_review);
        Comment=findViewById(R.id.comment);
        Delete=findViewById(R.id.delete_review);
        Complete=findViewById(R.id.comment_complete);

        Star.setText(score);
        Contents.setText(content);


        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ReviewDetailActivity.this);
                builder.setMessage("삭제하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ReviewDetailActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("아니오",null);
                builder.create().show();

            }
        });

        Complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "등록되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        /*
        //댓글 리스트뷰
        comment_lv=findViewById(R.id.comment_lv);
        items=new ArrayList<String>();
        items.add("리뷰 감사합니다^^");

        Complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.add(Comment.getText().toString());
                adapter.notifyDataSetChanged();
                Comment.setText("");
            }
        });

        adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,items);
        comment_lv.setAdapter(adapter);

         */
    }


}