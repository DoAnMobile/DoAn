package com.chau.phimplus.ui.movie_detail;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.chau.phimplus.R;

public class FragmentLichChieu extends Fragment implements View.OnClickListener {

    String arrBranch[];
    private View mRootView;
    Spinner spinner_branch;
<<<<<<< Updated upstream
    Button btn_test;
    private LinearLayout MasterLayout;
    private LinearLayout ShowtimeLayout;
    EditText editText_test;
=======
    private LinearLayout MasterLayout;
>>>>>>> Stashed changes

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){




        mRootView =inflater.inflate(R.layout.fragment_lichchieu,container,false);
        //
        anhXa();
        setData();
        loadSpinerBranch();
<<<<<<< Updated upstream
        //

        btn_test.setOnClickListener(this);
=======

>>>>>>> Stashed changes
        //
        return mRootView;
    }

    private void createLayout(LinearLayout _rootCView, Integer _SubId) {
        // new layout
        LinearLayout mLayout = new LinearLayout(getActivity());
        mLayout.setGravity(View.TEXT_ALIGNMENT_CENTER);
        mLayout.setVerticalGravity(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayout.setLayoutParams(params);
        mLayout.setBackgroundColor(Color.RED);
        //mLayout.setId(_SubId);
        // add TEXT VIEW
        createTextView(mLayout,_SubId,"TITLE in here!" + "\t ID="+ Integer.toString(mLayout.getId()));

        _rootCView.addView(mLayout);
    }
    private void createTextView(LinearLayout _rootView, Integer _SubId, String _content){
        TextView textView = new TextView(getActivity());
        textView.setId(_SubId);
        LinearLayout.LayoutParams size = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(size);
        textView.setText(_content);
        textView.setGravity(Gravity.CENTER);

        _rootView.addView(textView);
    }


    private  void setData() {
       arrBranch = new String[]{"Lê Thánh Tôn", "Bình Long","Trung Sơn"};
    }

    private void loadSpinerBranch() {
        ArrayAdapter<String> arrayAdapter = new  ArrayAdapter<String>(
                getActivity(),
               R.layout.support_simple_spinner_dropdown_item,
                arrBranch);
        // style
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_branch.setAdapter(arrayAdapter);
    }

    private void anhXa() {
        spinner_branch = (Spinner)mRootView.findViewById(R.id.spi_branch);
        //
<<<<<<< Updated upstream
        btn_test = (Button)mRootView.findViewById(R.id.btn_test);
        //
        MasterLayout = (LinearLayout) mRootView.findViewById(R.id.linearLayoutMainShowPaln);
        //
        editText_test = (EditText)mRootView.findViewById(R.id.edit_test);
=======
        MasterLayout = (LinearLayout) mRootView.findViewById(R.id.linearLayoutMainShowPaln);
>>>>>>> Stashed changes

    }
    private Spinner addList (Spinner spinner){
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.list_breanch,
                R.layout.support_simple_spinner_dropdown_item);
        // style
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        return spinner;
    }

    @Override
    public void onClick(View v) {

<<<<<<< Updated upstream
        switch (v.getId()) {
            case R.id.btn_test: {
//                Integer subLayoutID = 1;
//                Integer id = Integer.parseInt(editText_test.getText().toString());
//                test_function(id);

            }
        }
=======

>>>>>>> Stashed changes
    }

}
