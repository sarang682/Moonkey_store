package com.example.ui2_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
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
    private String id, uid, phone, addr, nickname, strname, straddr, category, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);


        Intent intent = getIntent();
        int length;
        ArrayList<AccountItem> acclist;
        ArrayList<StoreItem> strlist;
        ArrayList<MenuItem> menulist;

        if(intent.hasExtra("menulength")&&intent.hasExtra("menulist")){
            length = Integer.parseInt(intent.getStringExtra("menulength"));
            menulist = (ArrayList<MenuItem>) intent.getSerializableExtra("menulist");
            listview(length,menulist);

        }
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
            strname = strlist.get(0).getName();
            straddr = strlist.get(0).getAddress();
            category = strlist.get(0).getCategory();
            contact = strlist.get(0).getContact();
        }


        store_name=findViewById(R.id.tv_address);
        mypage=findViewById(R.id.mypage);
        searchMenu=findViewById(R.id.searchmenu);
        searchbtn=findViewById(R.id.searchbtn);
        btn_add=findViewById(R.id.imageView2);
        listView=findViewById(R.id.listView);
        nestedScrollView=findViewById(R.id.nested_scroll_view);
        nick = findViewById(R.id.id_txt);

        store_name.setText(strname);

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
                Intent intent = new Intent(MainActivity.this, ReviewListActivity.class);
                startActivity(intent);
            }
        });

        //접수내역
        orderlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrderListActivity.class);
                startActivity(intent);
            }
        });


        //입점신청
        standing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StandActivity1.class);
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


    private void listview(int length, ArrayList<MenuItem> menulist){
        //리스트뷰
        listView=findViewById(R.id.listView);
        adapter=new MenuAdapter(items);
        for (int i = 0; i < length ;i++) {
            items.add(new MenuItem(menulist.get(i).getMenuId(), menulist.get(i).getPrice(), menulist.get(i).getMenuName(),
                    menulist.get(i).getOptions(), menulist.get(i).getDescript()));
        }
//        items.add(new Menu(10000,"떡볶이","밀떡으로 만들었어용"));
//        items.add(new Menu(21000,"로제떡볶이","쌀떡으로 만들었어용"));
        listView.setAdapter(adapter);

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
                startActivity(intent);

            }
        });
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