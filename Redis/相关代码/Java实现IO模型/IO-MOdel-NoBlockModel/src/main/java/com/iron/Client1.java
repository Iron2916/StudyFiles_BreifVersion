package com.iron;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {

    public static void main(String[] args) throws IOException {

        final Socket socket = new Socket("127.0.0.1", 3305);
        final OutputStream outputStream = socket.getOutputStream();

        while (true) {

            final Scanner scanner = new Scanner(System.in);
            final String input = scanner.next();

            if (input.equals("quit")) {

                break;
            }
            outputStream.write(input.getBytes());
        }

        socket.close();
    }
}
