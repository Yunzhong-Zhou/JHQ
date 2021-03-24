package com.ghzk.ghzk.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ghzk.ghzk.R;
import com.ghzk.ghzk.model.OnlineServiceModel;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.popupwindow.ZoomIMGPopupWindow;
import com.ghzk.ghzk.utils.LocalUserInfo;

import java.util.List;


/**
 * Created by zyz on 2016/7/6.
 * Email：1125213018@qq.com
 * description：在线留言adapter
 */
public class OnlineServiceAdapter extends BaseAdapter {
    private Context context;
    private List<OnlineServiceModel.LeaveMessageListBean> list;
    private int selectIndex = 0;

    public OnlineServiceAdapter(Context context, List<OnlineServiceModel.LeaveMessageListBean> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_onlineservice, null);
            holder.textView1_1 = (TextView) convertView.findViewById(R.id.textView1_1);
            holder.textView1_2 = (TextView) convertView.findViewById(R.id.textView1_2);
            holder.textView2_1 = (TextView) convertView.findViewById(R.id.textView2_1);
            holder.textView2_2 = (TextView) convertView.findViewById(R.id.textView2_2);
            holder.imageView1_1 = (ImageView) convertView.findViewById(R.id.imageView1_1);
            holder.imageView1_2 = (ImageView) convertView.findViewById(R.id.imageView1_2);
            holder.imageView2_1 = (ImageView) convertView.findViewById(R.id.imageView2_1);
            holder.imageView2_2 = (ImageView) convertView.findViewById(R.id.imageView2_2);
            holder.linearLayout1 = (LinearLayout) convertView.findViewById(R.id.linearLayout1);
            holder.linearLayout2 = (LinearLayout) convertView.findViewById(R.id.linearLayout2);
            holder.linearLayout1_1 = (LinearLayout) convertView.findViewById(R.id.linearLayout1_1);
            holder.linearLayout2_1 = (LinearLayout) convertView.findViewById(R.id.linearLayout2_1);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (list.get(position).getUser_id().equals("")){
            //自己的消息
            holder.linearLayout1.setVisibility(View.VISIBLE);
            holder.linearLayout2.setVisibility(View.GONE);
            //时间
            holder.textView1_1.setText(list.get(position).getCreated_at());
            //自己的头像
            if (!LocalUserInfo.getInstance(context).getUserImage().equals(""))
                Glide.with(context)
                        .load(OkHttpClientManager.IMGHOST + LocalUserInfo.getInstance(context).getUserImage())
                        .centerCrop().into(holder.imageView1_2);//加载图片
            else
                holder.imageView2_1.setImageResource(R.mipmap.headimg);
            //内容
            if (list.get(position).getPic() == null){
                //文字消息
                holder.imageView1_1.setVisibility(View.GONE);
                holder.linearLayout1_1.setVisibility(View.VISIBLE);
                holder.textView1_2.setText(list.get(position).getContent());
            }else {
                //图片消息
                holder.imageView1_1.setVisibility(View.VISIBLE);
                holder.linearLayout1_1.setVisibility(View.GONE);
                Glide.with(context).load(OkHttpClientManager.IMGHOST + list.get(position).getPic()).into(holder.imageView1_1);//加载图片

                final ViewHolder finalHolder = holder;
                holder.imageView1_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ZoomIMGPopupWindow popupwindow = new ZoomIMGPopupWindow(context,OkHttpClientManager.IMGHOST + list.get(position).getPic());
                        popupwindow.showAtLocation(finalHolder.imageView1_2, Gravity.CENTER, 0, 0);
                    }
                });
            }
        }else {
            //对方的消息
            holder.linearLayout1.setVisibility(View.GONE);
            holder.linearLayout2.setVisibility(View.VISIBLE);
            holder.textView2_2.setText(list.get(position).getContent());
            holder.textView2_1.setText(list.get(position).getCreated_at());
        }
        /*if (list.get(position).getStatus() == 2) {
            //已回复
            holder.linearLayout1.setVisibility(View.VISIBLE);
            holder.linearLayout2.setVisibility(View.VISIBLE);
            //自己
            holder.textView1_1.setText(list.get(position).getCreated_at());
            if (list.get(position).getPic_list() != null && list.get(position).getPic_list().size() !=0) {
                //有图片
                holder.imageView1_1.setVisibility(View.VISIBLE);
                Glide.with(context).load(IMGHOST + list.get(position).getPic_list().get(0).toString()).centerCrop().into(holder.imageView1_1);//加载图片
            } else {
                //没有图片
                holder.imageView1_1.setVisibility(View.GONE);
            }
            if (list.get(position).getContent() != null){
                if (list.get(position).getContent().equals("")) {
                    //没有文字
                    holder.textView1_2.setVisibility(View.GONE);
                } else {
                    //有文字
                    holder.textView1_2.setVisibility(View.VISIBLE);
                    holder.textView1_2.setText(list.get(position).getContent());
                }
            }else {
                //没有文字
                holder.textView1_2.setVisibility(View.GONE);
                holder.linearLayout1.setVisibility(View.GONE);
                holder.linearLayout2.setVisibility(View.VISIBLE);
            }

            if (!list.get(position).getMember().getHead().equals(""))
                Glide.with(context).load(IMGHOST + list.get(position).getMember().getHead()).centerCrop().into(holder.imageView1_2);//加载图片
            else
                holder.imageView1_2.setImageResource(R.mipmap.headimg);
            //系统 回复为空 则判断为没有回复该数据
            holder.textView2_1.setText(list.get(position).getReplay_at()+"");
            if (list.get(position).getReplay() != null){
                if (list.get(position).getReplay().equals("")) {
                    //没有文字
                    //未回复
                    holder.linearLayout1.setVisibility(View.VISIBLE);
                    holder.linearLayout2.setVisibility(View.GONE);
                } else {
                    //有文字
                    holder.textView2_2.setVisibility(View.VISIBLE);
                    holder.textView2_2.setText(list.get(position).getReplay()+"");
                }
            }else {
                //未回复
                holder.linearLayout1.setVisibility(View.VISIBLE);
                holder.linearLayout2.setVisibility(View.GONE);
            }

            if (!list.get(position).getUser().getHead().equals(""))
                Glide.with(context).load(IMGHOST+"/" + list.get(position).getUser().getHead()).centerCrop().into(holder.imageView2_1);//加载图片
            else
                holder.imageView2_1.setImageResource(R.mipmap.headimg);
        } else {
            //未回复
            holder.linearLayout1.setVisibility(View.VISIBLE);
            holder.linearLayout2.setVisibility(View.GONE);
            //自己
            holder.textView1_1.setText(list.get(position).getCreated_at());
            if (list.get(position).getPic_list() != null && list.get(position).getPic_list().size() !=0) {
                //有图片
                holder.imageView1_1.setVisibility(View.VISIBLE);
                Glide.with(context).load(IMGHOST + list.get(position).getPic_list().get(0).toString()).centerCrop().into(holder.imageView1_1);//加载图片

            } else {
                //没有图片
                holder.imageView1_1.setVisibility(View.GONE);
            }
            if (list.get(position).getContent().equals("")) {
                //没有文字
                holder.textView1_2.setVisibility(View.GONE);
            } else {
                //有文字
                holder.textView1_2.setVisibility(View.VISIBLE);
                holder.textView1_2.setText(list.get(position).getContent());
            }
            if (!list.get(position).getMember().getHead().equals(""))
                Glide.with(context).load(IMGHOST + list.get(position).getMember().getHead()).centerCrop().into(holder.imageView1_2);//加载图片
            else
                holder.imageView1_2.setImageResource(R.mipmap.headimg);
        }*/

        return convertView;
    }

    private static class ViewHolder {
        LinearLayout linearLayout1, linearLayout2,linearLayout1_1,linearLayout2_1;
        ImageView imageView1_1;
        ImageView imageView1_2, imageView2_1, imageView2_2;
        TextView textView1_1, textView1_2, textView2_1, textView2_2;
    }
}
