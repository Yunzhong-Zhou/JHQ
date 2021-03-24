package com.ghzk.ghzk.okhttp.websocket;

/**
 * Created by zyz on 2020/6/24.
 */
public interface IReceiveMessage {
    void onConnectSuccess();// 连接成功

    void onConnectFailed();// 连接失败

    void onClose(); // 关闭

    void onMessage(String text);
}
