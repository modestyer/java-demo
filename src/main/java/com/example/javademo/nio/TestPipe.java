package com.example.javademo.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author liuf
 * @create 2019-04-14 16:19
 */
public class TestPipe {

    @Test
    public void test1() throws IOException {
        Pipe pipe = Pipe.open();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("单向通道数据流".getBytes());
        Pipe.SinkChannel sinkChannel = pipe.sink();
        buffer.flip();
        sinkChannel.write(buffer);

        Pipe.SourceChannel source = pipe.source();
        buffer.flip();

        int len = source.read(buffer);

        System.out.println(new String(buffer.array(),0,len));

        source.close();
        sinkChannel.close();
    }
}
