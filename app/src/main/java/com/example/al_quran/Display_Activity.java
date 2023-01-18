package com.example.al_quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;


public class Display_Activity extends AppCompatActivity {

    TextView viewText;
    QuranArabicText quran;
    QDH qdh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        viewText=findViewById(R.id.txtView);
        quran=new QuranArabicText();
        qdh=new QDH();
        String text="";

        Intent i=getIntent();
        int juz=i.getIntExtra("juz",-1);
        int ayat=i.getIntExtra("ayat",-1);
        boolean isStart=i.getBooleanExtra("isStart",false);
        String surah=i.getStringExtra("surah");

        viewText.setMovementMethod(new ScrollingMovementMethod());

        if(isStart){

                viewText.setText(getString(0,quran.QuranArabicText.length/2));
                return;

        }

        if(!surah.equals("")){
            int sNum=qdh.getSurahNumber(surah);
            if(sNum>0){
                int start=qdh.getSurahStart(sNum),end=qdh.getSurahStart(sNum+1);
                if(ayat>0){
                    text=quran.QuranArabicText[start+ayat-1];
                }else{
                    viewText.setText(String.join(" ", quran.GetData(start,end)));
                    return;
                }

            }else{
                text="Not Found";
            }
        }
        else if(juz>0){
            //text=getString(qdh.PSP[juz],qdh.PSP[juz+1]);
            viewText.setText(String.join(" ", quran.GetData(qdh.PSP[juz],qdh.PSP[juz+1])));
            return;
        }else if(ayat>0){
            text=quran.QuranArabicText[ayat+1];
        }

        viewText.setText(text);


    }
/*
    public String getString(int a,int b){
        String result="";
        if(a!=0){
            result=quran.QuranArabicText[0]+"\n";
        }
        for(int i=a;i<b;i++){
            result+=quran.QuranArabicText[i];
        }
        return result;
    }
*/

    public String getString(int a,int b){
        String[] data=quran.GetData(a,b);
        String result="";
        for(int i=a;i<b;i++){
            result+=data[i];
        }
        return result;
    }


    public void backClicked(View view){
        Intent intent=new Intent(Display_Activity.this,MainActivity.class);
        startActivity(intent);
    }



}