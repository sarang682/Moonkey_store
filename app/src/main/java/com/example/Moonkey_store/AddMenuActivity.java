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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AddMenuActivity extends AppCompatActivity {

    EditText tv_menu, tv_price, tv_explain;
    EditText tv_option, tv_option_price;
    Button btn_img, btn_add_option, btn_del_option, btn_add;
    Uri uri;
    Bitmap bitmap;
    TextView tv_img;
    ArrayList<AccountItem> acclist;
    ArrayList<StoreItem> storelist;
    ArrayList<MenuItem> menulist;
    String token, storeId, menulength;
    String menuName, options, price, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_menu);

        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        storeId = intent.getStringExtra("storeId");
        acclist = (ArrayList<AccountItem>) intent.getSerializableExtra("acclist");
        storelist = (ArrayList<StoreItem>) intent.getSerializableExtra("storelist");
        menulength = intent.getStringExtra("menulength");
        menulist = (ArrayList<MenuItem>) intent.getSerializableExtra("menulist");

        tv_menu = findViewById(R.id.make_menu_name);
        tv_price = findViewById(R.id.make_menu_cost);
        tv_explain = findViewById(R.id.modify_menu_explan);

        tv_option = findViewById(R.id.newitemname);
        tv_option_price = findViewById(R.id.newitemcost);

        btn_img = findViewById(R.id.attached_file_btn);
        btn_add_option = findViewById(R.id.btnAdd);
        btn_del_option = findViewById(R.id.btnDelete);
        btn_add = findViewById(R.id.complete);

        tv_img = findViewById(R.id.attached_menu_picture);
        //????????????
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 menuName = tv_menu.getText().toString();
                 options = tv_option.getText().toString();
                 price = tv_price.getText().toString();
                 description = tv_explain.getText().toString();

                if (isNull(tv_menu) || isNull(tv_price)) {
                    Toast.makeText(getApplicationContext(), "????????? ????????? ?????????.", Toast.LENGTH_SHORT).show();
                } else {
                    addMenu();
                }
            }
        });

        //????????????
        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityResult.launch(intent);
            }
        });
    }

    //????????????
    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        uri = result.getData().getData();
                        tv_img.setText(uri.toString());
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    //??? ??????
    public boolean isNull(EditText editText) {
        if (editText.getText().toString().replace(" ", "").equals(""))
            return true;
        else return false;
    }

    private void addMenu() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //http ?????? ??? ????????? url ????????? ?????? ??????
        String totalUrl = "";
        String UrlData = "http://165.229.86.152:8293/app/store/" + storeId + "/menu/reg";

        totalUrl = UrlData.trim().toString();

        //http ????????? ???????????? ?????? ?????? ??????
        URL url = null;
        HttpURLConnection conn = null;

        //http ?????? ?????? ??? ?????? ?????? ???????????? ?????? ?????? ??????
        String responseData = "";
        BufferedReader br = null;
        StringBuffer sb = null;

        //????????? ?????? ???????????? ???????????? ?????? ??????
        String returnData = "";
        int responseCode = 0;

        String data = "{ \"menuName\" : \"" + menuName + "\", \"options\" :\"" + options + "\" ,\"price\" : \"" + price + "\", \"description\" : \"" + description + "\"}";


        try {
            //??????????????? ????????? url??? ????????? connection ??????
            url = new URL(totalUrl);
            conn = (HttpURLConnection) url.openConnection();

            //http ????????? ????????? ?????? ?????? ??????
            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json"); //post body json?????? ????????? ??????
            conn.setRequestProperty("Content-Type", "application/json; utf-8"); //post body json?????? ????????? ??????
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true); //OutputStream??? ???????????? post body ????????? ??????
            try (OutputStream os = conn.getOutputStream()) {
                byte request_data[] = data.getBytes("utf-8");
                os.write(request_data);
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //http ?????? ??????
            conn.connect();
            System.out.println("http ?????? ?????? : " + "POST BODY JSON");
            System.out.println("http ?????? ?????? : " + "application/json");
            System.out.println("http ?????? ?????? : " + UrlData);
            System.out.println("http ?????? ????????? : " + data);
            System.out.println("");

            //http ?????? ??? ?????? ?????? ???????????? ????????? ?????????
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            sb = new StringBuffer();
            while ((responseData = br.readLine()) != null) {
                sb.append(responseData); //StringBuffer??? ???????????? ????????? ??????????????? ?????? ??????
            }

            //????????? ?????? ?????? ??? ???????????? ????????? ?????? ????????? ?????? ??????
            returnData = sb.toString();

            //http ?????? ?????? ?????? ?????? ??????
//            String responseCode = String.valueOf(conn.getResponseCode());
            responseCode = conn.getResponseCode();
            System.out.println("http ?????? ?????? : " + responseCode);
            System.out.println("http ?????? ????????? : " + returnData);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (responseCode == 200) {
                menulist();
                Toast.makeText(AddMenuActivity.this, "?????? ????????? ?????????????????????.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddMenuActivity.this, "?????? ????????? ?????????????????????.", Toast.LENGTH_SHORT).show();
            }
            //http ?????? ??? ?????? ?????? ??? BufferedReader??? ???????????????
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

        //http ?????? ??? url ????????? ???????????? ???????????? ???????????? ?????? ?????? ??????
        //http ?????? ??? ????????? url ????????? ?????? ??????
        String totalUrl = "";
        String UrlData = "http://165.229.86.152:8293/app/store/" + storeId + "/menu/list"; //?????????????????? ???????????????

        totalUrl = UrlData.trim().toString();

        int responseCode = 0;

        //http ????????? ???????????? ?????? ?????? ??????
        URL url = null;
        HttpURLConnection conn = null;

        //http ?????? ?????? ??? ?????? ?????? ???????????? ?????? ?????? ??????
        String responseData = "";
        BufferedReader br = null;
        StringBuffer sb = null;

        //????????? ?????? ???????????? ???????????? ?????? ??????
        String returnData = "";

        try {
            //??????????????? ????????? url??? ????????? connection ??????
            url = new URL(totalUrl);
            conn = (HttpURLConnection) url.openConnection();

            //http ????????? ????????? ?????? ?????? ??????
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod("GET");

            //http ?????? ??????
            conn.connect();
            System.out.println("http ?????? ?????? : " + "GET");
            System.out.println("http ?????? ?????? : " + "application/json");
            System.out.println("http ?????? ?????? : " + UrlData);
            System.out.println("");

            //http ?????? ??? ?????? ?????? ???????????? ????????? ?????????
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            sb = new StringBuffer();
            while ((responseData = br.readLine()) != null) {
                sb.append(responseData); //StringBuffer??? ???????????? ????????? ??????????????? ?????? ??????
            }

            //????????? ?????? ?????? ??? ???????????? ????????? ?????? ????????? ?????? ??????
            returnData = sb.toString();

            //returnData??? json????????????
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

//                    System.out.println("menuId: " + menuId +
//                            "menuName: " + menuName + "\n");

                    list.add(new MenuItem(menuId, price, menuName, options, description));
                }

//                System.out.println("main?????? ?????? : " + list.get(0).getMenuName());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("menulength", Integer.toString(jsonArray.length()));
                intent.putExtra("acclist", acclist);
                intent.putExtra("storelist", storelist);
                intent.putExtra("menulist", list);
                intent.putExtra("token", token);
                startActivity(intent);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //http ?????? ?????? ?????? ?????? ??????
            responseCode = conn.getResponseCode();
            System.out.println("http ?????? ?????? : " + responseCode);
            System.out.println("http ?????? ????????? : " + returnData);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //http ?????? ??? ?????? ?????? ??? BufferedReader??? ???????????????
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