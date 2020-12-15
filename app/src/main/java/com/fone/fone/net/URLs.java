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
     * 新人领取
     */
    public static final String NewcomerReward = "/api/member/sign";
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
    //学院
    /**
     * 学院
     */
    public static final String School = "/api/article/college";
    /**
     * 合约
     */
    public static final String HeYue = "/api/change-game/index";
    /**
     * 合约-买入
     */
    public static final String HeYue_Add = "/api/change-game-participation/create";
    /**
     * 合约-详情
     */
    public static final String HeYue_Detail = "/api/change-game/detail";
    //区块
    /**
     * 区块
     */
    public static final String Fragment2 = "/api/forecast/record";
    /**
     * 预测详情
     */
    public static final String PredictionDetail = "/api/forecast/detail";
    /**
     * 购买区块
     */
    public static final String BuyQuKuai = "/api/block/create";
    //首页
    /**
     * 首页
     */
    public static final String Fragment3 = "/api/index";
    /**
     * 转出
     */
    public static final String Transfer_Cancel = "/api/contract/terminate";
    /**
     * 终止合约
     */
    public static final String Transfer_ZhongZhi = "/api/contract/make-terminate";
    /**
     * 创建合约
     */
    public static final String Transfer_Add = "/api/contract/create";
    /**
     * 合约记录
     */
    public static final String Transfer_List = "/api/contract/record";
    /**
     * 合约详情
     */
    public static final String Transfer_Detail = "/api/contract/detail";
    /**
     * 往期列表
     */
    public static final String PastList = "/api/like-game/history";
    /**
     * 排行榜
     */
    public static final String Leaderboard = "/api/like-game/rank";
    /**
     * 详情
     */
    public static final String GameDetail = "/api/like-game/detail";
    //充值
    /**
     * 充值
     */
    public static final String Fragment4 = "/api/top-up/create";
    // 用户模块
    /**
     * 我的主页
     */
    public static final String Center = "/api/member/center";
    /**
     * 个人信息
     */
    public static final String Info = "/api/member/get-info";
    /**
     * 修改信息
     */
    public static final String ChangeProfile = "/api/member/update";
    /**
     * 设置交易密码
     */
    public static final String TransactionPassword = "/api/member/set-trade-password";
    /**
     * 修改密码
     */
    public static final String ChangePassword = "/api/member/update-password";
    /**
     * 钱包地址（get）
     */
    public static final String WalletAddress = "/api/member/set-wallet-addr";
    /**
     * 账户详情
     */
    public static final String OFCAccountDetail = "/api/invest/account";
    /**
     * 分红主页
     */
    public static final String FenHong = "/api/invest/index";
    /**
     * 分红主页-chart
     */
    public static final String FenHong_Chart = "/api/invest/k-line-chart";
    /**
     * 买入分红
     */
    public static final String AddFenHong = "/api/invest/create";
    /**
     * 分红详情
     */
    public static final String FenHongDetail = "/api/invest/detail";
    /**
     * 质押OFC
     */
    public static final String ZhiYaOFC = "/api/invest/pledge";
    /**
     * 回购OFC
     */
    public static final String HuiGouOFC = "/api/invest/buy-back";
    /**
     * 回购列表
     */
    public static final String ShouRuList = "/api/buy-back/record";
    /**
     * 账户详情
     */
    public static final String AccountDetail = "/api/member/account";
    /**
     * 我的充值
     */
    public static final String MyRecharge = "/api/top-up/record";
    /**
     * 充值详情
     */
    public static final String RechargeDetail = "/api/top-up/detail";
    /**
     * 取消充值
     */
    public static final String RechargeDetail_Cancel = "/api/top-up/cancel";
    /**
     * 提币（get）
     */
    public static final String TakeCash = "/api/withdrawal/create";
    /**
     * 提现列表
     */
    public static final String MyTakeCash = "/api/withdrawal/record";
    /**
     * 有奖邀请
     */
    public static final String InvitationReward = "/api/index/share";
    /**
     * 提现详情
     */
    public static final String TakeCashDetail = "/api/withdrawal/detail";
    /**
     * 服务中心
     */
    public static final String ServiceCenter = "/api/service-center/index";
    /**
     * 服务中心-列表
     */
    public static final String ServiceCenter_List = "/api/service-center/record";
    /**
     * 服务中心-修改
     */
    public static final String ServiceCenter_Change = "/api/member/service-code-update";
    /**
     * 我的推广
     */
    public static final String Share = "/api/member/team";
    /**
     * 推广用户
     */
    public static final String DirectMember = "/api/member/direct-recommend";
    /**
     * 推广用户
     */
    public static final String DirectMember_OFC = "/api/invest/direct-recommend";
    /**
     * 资讯
     */
    public static final String Notice = "/api/article/notice";
    /**
     * 帮助列表
     */
    public static final String Help = "/api/article/help";
    /**
     * 在线客服
     */
    public static final String OnlineService = "/api/leave-message/record";
    /**
     * 添加留言
     */
    public static final String AddMessage = "/api/leave-message/create";
    /**
     * 我的海报
     */
    public static final String MyPoster = "/api/index/share";
    /**
     * 创建转币（获取可用金额）
     */
    public static final String Transfer = "/api/transfer/create";
    /**
     * 转币记录
     */
    public static final String TransferRecord = "/api/transfer/record";
    /**
     * 银行卡设置
     */
    public static final String Collection = "/api/member/set-proceeds";
    /**
     * 银行卡设置_验证码
     */
    public static final String Collection_code = "/api/sms-code/send-code";

    /**
     * DRVT交易列表
     */
    public static final String DRVTJiaoYiList1 = "/api/drvt-buy/index";
    /**
     * DRVT交易列表-我的购买
     */
    public static final String DRVTJiaoYiList2 = "/api/drvt-buy/my-buy";
    /**
     * 交易列表
     */
    public static final String JiaoYiList = "/api/drvt-sell/index";
    /**
     * DRVT购买
     */
    public static final String DRVTBuy = "/api/drvt-buy/create";
    /**
     * DRVT购买-取消
     */
    public static final String DRVTCancel = "/api/drvt-buy/cancel";
    /**
     * DRVT出售
     */
    public static final String DRVTSell = "/api/drvt-sell/create";
    /**
     * 推广用户
     */
    public static final String DirectMember_DRVT = "/api/drvt-buy/direct-recommend";

    /**
     * USDT交易列表
     */
    public static final String USDTJiaoYiList1 = "/api/usdt-deal";
    /**
     * USDT交易列表-我的购买
     */
    public static final String USDTJiaoYiList2 = "/api/usdt-deal/my-buy";
    /**
     * USDT购买-取消
     */
    public static final String USDTCancel = "/api/usdt-deal/cancel";
    /**
     * USDT出售
     */
    public static final String USDTSell = "/api/usdt-deal/sell";
    /**
     * USDT购买
     */
    public static final String USDTBuy = "/api/usdt-deal/buy";
    /**
     * USDT交易列表
     */
    public static final String USDTJiaoYiList = "/api/usdt-deal/record";
    /**
     * USDT交易详情
     */
    public static final String USDTJiaoYiDetail = "/api/usdt-deal/detail";
    /**
     * 申诉
     */
    public static final String ShenShu = "/api/usdt-deal/appeal";
    /**
     * 确认付款
     */
    public static final String ConfirmPay = "/api/usdt-deal/has-pay";
    /**
     * 确认收款
     */
    public static final String ShouKuan = "/api/usdt-deal/finish";
    /**
     * 关于
     */
    public static final String About = "/api/index/about";
    /**
      * 币地址管理
     */
    public static final String AddressManage = "/api/withdrawal/type";
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
