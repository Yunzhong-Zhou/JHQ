package com.fone.fone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.model.HelpModel;

import java.util.List;


/**
 * Created by zyz on 2016/7/6.
 * Email：1125213018@qq.com
 * description：帮助 adapter
 */
public class HelpAdapter extends BaseAdapter {
    private Context context;
    private List<HelpModel> list;
    private int selectIndex = 0;

    public HelpAdapter(Context context, List<HelpModel> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void setSelectItem(int selectItem) {
        this.selectIndex = selectItem;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_help, null);
            holder.textView1 = (TextView) convertView.findViewById(R.id.textView1);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView1.setText((position+1)+"、"+list.get(position).getTitle());
        /*if (selectIndex == position) {
            holder.textView1.setSelected(true);
            holder.textView1.setPressed(true);
            holder.textView1.setTextColor(context.getResources().getColor(R.color.yellow700));
            holder.textView1.setBackgroundColor(context.getResources().getColor(R.color.pop_bg2));
        } else {
            holder.textView1.setSelected(false);
            holder.textView1.setPressed(false);
            holder.textView1.setTextColor(context.getResources().getColor(R.color.txt_black1));
            holder.textView1.setBackgroundColor(Color.WHITE);
        }*/
        return convertView;
    }

    private static class ViewHolder {
        TextView textView1;
    }
}
