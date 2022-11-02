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

public class EditAccountActivity extends AppCompatActivity {

    private EditText nickname, phonenum, current_pw, new_pw, new_pwconfirm;
    private Button modify_nickname, modify_phonenum, modify_pw, unregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_account_modify);

        nickname=findViewById(R.id.modify_nickname);
        phonenum=findViewById(R.id.modify_phoneNum);
        modify_nickname=findViewById(R.id.modify_name_btn);
        modify_phonenum=findViewById(R.id.modify_phoneNum_btn);

        current_pw=findViewById(R.id.current_pw);
        new_pw=findViewById(R.id.new_pw);
        new_pwconfirm=findViewById(R.id.new_pwconfirm);
        modify_pw=findViewById(R.id.modify_pw_btn);
        unregister=findViewById(R.id.unregister_btn);


        modify_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditAccountActivity.this, "닉네임 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        modify_phonenum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditAccountActivity.this, "휴대폰번호 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        modify_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditAccountActivity.this, "비밀번호 변경이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        unregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditAccountActivity.this);
                builder.setMessage("탈퇴하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(EditAccountActivity.this, "회원 탈퇴가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("아니오",null);
                builder.create().show();

            }
        });


    }
}