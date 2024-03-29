package com.example.licaitong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Szbq7_xg extends Activity {
    private EditText txtFlag;// 创建EditText对象
    private Button btnEdit, btnDel;// 创建两个Button对象
    private String strid;// 创建字符串，表示便签的id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.szbq7_xg);// 设置布局文件
        txtFlag = (EditText) findViewById(R.id.ee1);// 获取便签文本框
        btnEdit = (Button) findViewById(R.id.bb1);// 获取修改按钮
        btnDel = (Button) findViewById(R.id.bb2);// 获取删除按钮

        Intent intent = getIntent();// 创建Intent对象
        Bundle bundle = intent.getExtras();// 获取便签id
        strid = bundle.getString(Showinfo.FLAG);// 将便签id转换为字符串
        final FlagDAO flagDAO = new FlagDAO(Szbq7_xg.this);// 创建FlagDAO对象
        txtFlag.setText(flagDAO.find(Integer.parseInt(strid)).getFlag());// 根据便签id查找便签信息，并显示在文本框中

        btnEdit.setOnClickListener(new OnClickListener() {// 为修改按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                Tb_flag tb_flag = new Tb_flag();// 创建Tb_flag对象
                tb_flag.setid(Integer.parseInt(strid));// 设置便签id
                tb_flag.setFlag(txtFlag.getText().toString());// 设置便签值
                flagDAO.update(tb_flag);// 修改便签信息
                // 弹出信息提示
                Toast.makeText(Szbq7_xg.this, "修改成功", Toast.LENGTH_SHORT).show();

            }
        });

        btnDel.setOnClickListener(new OnClickListener() {// 为删除按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                flagDAO.detele(Integer.parseInt(strid));// 根据指定的id删除便签信息
                Toast.makeText(Szbq7_xg.this, "删除成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
