package com.example.ui2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddMenuActivity extends AppCompatActivity {

    EditText tv_menu,tv_price,tv_explain;
    EditText tv_option,tv_option_price;
    Button btn_psearch, btn_add_option, btn_del_option, btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_menu);

        tv_menu=findViewById(R.id.make_menu_name);
        tv_price=findViewById(R.id.make_menu_cost);
        tv_explain=findViewById(R.id.modify_menu_explan);

        tv_option=findViewById(R.id.newitemname);
        tv_option_price=findViewById(R.id.newitemcost);

        btn_psearch=findViewById(R.id.attached_file_btn);
        btn_add_option=findViewById(R.id.btnAdd);
        btn_del_option=findViewById(R.id.btnDelete);
        btn_add=findViewById(R.id.complete);

        //상품등록
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNull(tv_menu)||isNull(tv_price)||isNull(tv_explain)){
                    Toast.makeText(getApplicationContext(), "빈칸을 확인해 주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //널 체크
    public boolean isNull(EditText editText) {
        if (editText.getText().toString().replace(" ", "").equals(""))
            return true;
        else return false;
    }

}