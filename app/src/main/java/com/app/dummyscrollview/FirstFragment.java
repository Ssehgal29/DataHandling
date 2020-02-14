package com.app.dummyscrollview;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FirstFragment extends Fragment
        implements View.OnClickListener,SecondFragment.OnSendClickListener {

    public OnClickListener callBack;
    private String strDataFromAct,strFirstFragData,strDataFromFrag;
    private EditText edtFirstFragData;
    private Button btnNextFrag,btnSendDataBack;
    private TextView txtDataFromAct,txtDataFromSecondFrag;

    public FirstFragment(String strName) {
        this.strDataFromAct = strName;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        setId(view);
        setListener();
        setDataFromActivity();

        return view;
    }

    public void setId(View view){
        edtFirstFragData=view.findViewById(R.id.firstFragmentData);
        txtDataFromAct=view.findViewById(R.id.data);
        txtDataFromSecondFrag=view.findViewById(R.id.dataFromSecondFragment);
        btnNextFrag=view.findViewById(R.id.goToNext);
        btnSendDataBack=view.findViewById(R.id.sendDataToActivity);
        callBack= (OnClickListener) getActivity();
    }

    public void setListener(){
        btnSendDataBack.setOnClickListener(this);
        btnNextFrag.setOnClickListener(this);
    }
    public void setDataFromActivity(){
        txtDataFromAct.setText(strDataFromAct); //using constructor value
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goToNext:
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container2,new SecondFragment(edtFirstFragData.getText().toString().trim())).commit();
                break;
            case R.id.sendDataToActivity:
                strFirstFragData=edtFirstFragData.getText().toString().trim();
                callBack.onSelect(strFirstFragData);
                break;
        }
    }

    @Override
    public void dataFromSecondFrag(String data) {
        this.strDataFromFrag=data;
        txtDataFromSecondFrag.setText(strDataFromFrag);
    }

    public interface OnClickListener{
        void onSelect(String str);
        void onDeSelect(String str);
    }

}
