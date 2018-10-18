package com.flowdemo.test.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ImageView ivBack;
    private TextView tvFlow;
    private FlowPopWindow flowPopWindow;
    private List<DataBean> dictList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParam();
        initView();
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvFlow = (TextView) findViewById(R.id.tv_flow);
        tvFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                flowPopWindow = new FlowPopWindow(getBaseContext(), dictList);
                flowPopWindow.showAsDropDown(ivBack);
                flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick() {
                        StringBuilder sb = new StringBuilder();
                        for (DataBean fb : dictList) {
                            List<DataBean.ChildItem> cdList = fb.getChildren();
                            for (int x = 0; x < cdList.size(); x++) {
                                DataBean.ChildItem children = cdList.get(x);
                                if (children.isSelected())
                                    sb.append(fb.getTypeName() + ":" + children.getValue() + "；");
                            }
                        }
                        if (!TextUtils.isEmpty(sb.toString()))
                            Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }


    //伪数据，真实项目中直接接口获取添加进来，FiltrateBean对象可根据自己需求更改
    private void initParam() {
        String[] sexs = {"男", "女"};
        String[] colors = {"红色", "黄色", "橙色", "绿色", "青色", "蓝色", "紫色", "黑色", "白色",};
        String[] style = {"长袖", "短袖", "连体", "带帽", "不带帽", "印花", "纯色"};
        String[] price = {"从高到低", "从低到高"};

        DataBean fb1 = new DataBean();
        fb1.setTypeName("性别");
        List<DataBean.ChildItem> childrenList = new ArrayList<>();
        for (int x = 0; x < sexs.length; x++) {
            DataBean.ChildItem cd = new DataBean.ChildItem();
            cd.setValue(sexs[x]);
            childrenList.add(cd);
        }
        fb1.setChildren(childrenList);

        DataBean fb2 = new DataBean();
        fb2.setTypeName("颜色");
        List<DataBean.ChildItem> childrenList2 = new ArrayList<>();
        for (int x = 0; x < colors.length; x++) {
            DataBean.ChildItem cd = new DataBean.ChildItem();
            cd.setValue(colors[x]);
            childrenList2.add(cd);
        }
        fb2.setChildren(childrenList2);


        DataBean fb3 = new DataBean();
        fb3.setTypeName("款式");
        List<DataBean.ChildItem> childrenList3 = new ArrayList<>();
        for (int x = 0; x < style.length; x++) {
            DataBean.ChildItem cd = new DataBean.ChildItem();
            cd.setValue(style[x]);
            childrenList3.add(cd);
        }
        fb3.setChildren(childrenList3);


        DataBean fb4 = new DataBean();
        fb4.setTypeName("价格");
        List<DataBean.ChildItem> childrenList4 = new ArrayList<>();
        for (int x = 0; x < price.length; x++) {
            DataBean.ChildItem cd = new DataBean.ChildItem();
            cd.setValue(price[x]);
            childrenList4.add(cd);
        }
        fb4.setChildren(childrenList4);

        dictList.add(fb1);
        dictList.add(fb2);
        dictList.add(fb3);
        dictList.add(fb4);
    }
}
