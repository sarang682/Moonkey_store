package com.example.Moonkey_store;

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
    private StoreItem storeInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_orderlist);

        Intent intent = getIntent();
        ArrayList<OrderItem> list = (ArrayList<OrderItem>)intent.getSerializableExtra("list");
        int length = Integer.parseInt(intent.getStringExtra("length"));
        storeInfo= (StoreItem) intent.getSerializableExtra("storeInfo");
        String token = intent.getStringExtra("token");
        String uid = intent.getStringExtra("uid");

        lView=findViewById(R.id.order_lv);
        adapter=new OrderListActivity.ItemAdapter(list);
//
        lView.setAdapter(adapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderItem item = (OrderItem) adapter.getItem(position);
                Intent intent=new Intent(OrderListActivity.this, OrderDetailActivity.class);
                intent.putExtra("orderItem",item);
                intent.putExtra("storeInfo",storeInfo);
                intent.putExtra("token",token);
                intent.putExtra("uid",uid);
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
            view.setPrice(Integer.toString(item.getAmount()));
            view.setProduct(item.getProduct());
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