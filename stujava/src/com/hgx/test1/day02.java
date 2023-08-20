package com.hgx.test1;

public class day02 {

    public static void main(String[] args) {
        byte b1 = 12;
        int i1 = 100 ;
        //byte b2 = b1 + i1;//编译不通过
        int i2 = b1 + i1;
        long l2 = b1 + i1;
        double d2 = b1 + i1;
        System.out.println(i2);
        System.out.println(l2);
        System.out.println(d2);


        char c1 = 'a';//c1的值为97
        short s1 = 3;
        //short s2 = c1 + s1;//编译不通过
        int i3 = s1 + c1;
        System.out.println(i3);

        byte b2 = 3;
        //char c2 = c1 + b2;//编译不通过
        //byte b3 = s1 + b2;//编译不通过


        double d1 = 12.3;

        int i6 = (int)d1;//四舍五入，会导致精度损失。
        System.out.println(i6);

        long l1 = 123l;
        short s6 = (short)l1;
        System.out.println(s6);

        int i7 = 128;//精度损失
        byte b6 = (byte) i7;
        System.out.println(b6);

    }
}
