package com.app.dummyscrollview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements FirstFragment.OnClickListener, View.OnClickListener {

    private Button btnCallFragment;
    private EditText edtName;
    private TextView txtCallBack;
    private String strName,strCallBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setId();
        setListener();
    }

    public void setId(){
        btnCallFragment=findViewById(R.id.callFirstFragment);
        txtCallBack=findViewById(R.id.textCallBack);
        edtName=findViewById(R.id.data);
    }

    public void setListener(){
        btnCallFragment.setOnClickListener(this);
    }
    public void setDataFromFragment(){
        txtCallBack.setText(strCallBack);
    }

    @Override
    public void onSelect(String str) {
        this.strCallBack=str;
        setDataFromFragment();
    }

    @Override
    public void onDeSelect(String str) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.callFirstFragment:
                strName=edtName.getText().toString();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container,new FirstFragment(strName)).commit();
                break;
        }
    }
}
