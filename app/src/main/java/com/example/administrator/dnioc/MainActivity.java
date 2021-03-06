package com.example.administrator.dnioc;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dnioc.ioc.InjectLayout;
import com.example.administrator.dnioc.ioc.InjectView;
import com.example.administrator.dnioc.ioc.OnClick;

@InjectLayout(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @InjectView(R.id.tv_1)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, tv==null?"没找到控件":tv.toString(), Toast.LENGTH_SHORT).show();
    }

    @Deprecated
    @OnClick(R.id.tv_1)
    public void onClick(View view){
        Toast.makeText(this,"---->"+tv,Toast.LENGTH_SHORT).show();
    }

}
