package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.appdoctruyen.adapter.TruyenTranhAdapter;
import com.example.appdoctruyen.api.ApiLayTruyen;
import com.example.appdoctruyen.interfaces.LayTruyenVe;
import com.example.appdoctruyen.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LayTruyenVe {

    GridView gdvDSTruyen;
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh> truyenTranhArrayList;
    EditText edtTimKiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiLayTruyen(this).execute();
    }
    private void init(){
        truyenTranhArrayList = new ArrayList<>();
//        truyenTranhArrayList.add(new TruyenTranh("Con Trai Út Của Gia Đình Kiếm Thuật","Chapter 79","https://st.nettruyenmax.com/data/comics/202/con-trai-ut-cua-gia-dinh-kiem-thuat-danh-3180.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Ta Trời Sinh Đã Là Nhân Vật Phản Diện","Chapter 91","https://st.nettruyenmax.com/data/comics/235/ta-troi-sinh-da-la-nhan-vat-phan-dien-3659.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Mở Đầu Nữ Đế Làm Chính Cung","Chapter 184","https://st.nettruyenmax.com/data/comics/235/mo-dau-nu-de-lam-chinh-cung.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Người Trên Vạn Người","Chapter 238","https://st.nettruyenmax.com/data/comics/208/nguoi-tren-van-nguoi.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Con Trai Út Của Gia Đình Kiếm Thuật","Chapter 79","https://st.nettruyenmax.com/data/comics/202/con-trai-ut-cua-gia-dinh-kiem-thuat-danh-3180.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Ta Trời Sinh Đã Là Nhân Vật Phản Diện","Chapter 91","https://st.nettruyenmax.com/data/comics/235/ta-troi-sinh-da-la-nhan-vat-phan-dien-3659.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Mở Đầu Nữ Đế Làm Chính Cung","Chapter 184","https://st.nettruyenmax.com/data/comics/235/mo-dau-nu-de-lam-chinh-cung.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Người Trên Vạn Người","Chapter 238","https://st.nettruyenmax.com/data/comics/208/nguoi-tren-van-nguoi.jpg"));truyenTranhArrayList.add(new TruyenTranh("Con Trai Út Của Gia Đình Kiếm Thuật","Chapter 79","https://st.nettruyenmax.com/data/comics/202/con-trai-ut-cua-gia-dinh-kiem-thuat-danh-3180.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Ta Trời Sinh Đã Là Nhân Vật Phản Diện","Chapter 91","https://st.nettruyenmax.com/data/comics/235/ta-troi-sinh-da-la-nhan-vat-phan-dien-3659.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Mở Đầu Nữ Đế Làm Chính Cung","Chapter 184","https://st.nettruyenmax.com/data/comics/235/mo-dau-nu-de-lam-chinh-cung.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Người Trên Vạn Người","Chapter 238","https://st.nettruyenmax.com/data/comics/208/nguoi-tren-van-nguoi.jpg"));

        adapter = new TruyenTranhAdapter(this,0,truyenTranhArrayList);
    }
    private void anhXa(){
        gdvDSTruyen = findViewById(R.id.gdvDSTruyen);
        edtTimKiem = findViewById(R.id.edtTimKiem);

    }
    private void setUp(){
        gdvDSTruyen.setAdapter(adapter);
    }
    private void setClick(){
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String stg = edtTimKiem.getText().toString();
                adapter.sortTruyen(stg);
            }
        });
        gdvDSTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                TruyenTranh truyenTranh=truyenTranhArrayList.get(i);
                Bundle b = new Bundle();
                b.putSerializable("truyen",truyenTranh);
                Intent intent = new Intent(MainActivity.this, ChapActivity.class);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this,"Dang Lay Ve",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            truyenTranhArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i =0; i< arr.length();i++){
                JSONObject o = arr.getJSONObject(i);
                truyenTranhArrayList.add(new TruyenTranh(o));
            }
            adapter = new TruyenTranhAdapter(this,0,truyenTranhArrayList);
            gdvDSTruyen.setAdapter(adapter);

        }catch (JSONException e){

        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this,"Loi Ket Noi",Toast.LENGTH_SHORT).show();
    }

    public void update(View view) {
        new ApiLayTruyen(this).execute();
    }
}