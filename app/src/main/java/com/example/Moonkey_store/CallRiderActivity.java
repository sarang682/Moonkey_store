package com.example.Moonkey_store;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallRiderActivity extends AppCompatActivity {

    private TextView tv_pay, addr, total, storename, storeaddr,tv_distance;
    private Button searchrider;
    private StoreItem storeInfo;
    private OrderItem orderItem;
    private EditText edt_requested;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_type);

        Intent intent = getIntent();
        orderItem= (OrderItem) intent.getSerializableExtra("orderItem");

        storeInfo= (StoreItem) intent.getSerializableExtra("storeInfo");


        tv_distance=findViewById(R.id.tv_distance);
        tv_pay=findViewById(R.id.tv_pay);
        addr=findViewById(R.id.address);
        total=findViewById(R.id.total);
        storename=findViewById(R.id.store_name);
        storeaddr=findViewById(R.id.store_address);
        searchrider=findViewById(R.id.search_rider);
        edt_requested=findViewById(R.id.want_rider_option);

        addr.setText(orderItem.getAddress());
        total.setText(Integer.toString(orderItem.getAmount()));
        storename.setText(storeInfo.getName());
        storeaddr.setText(storeInfo.getAddress());
        //공고 등록
        searchrider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                register(edt_requested.getText().toString());
                Toast.makeText(getApplicationContext(), "등록되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });

    }

//    private void register(String requests){
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//        //http 요청 시 필요한 url 주소를 변수 선언
//        String totalUrl = "";
//        String UrlData = "http://165.229.86.152:8293/app/delivery/reg";
//        totalUrl = UrlData.trim().toString();
//
//        //http 통신을 하기위한 객체 선언 실시
//        URL url = null;
//        HttpURLConnection conn = null;
//
//        //http 통신 요청 후 응답 받은 데이터를 담기 위한 변수
//        String responseData = "";
//        BufferedReader br = null;
//        StringBuffer sb = null;
//
//        //메소드 호출 결과값을 반환하기 위한 변수
//        String returnData = "";
//        int responseCode=0;
//
//        String data="{ \"address\" : \"" +orderItem.getAddress()+ "\", \"distance\" :\""+8000+"\" ,\"pay\" : \""+3000+"\", " +
//                "\"requests\" : \""+requests+"\", \"orderId\" : \""+1+"\",\"store_id\":\""+storeInfo.getStoreId()+"\",\"uid\":\""+1+"\",\"totalPay\":\""+orderItem.getAmount()+"\"}";
//
//
//        try {
//            //파라미터로 들어온 url을 사용해 connection 실시
//            url = new URL(totalUrl);
//            conn = (HttpURLConnection) url.openConnection();
//
//            //http 요청에 필요한 타입 정의 실시
//            conn.setRequestMethod("POST");
////            conn.setRequestProperty("Content-Type", "application/json"); //post body json으로 던지기 위함
//            conn.setRequestProperty("Content-Type", "application/json; utf-8"); //post body json으로 던지기 위함
//            conn.setRequestProperty("Accept", "application/json");
//            conn.setRequestProperty("Authorization",token);
//            conn.setDoOutput(true); //OutputStream을 사용해서 post body 데이터 전송
//            try (OutputStream os = conn.getOutputStream()){
//                byte request_data[] = data.getBytes("utf-8");
//                os.write(request_data);
//                os.close();
//            }
//            catch(Exception e) {
//                e.printStackTrace();
//            }
//
//            //http 요청 실시
//            conn.connect();
//            System.out.println("http 요청 방식 : "+"POST BODY JSON");
//            System.out.println("http 요청 타입 : "+"application/json");
//            System.out.println("http 요청 주소 : "+UrlData);
//            System.out.println("http 요청 데이터 : "+data);
//            System.out.println("");
//
//            //http 요청 후 응답 받은 데이터를 버퍼에 쌓는다
//            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//            sb = new StringBuffer();
//            while ((responseData = br.readLine()) != null) {
//                sb.append(responseData); //StringBuffer에 응답받은 데이터 순차적으로 저장 실시
//            }
//
//            //메소드 호출 완료 시 반환하는 변수에 버퍼 데이터 삽입 실시
//            returnData = sb.toString();
//
//            //http 요청 응답 코드 확인 실시
////            String responseCode = String.valueOf(conn.getResponseCode());
//            responseCode = conn.getResponseCode();
//            System.out.println("http 응답 코드 : "+responseCode);
//            System.out.println("http 응답 데이터 : "+returnData);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if(responseCode==200){
//                Toast.makeText(getApplicationContext(), "입점 신청되었습니다.", Toast.LENGTH_SHORT).show();
//
//            }else{
//                Toast.makeText(getApplicationContext(), "입점 신청에 실패하였습니다.", Toast.LENGTH_SHORT).show();
//            }
//            //http 요청 및 응답 완료 후 BufferedReader를 닫아줍니다
//            try {
//                if (br != null) {
//                    br.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
