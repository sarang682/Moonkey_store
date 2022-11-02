package com.example.ui2_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ui2_1.MainActivity;
import com.example.ui2_1.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    private EditText id ,pw;
    private Button login;
    private RadioButton autologin;
    private TextView join, search_id, search_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id=findViewById(R.id.LoginId);
        pw=findViewById(R.id.LoginPw);

        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strID=id.getText().toString();
                String strPW=pw.getText().toString();
                if (strID.replace(" ", "").equals("") || //공백 체크
                        strPW.replace(" ", "").equals("")) {
                    Toast.makeText(getApplicationContext(), "빈칸을 확인해 주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    login(strID,strPW);
                }
            }
        }); // 로그인 리스너

        join=findViewById(R.id.signup);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
//                startActivity(intent);
            }
        }); // 회원가입 리스너


        search_id=findViewById(R.id.searchid);

        search_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FindAccountActivity.class);
                startActivity(intent);
            }
        }); // 아이디찾기 리스너

        search_pw=findViewById(R.id.searchpw);

        search_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FindPwActivity.class);
                startActivity(intent);
            }
        }); // 비밀번호찾기 리스너

    }

    public void login(String id,String pw){

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

}