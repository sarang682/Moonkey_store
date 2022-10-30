package com.example.ui2_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ui2_1.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btn_add;
    private ListView listView;
    private MenuAdapter adapter;
    private ArrayList<Menu> items = new ArrayList<Menu>();
    private NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_home);

        //상품 추가
        btn_add=findViewById(R.id.imageView2);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AddMenuActivity.class);
                startActivity(intent);
            }
        });

        //리스트뷰
        listView=findViewById(R.id.listView);
        adapter=new MenuAdapter(items);
        items.add(new Menu("10000원","떡볶이","밀떡으로 만들었어용"));
        items.add(new Menu("21000원","로제떡볶이","쌀떡으로 만들었어용"));
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

    }

    class MenuAdapter extends BaseAdapter {

        ArrayList<Menu> items;

        String user_id;

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