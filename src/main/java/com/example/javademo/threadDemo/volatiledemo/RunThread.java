package com.example.javademo.threadDemo.volatiledemo;

import lombok.Data;

/**
 * @author liuf
 * @create 2019-02-28 21:56
 * volatile保证变量在线程之间的可见性。使线程强制读取主存里变量的值
 * 1、在当前的 Java 内存模型下，线程可以把变量保存本地内存（比如机器的寄存器）中，而不是直接在主存中进行读写。
 * 这就可能造成一个线程在主存中修改了一个变量的值，而另外一个线程还继续使用它在寄存器中的变量值的拷贝，
 * 造成数据的不一致。
 *
 * 2、volatile 修饰的成员变量在每次被线程访问时，都强迫从主存（共享内存）中重读该成员变量的值。
 * 而且，当成员变量发生变化时，强迫线程将变化值回写到主存（共享内存）。这
 * 样在任何时刻，两个不同的线程总是看到某个成员变量的同一个值，这样也就保证了同步数据的可见性。
 *
 * 3、JVM会尽力保证内存的可见性，即便这个变量没有加同步关键字。
 */
@Data
public class RunThread extends Thread{

    private volatile boolean isRunning = true;
    int m;

    @Override
    public void run() {
        super.run();
        System.out.println("进入run了");
        while (isRunning){
            int a=2;
            int b=3;
            int c=a+b;
            m=c;
//            System.out.println(m);
            /*try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        System.out.println(m);
        System.out.println("线程被停止了！");
    }
}
