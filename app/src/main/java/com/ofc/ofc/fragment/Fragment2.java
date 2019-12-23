package com.ofc.ofc.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.activity.MainActivity;
import com.ofc.ofc.base.BaseFragment;
import com.ofc.ofc.model.Fragment2Model;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.MyLogger;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 区块
 */
public class Fragment2 extends BaseFragment {
    private RecyclerView recyclerView;
    List<Fragment2Model> list1 = new ArrayList<>();
    CommonAdapter<Fragment2Model> mAdapter1;

    List<String>stringList = new ArrayList<>();

    private String userName = "user";
    private String passWord = "SZUI78*AAQa";
    private String virtualHost = "/";
    private String hostName = "221.122.37.70";
    private int portNum = 5672;
    private String queueName = "bsTradingPoint";//接消息name
    private String exchangeName = "BsTradingPoint";//发消息name
    private String rountingKey = "key";
    ConnectionFactory factory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        StatusBarUtil.setTransparent(getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        /*if (MainActivity.item == 1) {
            requestServer();
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.item == 1) {
            requestServer();
        }
    }

    /*@Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (MainActivity.item == 1) {
            requestServer();
        }
    }*/
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 1) {
                    requestServer();
                }
            }
        }
    }

    @Override
    protected void initView(View view) {
//        findViewByID_My(R.id.headview).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                String string = "?token=" + localUserInfo.getToken();
                Request(string);
            }

            @Override
            public void onLoadmore() {

            }
        });


        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);

        for (int i = 0; i < 10; i++) {
            list1.add(new Fragment2Model());
        }
        mAdapter1 = new CommonAdapter<Fragment2Model>
                (getActivity(), R.layout.item_fragment2, list1) {
            @Override
            protected void convert(ViewHolder holder, final Fragment2Model model, int position) {
                /*LineChart lineChart = holder.getView(R.id.lineChart);
                initChart(lineChart);
                showLineChart(model.getMoneylist(),"",getResources().getColor(R.color.white),lineChart);*/

                /*holder.setText(R.id.textView1, model.getMember_nickname());
                holder.setText(R.id.textView2, model.getMoney() + getString(R.string.app_ge));
                holder.setText(R.id.textView3, model.getCreated_at());
                ImageView imageView1 = holder.getView(R.id.imageView1);
                if (!model.getMember_head().equals(""))
                    Glide.with(getActivity())
                            .load(IMGHOST + model.getMember_head())
                            .centerCrop()
//                                    .placeholder(R.mipmap.headimg)//加载站位图
//                                    .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片
                else
                    imageView1.setImageResource(R.mipmap.headimg);*/

            }
        };
        /*//listview 滑动监听
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mLinearLayoutManager.findFirstVisibleItemPosition() >= 1) {
                    invis.setVisibility(View.VISIBLE);
                    headerView2.setVisibility(View.GONE);
                } else {
                    invis.setVisibility(View.GONE);
                    headerView2.setVisibility(View.VISIBLE);
                }
            }
        });*/

    }

    @Override
    protected void initData() {
//        requestServer();
       /* TextView textView = findViewByID_My(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        basicPublish();//发消息
                    }
                }).start();
            }
        });*/
        /*//用于从线程中获取数据，更新ui
        final Handler incomingMessageHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String message = msg.getData().getString("msg");
                MyLogger.i("test>>>>>>>", "msg:" + message);
                myToast("收到消息"+message);
                stringList.add(message);
            }
        };
        //连接设置
        setupConnectionFactory();

        //开启消费者线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                basicConsume(incomingMessageHandler);
            }
        }).start();*/
        /*BigDecimal bd = new BigDecimal("1.5770196E+12");
        String str = bd.toPlainString();
        MyLogger.i(">>>>>>"+str);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(Long.valueOf(str));
        MyLogger.i(">>>>>>"+ simpleDateFormat.format(date));*/
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        /*showProgress(true, getString(R.string.app_loading));
        String string = "?token=" + localUserInfo.getToken();
        Request(string);*/





    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment2 + string, new OkHttpClientManager.ResultCallback<Fragment2Model>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                MainActivity.isOver = true;
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(final Fragment2Model response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>区块" + response);

                hideProgress();

                MainActivity.isOver = true;
            }
        });
    }

    /**
     * ************************************初始化图表***********************************************
     */
    private void initChart(LineChart lineChart) {
        /***图表设置***/
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //是否显示边界
        lineChart.setDrawBorders(true);
        //是否可以拖动
        lineChart.setDragEnabled(false);
        //是否有触摸事件
        lineChart.setTouchEnabled(true);
        //设置XY轴动画效果
        lineChart.animateY(2500);
        lineChart.animateX(1500);

        /***XY轴的设置***/
        /*xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinimum(0f);
        rightYaxis.setAxisMinimum(0f);*/

        /***折线图例 标签 设置***/
        /*legend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);*/
    }

    /**
     * 曲线初始化设置 一个LineDataSet 代表一条曲线
     *
     * @param lineDataSet 线条
     * @param color       线条颜色
     * @param mode
     */
    private void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        //设置折线图填充
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        if (mode == null) {
            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }
    }

    /**
     * 展示曲线
     *
     * @param dataList 数据集合
     * @param name     曲线名称
     * @param color    曲线颜色
     */
    public void showLineChart(List<Fragment2Model> dataList, String name, int color, LineChart lineChart) {
        ArrayList arrayList = new ArrayList();
        /*for (int i = 0; i < dataList.size(); i++) {
            arrayList.add(new Entry(i, dataList.get(i)));
        }*/
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(arrayList, name);
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
    }


    /**
     * *************************************长连接**************************************************
     */
    /**
     * 连接设置
     */
    private void setupConnectionFactory() {
        factory = new ConnectionFactory();
        factory.setHost(hostName);
        factory.setPort(portNum);
        factory.setUsername(userName);
        factory.setPassword(passWord);
        factory.setAutomaticRecoveryEnabled(true);// 设置连接恢复

    }

    /**
     * 收消息（从发布者那边订阅消息）
     */
    private void basicConsume(final Handler handler) {
        try {
            //连接
            Connection connection = factory.newConnection();
            //通道
            final Channel channel = connection.createChannel();

            //实现Consumer的最简单方法是将便捷类DefaultConsumer子类化。可以在basicConsume 调用上传递此子类的对象以设置订阅：
            //队列名称、
            channel.basicConsume(queueName, false, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    super.handleDelivery(consumerTag, envelope, properties, body);

                    String msg = new String(body);
                    long deliveryTag = envelope.getDeliveryTag();
                    channel.basicAck(deliveryTag, false);
                    //从message池中获取msg对象更高效
                    Message uimsg = handler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", msg);
                    uimsg.setData(bundle);
                    handler.sendMessage(uimsg);

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发消息
     */
    private void basicPublish(){

        try {
            //连接
            Connection connection = factory.newConnection() ;
            //通道
            Channel channel = connection.createChannel() ;
            //声明了一个交换和一个服务器命名的队列，然后将它们绑定在一起。
            channel.exchangeDeclare(exchangeName , "fanout" , true) ;
            String queueName = channel.queueDeclare().getQueue() ;
            channel.queueBind(queueName , exchangeName , rountingKey) ;
            //消息发布
            byte[] msg = "hello word!".getBytes() ;
            channel.basicPublish(exchangeName , rountingKey  ,null , msg);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
    /**
     * 收消息
     */
    private void basicConsume(){

        try {
            //连接
            Connection connection = factory.newConnection() ;
            //通道
            final Channel channel = connection.createChannel() ;
            //声明了一个交换和一个服务器命名的队列，然后将它们绑定在一起。
            channel.exchangeDeclare(exchangeName , "fanout" , true) ;
            String queueName = channel.queueDeclare().getQueue() ;
            Log.e("TAG" , queueName + " :queueName") ;
            channel.queueBind(queueName , exchangeName , rountingKey) ;
            //实现Consumer的最简单方法是将便捷类DefaultConsumer子类化。可以在basicConsume 调用上传递此子类的对象以设置订阅：
            channel.basicConsume(queueName , false , "administrator" , new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    super.handleDelivery(consumerTag, envelope, properties, body);

                    String rountingKey = envelope.getRoutingKey() ;//路由密钥
                    String contentType = properties.getContentType() ;//contentType字段，如果尚未设置字段，则为null。
                    String msg = new String(body,"UTF-8") ;//接收到的消息
                    long deliveryTag = envelope.getDeliveryTag() ;//交付标记

                    Log.e("TAG" , rountingKey+"：rountingKey") ;
                    Log.e("TAG" , contentType+"：contentType") ;
                    Log.e("TAG" , msg+"：msg") ;
                    Log.e("TAG" , deliveryTag+"：deliveryTag") ;
                    Log.e("TAG" , consumerTag+"：consumerTag") ;
                    Log.e("TAG" , envelope.getExchange()+"：exchangeName") ;

                    channel.basicAck(deliveryTag , false);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
