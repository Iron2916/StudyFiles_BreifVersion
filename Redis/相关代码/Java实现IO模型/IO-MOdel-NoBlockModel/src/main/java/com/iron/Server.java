package com.iron;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static List<SocketChannel> container = new ArrayList<>();
    private static ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws IOException {

        System.out.println("------------------- 等待非阻塞IO启动 ------------------------");
        final ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 3305));
        serverSocket.configureBlocking(false); // 设置非阻塞


        while (true) {

            // 非阻塞等待：空转进行判断那个Socket发送过来了数据
            for (SocketChannel channel : container) {

                final int read = channel.read(byteBuffer);
                if (read > 0) {

                    byteBuffer.flip(); // 从读取切换到写入
                    final byte[] bytes = new byte[read];
                    byteBuffer.get(bytes);
                    System.out.println(new String(bytes));
                    byteBuffer.clear(); // 清除数据
                }
            }

            // 非阻塞连接
            final SocketChannel accept = serverSocket.accept();
            if (accept != null) {

                System.out.println("------------------- 连接成功 ------------------------");
                accept.configureBlocking(false);    // 非阻塞连接
                container.add(accept);
            }

        }
    }
}
