package com.example.da08.calc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView pretxt, restxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pretxt = (TextView) findViewById(R.id.pretxt);
        restxt = (TextView) findViewById(R.id.restxt);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btn10).setOnClickListener(this);

        findViewById(R.id.btnplus).setOnClickListener(this);
        findViewById(R.id.btnminus).setOnClickListener(this);
        findViewById(R.id.btnmulti).setOnClickListener(this);
        findViewById(R.id.btndivi).setOnClickListener(this);

        findViewById(R.id.btnclear).setOnClickListener(this);
        findViewById(R.id.btnres).setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1 : setPreView(1); break;
            case R.id.btn2 : setPreView(2); break;
            case R.id.btn3 : setPreView(3); break;
            case R.id.btn4 : setPreView(4); break;
            case R.id.btn5 : setPreView(5); break;
            case R.id.btn6 : setPreView(6); break;
            case R.id.btn7 : setPreView(7); break;
            case R.id.btn8 : setPreView(8); break;
            case R.id.btn9 : setPreView(9); break;
            case R.id.btn10 : setPreView(0); break;

            case R.id.btnplus : setPreView("+"); break;
            case R.id.btnminus : setPreView("-"); break;
            case R.id.btnmulti : setPreView("*"); break;
            case R.id.btndivi : setPreView("/"); break;

            case R.id.btnclear : clear(); break;
            case R.id.btnres : result(); break;


        }

    }

    private void setPreView(int number){
        String current = pretxt.getText().toString();
        pretxt.setText(current + number);

    }

    private void setPreView (String str){
        String current = pretxt.getText().toString();
        pretxt.setText(current + str);
    }

    private void clear(){
        pretxt.setText("0");
        restxt.setText("0");
    }

    private void result(){
        String current = pretxt.getText().toString();
        pretxt.setText(cal(current));
    }
    private String cal(String pretxt){
        String result = "";   // 문자열로 초기화 한번 해 줌

        String splited[] = pretxt.split("(?<=[*/+-])|(?=[*/+-])");  // 정규식

        ArrayList<String> list = new ArrayList<>();
        for(String temp : splited){
            list.add(temp);
        }

        for (int i = 0; i < list.size(); i++){
            String temp = list.get(i);
            int restemp = 0;

            if(temp.equals("*")|| temp.equals("/")) {
                int before = Integer.parseInt(list.get(i - 1));
                // 부호를 기준으로 나뉘어 앞 뒤 구분을 해주기 위해서
                // ex 1+2*3/4  > 1 : 1, 2 : +, 3 : 2, 4 : *...
                // 부호에서 걸리면 제일 먼저인 4번에서 앞 자리 before 뒷자리 after...
                int after = Integer.parseInt(list.get(i + 1));

                if (temp.equals("*"))
                    restemp = before * after;
                else
                    restemp = before / after;


                // 결과 값 저장
                list.set(i, restemp + "");
                list.remove(i + 1);
                list.remove(i - 1);
                i--;
            }

        }

        for(int i = 0; i<list.size(); i++){
            String temp = list.get(i);
            int restemp = 0;

            if(temp.equals("+") || temp.equals("-")){
                int before = Integer.parseInt(list.get(i-1));
                int after = Integer.parseInt(list.get(i+1));

                if (temp.equals("+"))
                    restemp = before + after;
                else restemp = before - after;

                list.set(i , restemp+"");
                list.remove(i + 1);
                list.remove(i - 1);
                i--;
            }
        }


        return list.get(0);
    }

}
