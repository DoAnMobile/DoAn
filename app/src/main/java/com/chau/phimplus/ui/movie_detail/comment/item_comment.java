package com.chau.phimplus.ui.movie_detail.comment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chau.phimplus.R;

public class item_comment extends AppCompatActivity {

    TextView txt_more,txt_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_comment);

    }
    private void fullComment() {

        Log.d("fff","Maxline " + Float.toString(txt_content.getMaxLines()));

        if(txt_content.getText().length()<1){

            ((ViewGroup) txt_more.getParent()).removeView(txt_more);
            ((ViewGroup) txt_content.getParent()).removeView(txt_content);

        }else if(txt_content.getMaxLines()<100){
            ((ViewGroup) txt_more.getParent()).removeView(txt_more);
        }else
        txt_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams size;
                if(txt_content.getHeight() > 100){

                    size = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
                    txt_more.setText("More...");

                }else{
                    size = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    txt_more.setText("^ ^ ^");
                }

                String fff = Integer.toString(txt_content.getHeight()) ;
                Log.d("fff","Maxline " + Float.toString(txt_content.getMaxLines()));
                Log.d("fff",fff);
                txt_content.setLayoutParams(size);
            }
        });
    }

    private void init() {
        txt_content = findViewById(R.id.cmt_content);
    }
}