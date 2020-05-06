package com.coding.interview;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class T10_PipedStream {

    public static void main(String[] args) throws Exception {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();
        PipedOutputStream output1 = new PipedOutputStream();
        PipedOutputStream output2 = new PipedOutputStream();

        input1.connect(output2);
        input2.connect(output1);

        String msg = "Year Turn";

        new Thread(() -> {
            byte[] buffer = new byte[9];
            try {
                for (char c: aI){
                    input1.read(buffer);
                    if (new String(buffer).equals(msg)){
                        System.out.println(c);
                    }
                    output1.write(msg.getBytes());
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }, "t1").start();

        new Thread(() -> {
            byte[] buffer = new byte[9];
            try {
                for (char c: aC) {
                    System.out.println(c);
                    output2.write(msg.getBytes());
                    input2.read(buffer);
                    if (new String(buffer).equals(msg)){
                        continue;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }, "t2").start();

    }
}
