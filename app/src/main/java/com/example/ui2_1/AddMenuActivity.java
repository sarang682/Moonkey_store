package com.example.ui2_1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

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
                if(isNull(tv_menu)||isNull(tv_price)||isNull(tv_explain)){
                    Toast.makeText(getApplicationContext(), "빈칸을 확인해 주세요.", Toast.LENGTH_SHORT).show();
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
}