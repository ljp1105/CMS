package com.casic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatterAndGatherTest {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("D:\\新建文本文档.txt");
        FileChannel fileChannel = inputStream.getChannel();
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);
        ByteBuffer []buffers={header,body};
        fileChannel.read(buffers);

        fileChannel.write(buffers);
    }
}
