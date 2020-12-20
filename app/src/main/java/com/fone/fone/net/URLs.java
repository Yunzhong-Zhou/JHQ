package com.fone.fone.net;


/**
 * URL路径处理类
 */
public class URLs {
    //   public static String HOST = "http://app.zcashplan.com";
//    public static String HOST = "http://192.168.0.200";
    public static final String PROJECT_NAME = "";
    public static final String API = "";

    /**
     * 更新
     */
    public static final String Upgrade = "/api/article/sys-upgrade";
    /**
     * 发送验证码
     */
    public static final String Code = "/api/sms-code/send-code";
    /**
     * 登录
     */
    public static final String Login = "/api/member/login";
    /**
     * 登录 - 不是同一手机号 -授权
     */
    public static final String Login_Authorization = "/api/member/login-verify";
    /**
     * 重置密码
     */
    public static final String ForgetPassword = "/api/member/reset-password";
    /**
     * 注册
     */
    public static final String Registered = "/api/member/register";

    /**
     * 注册-极验
     */
    public static final String Registered_API1 = "/api/sms-code/send-code";

    /**
     * 实名认证1
     */
    public static final String Verified1 = "/api/auth/load";
    /**
     * 实名认证2
     */
    public static final String Verified2 = "/api/auth/check";
    /**
     * 实名认证3
     */
    public static final String Verified3 = "/api/auth/create";
    /**
     * 实名认证4 -海外
     */
    public static final String Verified4 = "https://api.253.com/open/i/witness/face-contrast";

    /**
     * 矿机
     */
    public static final String Fragment1 = "/api/like-game/rank";
    //矿机详情
    public static final String MachineDetail = "/api/like-game/rank";
    //支付详情
    public static final String PayDetail = "/api/like-game/rank";
    /**
     * 算力
     */
    public static final String Fragment2 = "/api/invest/record";

    /**
     * 首页
     */
    public static final String Fragment3 = "/api/change-game/index";
    //往期列表
    public static final String JoinList = "/api/change-game/record";
    //排行榜
    public static final String Leaderboard = "/api/change-game/rank";
    //加入拼团
    public static final String Join = "/api/change-game-participation/create";
    /**
     * 账户
     */
    public static final String Fragment4 = "/api/member/account";
    //充值
    public static final String Recharge = "/api/top-up/create";
    //USDT钱包
    public static final String USDTWallet = "/api/member/usdt-account";
    //FIL钱包
    public static final String FILWallet = "/api/member/fil-account";
    //收入记录
    public static final String ShouRuList = "/api/member/usdt-in-money-record";
    //支出记录
    public static final String ZhiChuList = "/api/member/usdt-out-money-record";
    //收入记录-fil
    public static final String ShouRuList_FIL = "/api/member/fil-in-money-record";
    //支出记录-fil
    public static final String ZhiChuList_FIL = "/api/member/fil-out-money-record";
    //充值详情
    public static final String RechargeDetail = "/api/top-up/detail";
    //取消充值
    public static final String RechargeDetail_Cancel = "/api/top-up/cancel";
    //提币
    public static final String TakeCash = "/api/withdrawal/create";
    //我的提币
    public static final String MyTakeCash = "/api/withdrawal/record";
    //我的充值
    public static final String MyRecharge = "/api/top-up/record";
    //提现详情
    public static final String TakeCashDetail = "/api/withdrawal/detail";
    //创建转币（获取可用金额）
    public static final String Transfer = "/api/transfer/create";
    //转账二维码-主页面
    public static final String QRCode = "/api/transfer/index";
    //划转
    public static final String HuaZhuan = "/api/convert/create";

    /**
     * 我的主页
     */
    public static final String Center = "/api/member/center";
    //个人信息
    public static final String Info = "/api/member/get-info";
    //修改信息
    public static final String ChangeProfile = "/api/member/update";
    //设置交易密码
    public static final String TransactionPassword = "/api/member/set-trade-password";
    //修改密码
    public static final String ChangePassword = "/api/member/update-password";
    //有奖邀请
    public static final String InvitationReward = "/api/index/share";
    //我的推广
    public static final String Share = "/api/member/team";
    //推广用户
    public static final String DirectMember = "/api/member/direct-recommend";
    //资讯
    public static final String Notice = "/api/article/notice";
    //帮助列表
    public static final String Help = "/api/article/help";
    //在线客服
    public static final String OnlineService = "/api/leave-message/record";
    //添加留言
    public static final String AddMessage = "/api/leave-message/create";
    //我的海报
    public static final String MyPoster = "/api/index/share";
    //关于
    public static final String About = "/api/index/about";
    //币地址管理
    public static final String AddressManage = "/api/member/set-wallet-addr";
    /**
     * 拼接请求路径
     *
     * @PARAM URI
     * @RETURN
     */
  /*  public static String getURL(String uri) {
        return HOST + PROJECT_NAME + API + uri;
    }*/

}
