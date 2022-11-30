package com.example.Moonkey_store;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditAccountActivity extends AppCompatActivity {

    private EditText nickname, phonenum, current_pw, new_pw, new_pwconfirm;
    private Button modify_nickname, modify_phonenum, modify_pw, unregister;
    private TextView ID;
    private String uid, token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_account_modify);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nick = intent.getStringExtra("nick");
        String phone = intent.getStringExtra("phone");
//        uid = intent.getStringExtra("uid");
        token = intent.getStringExtra("token");

        ID=findViewById(R.id.id);
        nickname=findViewById(R.id.modify_nickname);
        phonenum=findViewById(R.id.modify_phoneNum);
        modify_nickname=findViewById(R.id.modify_name_btn);
        modify_phonenum=findViewById(R.id.modify_phoneNum_btn);

        current_pw=findViewById(R.id.current_pw);
        new_pw=findViewById(R.id.new_pw);
        new_pwconfirm=findViewById(R.id.new_pwconfirm);
        modify_pw=findViewById(R.id.modify_pw_btn);
        unregister=findViewById(R.id.unregister_btn);

        ID.setText(id);
        nickname.setText(nick);
        phonenum.setText(phone);


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

        //비밀번호 변경
        modify_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!check_new_pw(new_pw,new_pwconfirm)){
                    Toast.makeText(EditAccountActivity.this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditAccountActivity.this, "비밀번호 변경이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                }
                
            }
        });

        unregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditAccountActivity.this, UnregisterActivity.class);
//                intent.putExtra("uid",uid);
                intent.putExtra("token",token);
                startActivity(intent);

            }
        });


    }

    public boolean check_new_pw(EditText pw,EditText pw_confirm){
        if(!pw.getText().toString().equals(pw_confirm.toString())){
            return false;
        }
        return true;
    }
}