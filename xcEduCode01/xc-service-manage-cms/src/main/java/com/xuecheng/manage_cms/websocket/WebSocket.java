package com.xuecheng.manage_cms.websocket;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/webSocketByTomcat/{username}")
public class WebSocket {
    private static int onlineCount = 0;//记录webSocket连接数量
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<>(); //存放每个线程对应的WebSocket
    private Session session;//通过session向客户端发送数据
    private String username;//用户名(sessionId)


    @OnOpen
    public void onOpen(@PathVariable("username")String username,Session session){
        this.username=username;
        this.session=session;
        addOnlineCount();
        clients.put(username,this);
        System.out.println("已连接");
    }
    @OnClose
    public void onClose(){
        clients.remove(username);
        subOnlineCount();
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        throwable.printStackTrace();
    }
    @OnMessage
    public void onMessage(String message){
        JSONObject jsonTo = JSONObject.parseObject(message);
        String to = jsonTo.getString("to");
        System.out.println(to);
        if (!StringUtils.equalsIgnoreCase(to,"all")){
            sendMessageTo(message,to);
        }else {
            sendMessageAll(message);
        }
    }

    public void sendMessageTo(String message,String to){
        for (WebSocket webScoket : clients.values()) {
            if (webScoket.username.equals(to)){
                webScoket.session.getAsyncRemote().sendText(message);
            }
        }
    }
    public void sendMessageAll(String message){
        for (WebSocket webScoket : clients.values()) {
            webScoket.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }

    public static synchronized Map<String, WebSocket> getClients() {
        return clients;
    }
}
