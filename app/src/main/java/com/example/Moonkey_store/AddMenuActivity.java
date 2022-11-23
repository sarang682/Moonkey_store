package com.example.Moonkey_store;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddMenuActivity extends AppCompatActivity {

    EditText tv_menu,tv_price,tv_explain;
    EditText tv_option,tv_option_price;
    Button btn_img, btn_add_option, btn_del_option, btn_add;
    Uri uri;
    Bitmap bitmap;
    TextView tv_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_menu);

        tv_menu=findViewById(R.id.make_menu_name);
        tv_price=findViewById(R.id.make_menu_cost);
        tv_explain=findViewById(R.id.modify_menu_explan);

        tv_option=findViewById(R.id.newitemname);
        tv_option_price=findViewById(R.id.newitemcost);

        btn_img=findViewById(R.id.attached_file_btn);
        btn_add_option=findViewById(R.id.btnAdd);
        btn_del_option=findViewById(R.id.btnDelete);
        btn_add=findViewById(R.id.complete);

        tv_img=findViewById(R.id.attached_menu_picture);
        //상품등록
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String menuName=tv_menu.getText().toString();
                String options=tv_option.getText().toString();
                String price=tv_price.getText().toString();
                String description=tv_explain.getText().toString();

                if(isNull(tv_menu)||isNull(tv_price)){
                    Toast.makeText(getApplicationContext(), "빈칸을 확인해 주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    addMenu(menuName, options, price, description);
                }
            }
        });

        //사진찾기
        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityResult.launch(intent);
            }
        });
    }

    //사진선택
    ActivityResultLauncher<Intent> startActivityResult=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==RESULT_OK&&result.getData()!=null){
                        uri=result.getData().getData();
                        tv_img.setText(uri.toString());
                        try{
                            bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                        }catch (FileNotFoundException e){
                            e.printStackTrace();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    //널 체크
    public boolean isNull(EditText editText) {
        if (editText.getText().toString().replace(" ", "").equals(""))
            return true;
        else return false;
    }

    private void addMenu(String menuName, String options, String price, String description) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //http 요청 시 필요한 url 주소를 변수 선언
        String totalUrl = "";
        String UrlData = "http://165.229.86.152:8293/app/store/1/menu/reg"; //storeid 1 메뉴 추가
//        String UrlData = "http://165.229.86.152:8293/app/store/" + {storeId} + "/menu/reg\n";

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

        String data="{ \"menuName\" : \"" +menuName+ "\", \"options\" :\""+options+"\" ,\"price\" : \""+price+"\", \"description\" : \""+description+"\"}";


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
                Toast.makeText (AddMenuActivity.this, "상품 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(AddMenuActivity.this, MainActivity.class);
//                startActivity(intent);
            }else{
                Toast.makeText (AddMenuActivity.this, "상품 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
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