package com.casic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("D:\\新建文本文档.txt");
        FileChannel fileChannel = inputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(inputStream.available());
        int bytesRead = fileChannel.read(buffer);
        while (bytesRead!=-1){
            System.out.println("Read"+bytesRead);
            buffer.flip();
            while (buffer.hasRemaining()){
                System.out.println((char) buffer.get());
            }
            buffer.clear();
            bytesRead = fileChannel.read(buffer);
        }
        inputStream.close();

    }
}
