package com.coding.interview;

public class T06_cas {
    enum ReadyToRun {T1, T2}

    static volatile ReadyToRun r = ReadyToRun.T1;

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        /**
         * 模拟自旋锁
         */
        new Thread(() -> {
            for (char c: aI){
                while (r != ReadyToRun.T1){}
                System.out.print(c);
                r = ReadyToRun.T2;
            }
        }, "t1").start();

        new Thread(() -> {
            for (char c: aC){
                while (r != ReadyToRun.T2){}
                System.out.print(c);
                r = ReadyToRun.T1;
            }
        }, "t2").start();


    }
}
