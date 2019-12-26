package com.ofc.ofc.view.chart;

import com.github.tifezh.kchartlib.chart.BaseKChartAdapter;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据适配器
 * Created by tifezh on 2016/6/18.
 */

public class KChartAdapter extends BaseKChartAdapter {

    private List<KLineEntity> datas = new ArrayList<>();

    public KChartAdapter() {

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public Date getDate(int position) {
        try {

            /*Calendar cd = Calendar.getInstance();
            cd.setTime(new Date(Long.valueOf(datas.get(position).Date)));

            Date date = new Date();
            date.setYear(cd.get(Calendar.YEAR));
            date.setMonth(cd.get(Calendar.MONTH));
            date.setDate(cd.get(Calendar.DAY_OF_MONTH));
            date.setHours(cd.get(Calendar.HOUR_OF_DAY));
            date.setMinutes(cd.get(Calendar.MINUTE));*/

            String s = CommonUtil.timedate4(datas.get(position).Date);
            MyLogger.i(">>>>>>"+s);
            String[] split = s.split("/");
            Date date = new Date();
            date.setYear(Integer.parseInt(split[0]));
            date.setMonth(Integer.parseInt(split[1]));
            date.setDate(Integer.parseInt(split[2]));
            date.setHours(Integer.parseInt(split[3]));
            date.setMinutes(Integer.parseInt(split[4]));

            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向头部添加数据
     */
    public void addHeaderData(List<KLineEntity> data) {
        if (data != null && !data.isEmpty()) {
            datas.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 向尾部添加数据
     */
    public void addFooterData(List<KLineEntity> data) {
        if (data != null && !data.isEmpty()) {
            datas.addAll(0, data);
            notifyDataSetChanged();
        }
    }

    /**
     * 改变某个点的值
     * @param position 索引值
     */
    public void changeItem(int position,KLineEntity data)
    {
        datas.set(position,data);
        notifyDataSetChanged();
    }

}