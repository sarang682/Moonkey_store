package com.example.Moonkey_store;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JoinActivity extends AppCompatActivity {

    EditText ID, PW, PW_check, Addr, NickName, Phone;
    Button id_check, complete;
    CheckBox push_check, inform_check;

//    private static String IP_ADDRESS = "http://165.229.86.152:9501";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        ID=findViewById(R.id.InputId);
        PW=findViewById(R.id.InputPw);
        PW_check=findViewById(R.id.InputConfirmPw);
        Addr=findViewById(R.id.InputAddr);
        NickName=findViewById(R.id.Inputnickname);
        Phone=findViewById(R.id.PhoneNumber);
        inform_check=findViewById(R.id.inform_check);
        push_check=findViewById(R.id.push_check);

//        id_check=findViewById(R.id.id_check);
//        //아이디확인 리스너
//        id_check.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "사용 가능한 ID입니다.", Toast.LENGTH_SHORT).show();
//                if (id_duplicate_check() == false) {
//                    Toast.makeText(getApplicationContext(), "이미 존재하는 ID입니다.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        complete=findViewById(R.id.Joinbtn);
        //완료 리스너
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id=ID.getText().toString();
                String pw=PW.getText().toString();
                String phone=Phone.getText().toString();
                String addr=Addr.getText().toString();
                String nickname=NickName.getText().toString();


                if (!nullCheck(ID) || !nullCheck(PW) || !nullCheck(NickName)||!nullCheck(Phone)||!nullCheck(Addr)) {
                    Toast.makeText(getApplicationContext(), "빈칸을 확인해 주세요.", Toast.LENGTH_SHORT).show();
                } else {
//                    if (!pw_check()) { // pw 확인
//                    } else {
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

//                                         join();
                                    join(id,pw,phone,addr,nickname,1);
//                                    Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }

                    }
//                    }
                }
            }
        });

    }


    // id 중복확인 함수
    public boolean id_duplicate_check() {
        String str_Id=ID.getText().toString();

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

    //    /*
    private void join(String id, String password, String phone, String addr, String nickname,int flag) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //http 요청 시 필요한 url 주소를 변수 선언
        String totalUrl = "";
        String UrlData = "http://165.229.86.152:8293/app/usr/reg";
        totalUrl = UrlData.trim().toString();

        //http 통신을 하기위한 객체 선언 실시
        URL url = null;
        HttpURLConnection conn = null;

        //http 통신 요청 후 응답 받은 데이터를 담기 위한 변수
        String responseData = "";
        BufferedReader br = null;
        StringBuffer sb = null;

        //메소드 호출 결과값을 반환하기 위한 변수
        String returnData = "";
        int responseCode=0;

        String data="{ \"id\" : \"" +id+ "\", \"password\" :\""+password+"\" ,\"phone\" : \""+phone+"\", \"addr\" : \""+addr+"\", \"nickname\" : \""+nickname+"\" , \"flag\" : 1 }";


        try {
            //파라미터로 들어온 url을 사용해 connection 실시
            url = new URL(totalUrl);
            conn = (HttpURLConnection) url.openConnection();

            //http 요청에 필요한 타입 정의 실시
            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json"); //post body json으로 던지기 위함
            conn.setRequestProperty("Content-Type", "application/json; utf-8"); //post body json으로 던지기 위함
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true); //OutputStream을 사용해서 post body 데이터 전송
            try (OutputStream os = conn.getOutputStream()){
                byte request_data[] = data.getBytes("utf-8");
                os.write(request_data);
                os.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }

            //http 요청 실시
            conn.connect();
            System.out.println("http 요청 방식 : "+"POST BODY JSON");
            System.out.println("http 요청 타입 : "+"application/json");
            System.out.println("http 요청 주소 : "+UrlData);
            System.out.println("http 요청 데이터 : "+data);
            System.out.println("");

            //http 요청 후 응답 받은 데이터를 버퍼에 쌓는다
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            sb = new StringBuffer();
            while ((responseData = br.readLine()) != null) {
                sb.append(responseData); //StringBuffer에 응답받은 데이터 순차적으로 저장 실시
            }

            //메소드 호출 완료 시 반환하는 변수에 버퍼 데이터 삽입 실시
            returnData = sb.toString();

            //http 요청 응답 코드 확인 실시
//            String responseCode = String.valueOf(conn.getResponseCode());
            responseCode = conn.getResponseCode();
            System.out.println("http 응답 코드 : "+responseCode);
            System.out.println("http 응답 데이터 : "+returnData);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(responseCode==200){
                Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText (JoinActivity.this, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
            //http 요청 및 응답 완료 후 BufferedReader를 닫아줍니다
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* 연결확인 app/helloAccount
        private void join(String id, String password, String phone, String addr, String nickname,int flag){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url;
        HttpURLConnection connection = null;

        try {
            url = new URL("http://165.229.86.152:9501/app/helloAccount");
            connection = (HttpURLConnection) url.openConnection();
            Log.d("status code",Integer.toString(connection.getResponseCode()));
            Toast.makeText (JoinActivity.this, "응답 결과 : " + connection.getResponseCode(), Toast.LENGTH_SHORT).show();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            char[] buff = new char[512];
            int len = -1;

            while( (len = br.read(buff)) != -1) {
                System.out.print(new String(buff, 0, len));
                Log.d("response body", new String(buff, 0, len));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

     */

}