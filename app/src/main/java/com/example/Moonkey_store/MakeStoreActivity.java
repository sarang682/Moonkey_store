package com.example.Moonkey_store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.Moonkey_store.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MakeStoreActivity extends AppCompatActivity {

    private String name, address, category, description, contact, num, bussiness;
    private Button approval;
    private EditText Input_contact, strname, descript, store_address, corporate_num, business_conditions;
    private Spinner spinner;
    private String[] items ={"햄버거","할인제품","한식","피자","카페","치킨","중식","일식","양식","아이스크림",
                    "생필품","샐러드","분식", "샐러드", "냉동식품", "가전제품"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_make_store);

        Intent intent = getIntent();
        String token=intent.getStringExtra("token");

        Input_contact=findViewById(R.id.Input_contact);
        strname=findViewById(R.id.Input_business_name);
        descript=findViewById(R.id.description);
        store_address=findViewById(R.id.store_address);
        corporate_num=findViewById(R.id.corporate_num);
        business_conditions=findViewById(R.id.business_conditions);
        spinner=findViewById(R.id.spinner_category);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item, items );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item );
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category = items[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                category=null;
            }
        });

        approval=findViewById(R.id.wait_approval_btn);
        approval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=strname.getText().toString();
                address=store_address.getText().toString();
                description=descript.getText().toString();
                contact=Input_contact.getText().toString();
                num=corporate_num.getText().toString();
                bussiness=business_conditions.getText().toString();

                if (!nullCheck(name) || !nullCheck(address) || !nullCheck(contact) || !nullCheck(num) || !nullCheck(bussiness)) {
                    Toast.makeText(getApplicationContext(), "빈칸을 확인해 주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    makeStore(token, name, address, description, contact, category);
                }
//                Toast.makeText(getApplicationContext(), "입점 신청되었습니다.", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MakeStoreActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        });

    }

    //널 체크
    public boolean nullCheck(String string) {
        if (string.replace(" ", "").equals(""))
            return false;
        else return true;
    }

    private void makeStore(String token, String name, String addr, String descript, String contact, String category){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //http 요청 시 필요한 url 주소를 변수 선언
        String totalUrl = "";
        String UrlData = "http://165.229.86.152:8293/app/store/reg";
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

        String data="{ \"name\" : \"" +name+ "\", \"address\" :\""+addr+"\" ,\"description\" : \""+descript+"\", " +
                "\"contact\" : \""+contact+"\", \"category\" : \""+category+"\"}";


        try {
            //파라미터로 들어온 url을 사용해 connection 실시
            url = new URL(totalUrl);
            conn = (HttpURLConnection) url.openConnection();

            //http 요청에 필요한 타입 정의 실시
            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json"); //post body json으로 던지기 위함
            conn.setRequestProperty("Content-Type", "application/json; utf-8"); //post body json으로 던지기 위함
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization",token);
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
                Toast.makeText(getApplicationContext(), "입점 신청되었습니다.", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(getApplicationContext(), "입점 신청에 실패하였습니다.", Toast.LENGTH_SHORT).show();
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