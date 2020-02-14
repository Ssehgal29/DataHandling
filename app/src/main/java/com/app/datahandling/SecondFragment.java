package com.app.datahandling;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondFragment extends Fragment implements View.OnClickListener{

    private String strFirstFragData;

    public SecondFragment(String data) {
        this.strFirstFragData=data;
    }
    public OnSendClickListener callback;
    private TextView txtDataFromFrag;
    private EditText edtDataTOFrag;
    private Button btnSendDataToFrag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        setId(view);
        setListener();

        return view;
    }

    public void setId(View view){
        txtDataFromFrag=view.findViewById(R.id.dataFromFirstFrag);
        txtDataFromFrag.setText(strFirstFragData);
        edtDataTOFrag=view.findViewById(R.id.dataToFrag);
        btnSendDataToFrag=view.findViewById(R.id.sendBackFrag);
    }
    public void setListener(){
        btnSendDataToFrag.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sendBackFrag:
                try {
                    callback.dataFromSecondFrag(edtDataTOFrag.getText().toString().trim());
                }catch (NullPointerException e){
                    Toast.makeText(getActivity(), "Null Pointer Exception", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public interface OnSendClickListener{
        void dataFromSecondFrag(String data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback= (SecondFragment.OnSendClickListener) context;
        }catch (ClassCastException e){
            Toast.makeText(context, "Class Cast Exception", Toast.LENGTH_SHORT).show();
        }
    }
}
