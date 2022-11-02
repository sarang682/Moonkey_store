package com.example.ui2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ui2_1.R;
import com.example.ui2_1.ReviewItem;
import com.example.ui2_1.ReviewListView;

import java.util.ArrayList;

public class ReviewListActivity extends AppCompatActivity {

    private ListView lView;
    private ItemAdapter adapter;
    private ArrayList<ReviewItem> items = new ArrayList<ReviewItem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_reviewlist);

        lView=findViewById(R.id.ListView);
        adapter=new ItemAdapter(items);
        items.add(new ReviewItem("김세모","4.0","맛있어용"));
        items.add(new ReviewItem("박네모","4.5","최고에요"));
        items.add(new ReviewItem("이동글","5.0","다음에 또 시킬게요"));
        items.add(new ReviewItem("정수박","5.0","잘먹을게용"));

        lView.setAdapter(adapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReviewItem item = (ReviewItem) adapter.getItem(position);
                Intent intent=new Intent(ReviewListActivity.this, ReviewDetailActivity.class);
                intent.putExtra("score",item.getScore());
                intent.putExtra("content",item.getContents());
                startActivity(intent);

            }
        });

    }

    class ItemAdapter extends BaseAdapter {

        ArrayList<ReviewItem> items;

        String user_id;

        public ItemAdapter(ArrayList<ReviewItem> items){
            this.items=items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(ReviewItem i){
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
            ReviewListView view = null;
            if (convertView == null) {
                view = new ReviewListView(getApplicationContext());
            } else {
                view = (ReviewListView) convertView;
            }

            ReviewItem item = items.get(position);
            view.setNickname(item.getNickname());
            view.setScore(item.getScore());
            view.setContents(item.getContents());

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