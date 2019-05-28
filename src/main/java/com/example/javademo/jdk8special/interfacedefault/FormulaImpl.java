package com.example.javademo.jdk8special.interfacedefault;

/**
 * @author liuf
 * @create 2019-05-08 21:10
 */
public class FormulaImpl implements Formula{

    @Override
    public double calculate(int a) {
        return Math.sqrt(a);
    }
}
