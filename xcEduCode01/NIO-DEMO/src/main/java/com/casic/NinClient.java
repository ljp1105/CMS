package com.casic;



import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NinClient {
    /**
     * 通道
     */
    SocketChannel channel;
    public void initClient(String host,int port) throws IOException {
        InetSocketAddress servAddr = new InetSocketAddress(host, port);
        //打开连接
        this.channel=SocketChannel.open();
        channel.connect(servAddr);
    }

    public void sendAndRecv(String words) throws IOException {
        byte[] msg = words.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(msg);
        System.out.println("Client sending:"+words);
        channel.write(buffer);
        buffer.clear();
        channel.read(buffer);
        System.out.println("client received:"+new String(buffer.array()).trim());
        channel.close();
    }

    public static void main(String[] args) throws IOException {
        NinClient ninClient = new NinClient();
        ninClient.initClient("localhost",1111);
        ninClient.sendAndRecv("I am a client");
    }
}
