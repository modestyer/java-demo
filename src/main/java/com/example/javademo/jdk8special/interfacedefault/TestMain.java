package com.example.javademo.jdk8special.interfacedefault;

/**
 * @author liuf
 * @create 2019-05-08 21:10
 */
public class TestMain {

    public static void main(String[] args) {

        Formula formula1 = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a);
            }
        };

        System.out.println(formula1.calculate(100));
        System.out.println(formula1.sqrt(16));

        FormulaImpl formula  = new FormulaImpl();

        double sqrt = formula.sqrt(16);
        System.out.println(sqrt);
    }
}
