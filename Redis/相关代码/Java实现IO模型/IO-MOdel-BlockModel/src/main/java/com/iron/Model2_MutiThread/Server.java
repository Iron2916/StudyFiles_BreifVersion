package com.iron.Model2_MutiThread;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

// 问题：单线程每次只能处理一个线程，即一个客户端
public class Server {

    public static void main(String[] args) throws IOException {

        final ServerSocket server = new ServerSocket(3305);

        while (true) {

            System.out.println("--------------- 等待连接 ---------------");
            final Socket accept = server.accept();                          // 堵塞一
            System.out.println("--------------- 连接成功 ---------------");

            // 多线程进行处理
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {

                        final InputStream inputStream = accept.getInputStream();        // 堵塞二
                        final byte[] bytes = new byte[1024];
                        int len = 0;
                        System.out.println("--------------- 准备接受数据 ---------------");
                        while ( (len = inputStream.read(bytes) )!= -1) {                // 阻塞三：单线程，等待用户处理完，才能连接下一个用户

                            final String string = new String(bytes, 0, len);
                            System.out.println("服务器接受到消息：" + string);
                        }

                        inputStream.close();
                        accept.close();
                    } catch ( Exception e) {

                        e.printStackTrace();
                    }
                }
            }).start();


        }


    }
}
