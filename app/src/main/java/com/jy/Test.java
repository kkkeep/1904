package com.jy;

/*
 * created by Cherry on 2019-11-25
 **/
public class Test {

    public static void main(String args[]) {
        Parent parent = new Child();
        parent.print();
    }


    public static abstract class Parent {

        protected void print() {
            sayHi();
        }

        protected void sayHi() {
            System.out.println("i am a old man");
        }

    }

    public static class Child extends Parent {
        @Override
        protected void sayHi() {
            super.sayHi();
            System.out.println("i am a little cute girl");
        }
    }
}
