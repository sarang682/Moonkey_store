package com.example.ui2_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView store_name;
    private Button btn_add;
    private ListView listView;
    private MenuAdapter adapter;
    private ArrayList<Menu> items = new ArrayList<Menu>();
    private NestedScrollView nestedScrollView;
    private LinearLayout linearLayout;

    private ImageView openDrawer, mypage, searchbtn;
    private EditText searchMenu;
    private Button logout;
    private LinearLayout review, orderlist, standing, question, editAccnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        store_name=findViewById(R.id.tv_address);
        mypage=findViewById(R.id.mypage);
        searchMenu=findViewById(R.id.searchmenu);
        searchbtn=findViewById(R.id.searchbtn);
        btn_add=findViewById(R.id.imageView2);
        listView=findViewById(R.id.listView);
        nestedScrollView=findViewById(R.id.nested_scroll_view);

        store_name.setText("가게 이름");

        //마이페이지
        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MyPageActivity.class);
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

        //리스트뷰
        listView=findViewById(R.id.listView);
        adapter=new MenuAdapter(items);
        items.add(new Menu(10000,"떡볶이","밀떡으로 만들었어용"));
        items.add(new Menu(21000,"로제떡볶이","쌀떡으로 만들었어용"));
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
                Menu item = (Menu) adapter.getItem(position);
                Intent intent=new Intent(MainActivity.this, MenuDetailActivity.class);
                intent.putExtra("price",item.getPrice());
                intent.putExtra("name",item.getName());
                intent.putExtra("comment",item.getComment());
                startActivity(intent);

            }
        });


        //슬라이드 메뉴
        final DrawerLayout drawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        final View drawerView =findViewById(R.id.ll_drawer);
        openDrawer=findViewById(R.id.iv_hamburger);

        openDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
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

    class MenuAdapter extends BaseAdapter {

        ArrayList<Menu> items;


        public MenuAdapter(ArrayList<Menu> items){
            this.items=items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(Menu i){
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

            Menu item = items.get(position);
            view.setPrice(item.getPrice());
            view.setName(item.getName());
            view.setComment(item.getComment());

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