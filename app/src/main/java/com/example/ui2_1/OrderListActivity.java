package com.example.ui2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity {

    private ListView lView;
    private OrderListActivity.ItemAdapter adapter;
    private ArrayList<OrderItem> items = new ArrayList<OrderItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_orderlist);
        lView=findViewById(R.id.order_lv);
        adapter=new OrderListActivity.ItemAdapter(items);
        items.add(new OrderItem(8,"대구광역시 수성구 초록동 나무아파트",1584600));
        items.add(new OrderItem(10,"대구광역시 수성구 노랑동 나무아파트",874200));
        items.add(new OrderItem(5,"대구광역시 수성구 파랑동 나무아파트",70000));
        items.add(new OrderItem(7,"대구광역시 수성구 빨강동 나무아파트",1010200));

        lView.setAdapter(adapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderItem item = (OrderItem) adapter.getItem(position);
                Intent intent=new Intent(OrderListActivity.this, OrderDetailActivity.class);
                startActivity(intent);

            }
        });


    }

    class ItemAdapter extends BaseAdapter {

        ArrayList<OrderItem> items;

        String user_id;

        public ItemAdapter(ArrayList<OrderItem> items){
            this.items=items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(OrderItem i){
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
            OrderListView view = null;
            if (convertView == null) {
                view = new OrderListView(getApplicationContext());
            } else {
                view = (OrderListView) convertView;
            }

            OrderItem item = items.get(position);
            view.setPrice(Integer.toString(item.getPrice()));
            view.setNum(Integer.toString(item.getNum()));
            view.setAddress(item.getAddress());

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