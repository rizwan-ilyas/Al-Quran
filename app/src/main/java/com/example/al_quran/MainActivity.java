package com.example.al_quran;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startCliked(View v){
        Intent intent=new Intent(MainActivity.this,Display_Activity.class);
        intent.putExtra("isStart",true);
        startActivity(intent);
    }

    public void searchClicked(View v){
        EditText Surah=findViewById(R.id.editTextSurah);
        EditText Juz=findViewById(R.id.editTextJuz);
        EditText Ayat=findViewById(R.id.editTextAyat);
        Intent intent=new Intent(MainActivity.this,Display_Activity.class);

        //intent.putExtra("juz",Integer.valueOf(Juz.getText().toString()));
        intent.putExtra("surah","");
        intent.putExtra("ayat",-1);
        intent.putExtra("juz", -1);

        if(!Surah.getText().toString().equals("")){
            intent.putExtra("surah",Surah.getText().toString());
            if(!Ayat.getText().toString().equals("")){
                intent.putExtra("ayat",Integer.valueOf(Ayat.getText().toString()));
            }
        }else if(!Juz.getText().toString().equals("")){
            intent.putExtra("juz", Integer.valueOf(Juz.getText().toString()));
        }else if(!Ayat.getText().toString().equals("")){
            intent.putExtra("ayat",Integer.valueOf(Ayat.getText().toString()));
        }
        else{
            // do nothing
        }
        startActivity(intent);
    }

    public void githubClicked(View view){
        Uri webpage=Uri.parse("https://github.com/rizwan-ilyas/Al-Quran");
        Intent intent=new Intent(Intent.ACTION_VIEW,webpage);
        startActivity(intent);

    }

}