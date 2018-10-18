package com.flowdemo.test.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.List;


/**
 * Created by su on 2018/4/18.
 */
public class FlowPopListViewAdapter extends BaseAdapter {

    private Context context;
    private List<DataBean> dataList;

    public FlowPopListViewAdapter(Context context, List<DataBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_property, null);
            vh = new ViewHolder();
            vh.tvTypeName = (TextView) convertView.findViewById(R.id.tv_type_name);
            vh.layoutProperty = (MyFlowLayout) convertView.findViewById(R.id.layout_property);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        DataBean dataBean = dataList.get(position);
        vh.tvTypeName.setText(dataBean.getTypeName());

        setFlowLayoutData(dataBean.getChildren(), vh.layoutProperty);

        return convertView;
    }

    private void setFlowLayoutData(final List<DataBean.ChildItem> dataList, final MyFlowLayout flowLayout) {

        flowLayout.removeAllViews();
        for (int x = 0; x < dataList.size(); x++) {
            CheckBox checkBox = (CheckBox) View.inflate(context, R.layout.item_flowlayout_bill, null);
            checkBox.setText(dataList.get(x).getValue());

            if (dataList.get(x).isSelected()) {
                checkBox.setChecked(true);
                dataList.get(x).setSelected(true);
            } else {
                checkBox.setChecked(false);
                dataList.get(x).setSelected(false);
            }

            final int finalX = x;
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshCheckBox(flowLayout, finalX, dataList);
                }
            });
            flowLayout.addView(checkBox);


        }

    }

    private void refreshCheckBox(MyFlowLayout flowLayout, int finalX, List<DataBean.ChildItem> dataList) {
        for (int y = 0; y < flowLayout.getChildCount(); y++) {
            CheckBox radio = (CheckBox) flowLayout.getChildAt(y);
            radio.setChecked(false);
            dataList.get(y).setSelected(false);
            if (finalX == y) {
                radio.setChecked(true);
                dataList.get(y).setSelected(true);
            }
        }
    }

    class ViewHolder {
        private TextView tvTypeName;
        private MyFlowLayout layoutProperty;
    }
}
