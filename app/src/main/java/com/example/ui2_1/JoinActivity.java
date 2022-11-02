package com.example.ui2_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JoinActivity extends AppCompatActivity {

    EditText ID, PW, PW_check, Name, NickName, Phone;
    Button id_check, complete;
    CheckBox push_check, inform_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        ID=findViewById(R.id.InputId);
        PW=findViewById(R.id.InputPw);
        PW_check=findViewById(R.id.InputConfirmPw);
        Name=findViewById(R.id.InputName);
        NickName=findViewById(R.id.Inputnickname);
        Phone=findViewById(R.id.PhoneNumber);
        inform_check=findViewById(R.id.inform_check);
        push_check=findViewById(R.id.push_check);

//        id_check=findViewById(R.id.id_check);
//        //아이디확인 리스너
//        id_check.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });

        complete=findViewById(R.id.Joinbtn);
        //완료 리스너
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!nullCheck(ID) || !nullCheck(PW) || !nullCheck(NickName)||!nullCheck(Phone)||!nullCheck(Name)) {
                    Toast.makeText(getApplicationContext(), "빈칸을 확인해 주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!pw_check()) { // pw 확인
                    } else {
                        if (id_duplicate_check() == false) {
                            Toast.makeText(getApplicationContext(), "이미 존재하는 ID입니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            if (nick_duplicate_check() == false) {
                                Toast.makeText(getApplicationContext(), "이미 존재하는 닉네임입니다.", Toast.LENGTH_SHORT).show();
                            }else{
                                if(phone_duplicate_check()==false){
                                    Toast.makeText(getApplicationContext(), "이미 가입된 전화번호입니다.", Toast.LENGTH_SHORT).show();
                                }else{
                                    if(!inform_check.isChecked()){
                                        Toast.makeText(getApplicationContext(), "이용약관 및 사용자 정보제공에 동의하셔야합니다.", Toast.LENGTH_SHORT).show();
                                    }else{
                                        join(); // signup success
                                        Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            }

                        }
                    }
                }
            }
        });

    }


    // id 중복확인 함수
    public boolean id_duplicate_check() {
        return true;
    }

    // 닉네임 중복확인 함수
    public boolean nick_duplicate_check() {
        return true;
    }

    // 전화번호 중복확인 함수
    public boolean phone_duplicate_check() {
        return true;
    }

    // pw 확인 함수
    public boolean pw_check() {
        String str_Pw=PW.getText().toString();
        String pattern1 = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$";
        Matcher match;
        match = Pattern.compile(pattern1).matcher(str_Pw);
        boolean chk = false;

        if(match.find()) {
            chk = true;
        }

        if(str_Pw.equals(PW_check.getText().toString())){
            if(chk) return true;
            else {
                Toast.makeText(getApplicationContext(), "비밀번호는 8자 이상의 숫자와 영문, 특수문자 조합이어야 합니다.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else {
            Toast.makeText(getApplicationContext(), "비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    //널 체크
    public boolean nullCheck(EditText editText) {
        if (editText.getText().toString().replace(" ", "").equals(""))
            return false;
        else return true;
    }

    private void join() {

    }


}
