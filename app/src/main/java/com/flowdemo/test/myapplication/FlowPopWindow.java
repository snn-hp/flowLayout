package com.flowdemo.test.myapplication;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

/**
 * Created by su on 2018/4/18.
 */

public class FlowPopWindow extends PopupWindow {
    private final Context context;
    private final List<DataBean> dataList;
    private DIYListView mListView;
    private TextView tvReset, tvConfirm;
    private View nullView;
    private FlowPopListViewAdapter adapter;
    private OnConfirmClickListener onConfirmClickListener;
    public FlowPopWindow(Context context, List<DataBean> dataList) {
        this.context = context;
        this.dataList = dataList;
        initPop();
    }


    private void initPop() {
        /** 自定义 弹窗宽高 */
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final Display display = manager.getDefaultDisplay();

        /** 获取状态栏高度**/
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);
        }
        View popView = LayoutInflater.from(context).inflate(R.layout.flow_pop_listview, null);
        //设置view
        this.setContentView(popView);
        //设置宽高（也可设置为LinearLayout.LayoutParams.MATCH_PARENT或者LinearLayout.LayoutParams.MATCH_PARENT）
        this.setWidth(display.getWidth() / 3 * 2);/*屏幕宽度的 2/3 */
        this.setHeight(display.getHeight() - statusBarHeight1);/*屏幕高度    部分手机 因为状态栏的高度  会导致底部按钮显示不全，所以减去*/

        //设置PopupWindow的焦点
        this.setFocusable(true);
        //设置窗口以外的地方点击可关闭pop
        this.setOutsideTouchable(true);
        //设置背景透明
        this.setBackgroundDrawable(new ColorDrawable(0x66000000));
        this.setAnimationStyle(R.style.anim_photo_select); /* 手机厂商不一样  ，listview  或列表view  滑动回弹动画不一样*/
        this.showAtLocation(popView, Gravity.RIGHT, 0, 0);

        mListView = (DIYListView) popView.findViewById(R.id.listview);
        tvReset = (TextView) popView.findViewById(R.id.tv_reset);
        tvConfirm = (TextView) popView.findViewById(R.id.tv_confirm);
        nullView = popView.findViewById(R.id.view_null);

        adapter = new FlowPopListViewAdapter(context, dataList);
        mListView.setAdapter(adapter);
        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int x = 0; x < dataList.size(); x++) {
                    List<DataBean.ChildItem> childrenBeen = dataList.get(x).getChildren();
                    for (int y = 0; y < childrenBeen.size(); y++) {
                        if (childrenBeen.get(y).isSelected())
                            childrenBeen.get(y).setSelected(false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //自定义监听第三步   回传监听
                onConfirmClickListener.onConfirmClick();
                dismiss();
            }
        });
        nullView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    //自定义监听第二步  定义监听事件
    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
    }

    //自定义监听第一步   定义接口
    public interface OnConfirmClickListener {
        void onConfirmClick();
    }

}
