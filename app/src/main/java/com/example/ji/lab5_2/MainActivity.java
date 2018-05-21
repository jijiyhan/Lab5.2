package com.example.ji.lab5_2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    EditText inputData;
    TextView textView1;
    TextView textView2;
    Button calculateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputData=findViewById(R.id.edit1);
        calculateBtn=findViewById(R.id.button1);
        textView1=findViewById(R.id.textView1);
        textView2=findViewById(R.id.textView2);

        calculateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new factoTask().execute();
            }
        });
    }

    private class factoTask extends AsyncTask<Void, Integer, Void>
    {
        int result=1;
        int max;
        @Override
        protected void onPreExecute(){
            max=Integer.valueOf(inputData.getText().toString());
            textView1.setText("");
        }

        @Override
        protected Void doInBackground(Void... params){
            for(int i=max; i>0; i--) //-1씩 뺴기
            {
                try{
                    publishProgress(i);
                    Thread.sleep(500); //500ms초 마다
                    result*=i; //곱하기계산
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            textView1.append(" "+values[0]); //"띄어쓰기+숫자" 출력
        }

        @Override
        protected void onPostExecute(Void aVoid){
            textView2.setText(" = "+String.valueOf(result)); //"결과값" 출력
        }

    }
}