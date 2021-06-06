package com.casic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TransferFromAndTransferToTest {
    public static void main(String[] args) {

    }
    public void test01() throws IOException {
        FileInputStream is1 = new FileInputStream("D:\\新建文本文档.txt");
        FileChannel fromChannel = is1.getChannel();
        FileInputStream is2 = new FileInputStream("D:\\新建文本文档.txt");
        FileChannel toChannel = is2.getChannel();
        long position=0;
        long count=fromChannel.size();
        toChannel.transferFrom(fromChannel,position,count);
    }


    public void test02() throws IOException {
        FileInputStream is1 = new FileInputStream("D:\\新建文本文档.txt");
        FileChannel  fromChannel= is1.getChannel();
        FileInputStream is2 = new FileInputStream("D:\\新建文本文档.txt");
        FileChannel toChannel = is2.getChannel();
        long position=0;
        long count=fromChannel.size();
        fromChannel.transferTo(position,count,toChannel);
    }
}
