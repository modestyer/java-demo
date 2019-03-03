package com.example.javademo.bianrydemo;

/**
 * @author liuf
 * @create 2019-03-03 11:17
 */
public class TestBinary {

    public static void main(String[] args) {
//        getBinary();

//        getMaxAndMin();

        getTest10();
    }


    /**
     * 展示用十六进制和八进制计数法来操作Long值，用long.toBinaryString()来显示结果。
     */
    public static void getBinary(){
        long a = 0x4f;
        long b = 0x5a;
        long sum = a+b;
        System.out.println("十六进制的二进制表示为："+Long.toBinaryString(sum));

        long c = 056;
        long d = 017;
        long sum2 = c+d;
        System.out.println("八进制的二进制表示为："+Long.toBinaryString(sum2));
    }


    /**
     * 得到float和double的指数计数法所能表示最大值和最小值
     */
    public static void getMaxAndMin(){
        System.out.println("float的最大值"+Float.MAX_VALUE);
        System.out.println("float的最小值"+Float.MIN_VALUE);
        System.out.println("double的最大值"+Double.MAX_VALUE);
        System.out.println("double的最小值"+Double.MIN_VALUE);
    }

    /**
     * 编写一个具有两个常量值得程序，一个具有交替的二进制位1和0，其中最低有效位为0，另一个也具有交替的二进制位1和0，
     * * 但是最低有效位为1（提示：使用十六进制常量来表示是最简单的方法）。
     * 取这两个值，用按位操作符以所有可能的方式结合运算他们，
     * * 然后用Integer.toBinaryString（）显示。

     */
    public static void getTest10(){
        int a = 0x40;
        int b = 0xf551;

        System.out.println("与操作结果为："+Integer.toBinaryString(a&b));
        System.out.println("或操作结果为："+Integer.toBinaryString(a|b));
        System.out.println("异或操作结果为："+Integer.toBinaryString(a^b));



    }
}
