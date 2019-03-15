package com.example.licaitong;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Xzzc1 extends AppCompatActivity{
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year,month,day;
    Button b;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xzzc1);


        final EditText e1= (EditText) findViewById(R.id.ed1);
        final EditText e2= (EditText) findViewById(R.id.ed2);
        final Spinner sp1= (Spinner) findViewById(R.id.SP3);
        final EditText e4= (EditText) findViewById(R.id.ed4);
        final EditText e5= (EditText) findViewById(R.id.ed5);
        Button bt1= (Button) findViewById(R.id.bt1);
        Button bt2= (Button) findViewById(R.id.bt2);




//        bt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String je=e1.getText().toString();
//                String sj=e2.getText().toString();
//                String lb=sp1.getSelectedItem().toString();
//                String dd=e4.getText().toString();
//                String bz=e5.getText().toString();
//            }
//        });

        bt1.setOnClickListener(new View.OnClickListener() {// 为保存按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                String strMoney = e1.getText().toString();// 获取金额文本框的值
                if (!strMoney.isEmpty()) {// 判断金额不为空
                    // 创建OutaccountDAO对象
                    OutaccountDAO outaccountDAO = new OutaccountDAO(Xzzc1.this);
                    // 创建Tb_outaccount对象
                    Tb_outaccount tb_outaccount = new Tb_outaccount(outaccountDAO.getMaxId() + 1, Double.parseDouble(strMoney), e2.getText()
                            .toString(), sp1.getSelectedItem().toString(), e4.getText().toString(), e5.getText().toString());
                    outaccountDAO.add(tb_outaccount);// 添加支出信息
                    // 弹出信息提示
                    Toast.makeText(Xzzc1.this, "支出数据添加成功", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Xzzc1.this, "请输入支出金额", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1.setText("");
                e4.setText("");
                e5.setText("");
            }
        });




        Spinner spinner = (Spinner) findViewById(R.id.SP3);
        String[] mItems = getResources().getStringArray(R.array.zhichu);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner .setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String[] zhichu = getResources().getStringArray(R.array.zhichu);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        dateView=(EditText)findViewById(R.id.ed2);
        calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year,month+1,day);
    }
    @SuppressWarnings("deprecation")
    public void setDate(View v){
        Toast.makeText(getApplicationContext(),"选择时间",Toast.LENGTH_SHORT).show();
        new DatePickerDialog(Xzzc1.this,myDateListener,year,month,day).show();

    }
    private DatePickerDialog.OnDateSetListener myDateListener= new DatePickerDialog.OnDateSetListener() {
        @Override
        public  void onDateSet(DatePicker arg0, int arg1,int arg2,int arg3){
            showDate(arg1,arg2+1,arg3);
        }
    };
    private void showDate(int year,int month,int day) {
        dateView.setText(new StringBuilder().append(year).append("/")
                .append(month).append("/").append(day));
        String aaa= ""+year+"/"+month+"/"+day;
        if(day==day&&month==month&&year==year){/*Toast.makeText(getApplicationContext(),aaa,Toast.LENGTH_LONG).show();*/}
    }

}
