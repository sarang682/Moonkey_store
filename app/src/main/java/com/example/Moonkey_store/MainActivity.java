package com.example.Moonkey_store;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView store_name;
    private Button btn_add;
    private ListView listView;
    private MenuAdapter adapter;
    private ArrayList<MenuItem> items = new ArrayList<MenuItem>();
    private NestedScrollView nestedScrollView;
    private LinearLayout linearLayout;

    private ImageView openDrawer, mypage, searchbtn;
    private EditText searchMenu;
    private Button logout;
    private LinearLayout review, orderlist, standing, question, editAccnt;
    private TextView nick;
    private String id, uid, phone, addr, nickname, storeId, strname, straddr, category, contact;
    private String length, token;


    private ArrayList<AccountItem> acclist;
    private ArrayList<StoreItem> strlist;
    private ArrayList<MenuItem> menulist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        Intent intent = getIntent();
        token=intent.getStringExtra("token");


        store_name=findViewById(R.id.tv_address);
        mypage=findViewById(R.id.mypage);
        searchMenu=findViewById(R.id.searchmenu);
        searchbtn=findViewById(R.id.searchbtn);
        btn_add=findViewById(R.id.imageView2);
        listView=findViewById(R.id.listView);
        nestedScrollView=findViewById(R.id.nested_scroll_view);
        nick = findViewById(R.id.id_txt);



        if(intent.hasExtra("acclist")){
            acclist = (ArrayList<AccountItem>) intent.getSerializableExtra("acclist");
            id = acclist.get(0).getId();
            uid = acclist.get(0).getUid();
            phone = acclist.get(0).getPhone();
            addr = acclist.get(0).getAddress();
            nickname = acclist.get(0).getNickname();
        }
        if(intent.hasExtra("storelist")){
            strlist = (ArrayList<StoreItem>) intent.getSerializableExtra("storelist");
            storeId = strlist.get(0).getStoreId();
            strname = strlist.get(0).getName();
            straddr = strlist.get(0).getAddress();
            category = strlist.get(0).getCategory();
            contact = strlist.get(0).getContact();

            store_name.setText(strname);
            if(strname.length()>5){
                store_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            }
        }
        if(intent.hasExtra("menulength")&&intent.hasExtra("menulist")){
            length = intent.getStringExtra("menulength");
            menulist = (ArrayList<MenuItem>) intent.getSerializableExtra("menulist");
            listview(storeId, length,menulist);
        }



        //마이페이지(가게)
        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MyPageActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("nick",nickname);
                intent.putExtra("phone",phone);

                intent.putExtra("strname",strname);
                intent.putExtra("straddr",straddr);
                intent.putExtra("category",category);
                intent.putExtra("contact",contact);
                startActivity(intent);
            }
        });


        //상품 추가
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AddMenuActivity.class);
                intent.putExtra("storeId",storeId);
                intent.putExtra("menulength", length);
                intent.putExtra("acclist", acclist);
                intent.putExtra("storelist", strlist);
                intent.putExtra("menulist", menulist);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });

        //상품 찾기
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, searchMenu.getText()+"를 찾는중입니다.", Toast.LENGTH_SHORT).show();
            }
        });


        //슬라이드 메뉴
        final DrawerLayout drawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        final View drawerView =findViewById(R.id.ll_drawer);
        openDrawer=findViewById(R.id.iv_hamburger);
        nick.setText(id+"("+nickname+")");

        openDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
            }

        });

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { drawerLayout.closeDrawer(drawerView);
            }
        });

        review=findViewById(R.id.review);
        orderlist=findViewById(R.id.orderlist);
        standing=findViewById(R.id.standing);

        question=findViewById(R.id.question);

        logout=findViewById(R.id.btnLogout);
        editAccnt=findViewById(R.id.linearLayout5);


        //회원정보수정
        editAccnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditAccountActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("nick",nickname);
                intent.putExtra("phone",phone);
                startActivity(intent);
            }
        });

        //리뷰관리
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!storeId.equals("null")){
                    ReviewList(storeId);
                }else{
                    Toast.makeText(getApplicationContext(), "리뷰가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                }

//                Intent intent = new Intent(MainActivity.this, ReviewListActivity.class);
//                startActivity(intent);
            }
        });

        //접수내역
        orderlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!storeId.equals("null")){
                    OrderList(storeId);
                }else{
                    Toast.makeText(getApplicationContext(), "접수내역이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                }//                Intent intent = new Intent(MainActivity.this, OrderListActivity.class);
//                startActivity(intent);
            }
        });


        //입점신청
        standing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StandActivity1.class);
                intent.putExtra("token",token);
                startActivity(intent);
            }
        });

        //로그아웃
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //문의사항
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                startActivity(intent);
            }
        });

        //마이페이지
//        linearLayout=findViewById(R.id.linearLayout5);
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, MyPageActivity.class);
//                startActivity(intent);
//            }
//        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("로그아웃하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNeutralButton("아니오",null);
                builder.create().show();

            }
        });

    }



    private void listview(String storeId, String length, ArrayList<MenuItem> menulist){
        //리스트뷰
        listView=findViewById(R.id.listView);
        adapter=new MenuAdapter(items);

        int length2 = Integer.parseInt(length);
        for (int i = 0; i < length2 ;i++) {
            items.add(new MenuItem(menulist.get(i).getMenuId(), menulist.get(i).getPrice(), menulist.get(i).getMenuName(),
                    menulist.get(i).getOptions(), menulist.get(i).getDescript()));
        }
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //
        nestedScrollView=findViewById(R.id.nested_scroll_view);
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                nestedScrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MenuItem item = (MenuItem) adapter.getItem(position);
                Intent intent=new Intent(MainActivity.this, MenuDetailActivity.class);
                intent.putExtra("price",item.getPrice());
                intent.putExtra("name",item.getMenuName());
                intent.putExtra("comment",item.getDescript());
                intent.putExtra("options",item.getOptions());
                intent.putExtra("storeId",storeId);

                intent.putExtra("menulength", length);
                intent.putExtra("acclist", acclist);
                intent.putExtra("storelist", strlist);
                intent.putExtra("menulist", menulist);
                intent.putExtra("token", token);
                startActivity(intent);

            }
        });
    }

    private void ReviewList(String strid){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //http 요청 시 url 주소와 파라미터 데이터를 결합하기 위한 변수 선언
        //http 요청 시 필요한 url 주소를 변수 선언
        String totalUrl = "";
        String UrlData = "http://165.229.86.152:8293/app/review/"+strid+""; // 가게별 리뷰리스트
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
            ArrayList<ReviewItem> list = new ArrayList<ReviewItem>();

            try {
                JSONArray jsonArray = new JSONArray(returnData);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject JsonObject = jsonArray.getJSONObject(i);
                    String nickname = JsonObject.getString("nickname");
                    String stars = JsonObject.getString("stars");
                    String content = JsonObject.getString("content");

                    list.add(new ReviewItem(nickname, stars, content));
                }
                if(!list.isEmpty()){
                    Intent intent=new Intent(MainActivity.this, ReviewListActivity.class);
                    intent.putExtra("list",list);
                    intent.putExtra("length",Integer.toString(jsonArray.length()));
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "리뷰가 존재하지 않습니다.",Toast.LENGTH_SHORT).show();
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

    private void OrderList(String strid){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //http 요청 시 url 주소와 파라미터 데이터를 결합하기 위한 변수 선언
        //http 요청 시 필요한 url 주소를 변수 선언
        String totalUrl = "";
        String UrlData = "http://165.229.86.152:8293/app/package/"+strid+"/list"; // 가게별 리뷰리스트
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
            ArrayList<OrderItem> list = new ArrayList<OrderItem>();

            try {
                JSONArray jsonArray = new JSONArray(returnData);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject JsonObject = jsonArray.getJSONObject(i);
                    String product = JsonObject.getString("product");
                    String address = JsonObject.getString("address");
                    int amount = JsonObject.getInt("amount");

                    list.add(new OrderItem(product, address, amount));
                }
                if(!list.isEmpty()){
                    Intent intent=new Intent(MainActivity.this, OrderListActivity.class);
                    intent.putExtra("list",list);
                    intent.putExtra("length",Integer.toString(jsonArray.length()));
                    intent.putExtra("strname",strname);
                    intent.putExtra("straddr",straddr);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "주문내역이 존재하지 않습니다.",Toast.LENGTH_SHORT).show();
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




    class MenuAdapter extends BaseAdapter {

        ArrayList<MenuItem> items;


        public MenuAdapter(ArrayList<MenuItem> items){
            this.items=items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(MenuItem i){
            items.add(i);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 뷰 객체 재사용
            MenuListView view = null;
            if (convertView == null) {
                view = new MenuListView(getApplicationContext());
            } else {
                view = (MenuListView) convertView;
            }

            MenuItem item = items.get(position);
//            view.setPrice(item.getPrice()+"원");
//            view.setName(item.getMenuName());
//            view.setComment(item.getDescript());

            view.setName(item.getMenuName());
            view.setPrice(item.getPrice()+"원");
            view.setComment(item.getDescript());

//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent=new Intent(StoreListActivity.this, StoreDetailActivity.class);
////                    intent.putExtra();
//                    startActivity(intent);
//                    Toast.makeText(getApplicationContext(),"클릭",Toast.LENGTH_SHORT).show();
//                }
//            });

            return view;
        }
    }
}