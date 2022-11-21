package com.example.ui2_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;



public class LoginActivity extends AppCompatActivity {

    private EditText id, pw;
    private Button login;
    private RadioButton autologin;
    private TextView join, search_id, search_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        id = findViewById(R.id.LoginId);
        pw = findViewById(R.id.LoginPw);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strID = id.getText().toString();
                String strPW = pw.getText().toString();
                if (strID.replace(" ", "").equals("") || //공백 체크
                        strPW.replace(" ", "").equals("")) {
                    Toast.makeText(getApplicationContext(), "빈칸을 확인해 주세요", Toast.LENGTH_SHORT).show();
                } else {
                    login(strID, strPW);
                }
            }
        }); // 로그인 리스너

        join = findViewById(R.id.signup);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        }); // 회원가입 리스너


        search_id = findViewById(R.id.searchid);

        search_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FindAccountActivity.class);
                startActivity(intent);
            }
        }); // 아이디찾기 리스너

        search_pw = findViewById(R.id.searchpw);

        search_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FindPwActivity.class);
                startActivity(intent);
            }
        }); // 비밀번호찾기 리스너

    }


    private void login(String id, String pw) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //http 요청 시 필요한 url 주소를 변수 선언
        String totalUrl = "";
        String UrlData = "http://165.229.86.152:8293/app/auth/login";
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
        int responseCode = 0;

        String data = "{ \"id\" : \"" + id + "\", \"password\" :\"" + pw + "\" }";


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
            try (OutputStream os = conn.getOutputStream()) {
                byte request_data[] = data.getBytes("utf-8");
                os.write(request_data);
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //http 요청 실시
            conn.connect();
            System.out.println("http 요청 방식 : " + "POST BODY JSON");
            System.out.println("http 요청 타입 : " + "application/json");
            System.out.println("http 요청 주소 : " + UrlData);
            System.out.println("http 요청 데이터 : " + data);
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
            System.out.println("http 응답 코드 : " + responseCode);
            System.out.println("http 응답 데이터 : " + returnData);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (responseCode == 200) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
                menulist();
            } else {
                Toast.makeText(LoginActivity.this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
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


    private void menulist() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

//        StoreItem item = (StoreItem) adapter.getItem(position);

        //http 요청 시 url 주소와 파라미터 데이터를 결합하기 위한 변수 선언
        //http 요청 시 필요한 url 주소를 변수 선언
        String totalUrl = "";
//        String UrlData = "http://165.229.86.152:8293/app/store/" + item.getStoreId() + "/menu/list";
        String UrlData = "http://165.229.86.152:8293/app/store/1/menu/list"; //스토어 id 1 메뉴리스트

        totalUrl = UrlData.trim().toString();

        int responseCode = 0;

        //http 통신을 하기위한 객체 선언 실시
        URL url = null;
        HttpURLConnection conn = null;

        //http 통신 요청 후 응답 받은 데이터를 담기 위한 변수
        String responseData = "";
        BufferedReader br = null;
        StringBuffer sb = null;

        //메소드 호출 결과값을 반환하기 위한 변수
        String returnData = "";

        try {
            //파라미터로 들어온 url을 사용해 connection 실시
            url = new URL(totalUrl);
            conn = (HttpURLConnection) url.openConnection();

            //http 요청에 필요한 타입 정의 실시
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod("GET");

            //http 요청 실시
            conn.connect();
            System.out.println("http 요청 방식 : " + "GET");
            System.out.println("http 요청 타입 : " + "application/json");
            System.out.println("http 요청 주소 : " + UrlData);
            System.out.println("");

            //http 요청 후 응답 받은 데이터를 버퍼에 쌓는다
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            sb = new StringBuffer();
            while ((responseData = br.readLine()) != null) {
                sb.append(responseData); //StringBuffer에 응답받은 데이터 순차적으로 저장 실시
            }

            //메소드 호출 완료 시 반환하는 변수에 버퍼 데이터 삽입 실시
            returnData = sb.toString();

            //returnData를 json형식으로
            ArrayList<MenuItem> list = new ArrayList<MenuItem>();

            try {
                JSONArray jsonArray = new JSONArray(returnData);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject JsonObject = jsonArray.getJSONObject(i);
                    String menuId = JsonObject.getString("menuId");
                    String price = JsonObject.getString("price");
                    String menuName = JsonObject.getString("menuName");
                    String options = JsonObject.getString("options");
                    String description = JsonObject.getString("description");


                    System.out.println("menuId: " + menuId +
                            "menuName: " + menuName + "\n");

                    list.add(new MenuItem(menuId, price, menuName, options, description));
                }

//                System.out.println("main에서 출력 : " + list.get(0).getMenuName());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("length", Integer.toString(jsonArray.length()));
                intent.putExtra("list", list);
                startActivity(intent);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            //http 요청 응답 코드 확인 실시
            responseCode = conn.getResponseCode();
            System.out.println("http 응답 코드 : " + responseCode);
            System.out.println("http 응답 데이터 : " + returnData);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //http 요청 및 응답 완료 후 BufferedReader를 닫아줍니다
            try {
                if (br != null) {
                    br.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//                StoreItem item = (StoreItem) adapter.getItem(position);
//                Intent intent=new Intent(StoreListActivity.this,  StoreDetailActivity.class);
//                intent.putExtra("store_name",item.getName());
//                startActivity(intent);


    }

}