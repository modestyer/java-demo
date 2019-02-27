package com.example.javademo.threadDemo.synchronizedDemo.reentry;

/**
 * @author liuf
 * @create 2019-02-27 22:03
 * 可重入锁也支持在父子类继承的环境中，此时子父类用的是同一把锁
 */
public class Sub extends Main{

    public synchronized void operateISubMethod(){
        System.out.println(this);
        while (i>0){
            try {
                i--;
                System.out.println("sub print i="+i);
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            this.operateIMainMethod();
        }
    }
}
