package com.example.Moonkey_store;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MenuDetailActivity extends AppCompatActivity {

    private TextView Contents;
    private ImageView Image;
    private EditText Name, Price, Comment, OptName, OptPrice;
    private Button File, OptAdd, OptDelete, Delete, Complete;
    private String price, name, comment, storeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_productinfo);

//        int price = getIntent().getIntExtra("price",0);
        price = getIntent().getStringExtra("price");
        name = getIntent().getStringExtra("name");
        comment = getIntent().getStringExtra("comment");
        storeId = getIntent().getStringExtra("storeId");

        Name = findViewById(R.id.modify_menu_name);
        Price = findViewById(R.id.modify_menu_cost);
        Comment = findViewById(R.id.modify_menu_explan);
        OptName = findViewById(R.id.optionname);
        OptPrice = findViewById(R.id.optioncost);

        File = findViewById(R.id.attached_file_btn);
        OptAdd = findViewById(R.id.btnAdd);
        OptDelete = findViewById(R.id.btnDelete);
        Delete = findViewById(R.id.delete_menu);
        Complete = findViewById(R.id.complete);

        Price.setText(price);
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
                        getmenuId(storeId, name);
//                        deleteMenu(name);
                        Toast.makeText(MenuDetailActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("아니오", null);
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

    private void getmenuId(String storeId, String menuname) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //http 요청 시 url 주소와 파라미터 데이터를 결합하기 위한 변수 선언
        //http 요청 시 필요한 url 주소를 변수 선언
        String totalUrl = "";
        String UrlData = "http://165.229.86.152:8293/app/store/"+storeId+"/menu/list"; // 가게별 리뷰리스트
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
//            ArrayList list = new ArrayList();
            String ID="";

            try {
                JSONArray jsonArray = new JSONArray(returnData);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject JsonObject = jsonArray.getJSONObject(i);
                    String menuId = JsonObject.getString("menuId");
                    String menuName = JsonObject.getString("menuName");

                    if(menuName.equals(menuname)){
//                        list.add(menuId);
                        ID = menuId;
                    }
                }
                if(!ID.equals("")){
                    deleteMenu(storeId, ID);
                }
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
    }

    private void deleteMenu(String storeId, String menuId) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //http 요청 시 필요한 url 주소를 변수 선언
        String totalUrl = "";
        String UrlData = "http://165.229.86.152:8293/app/store/"+storeId+"/menu/unreg/"+menuId+"";
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

//        String data="{ \"id\" : \"" +id+ "\", \"password\" :\""+password+"\" ,\"phone\" : \""+phone+"\", \"addr\" : \""+addr+"\", \"nickname\" : \""+nickname+"\" , \"flag\" : 1 }";


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
//            try (OutputStream os = conn.getOutputStream()){
//                byte request_data[] = data.getBytes("utf-8");
//                os.write(request_data);
//                os.close();
//            }
//            catch(Exception e) {
//                e.printStackTrace();
//            }

            //http 요청 실시
            conn.connect();
            System.out.println("http 요청 방식 : "+"POST BODY JSON");
            System.out.println("http 요청 타입 : "+"application/json");
            System.out.println("http 요청 주소 : "+UrlData);
//            System.out.println("http 요청 데이터 : "+data);
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
                Toast.makeText (MenuDetailActivity.this, "상품 삭제에 성공공하였습니다.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText (MenuDetailActivity.this, "상품 삭제에 실패하였습니다.", Toast.LENGTH_SHORT).show();
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
}