package com.jy;

/*
 * created by Cherry on 2019-11-22
 **/
public class OuterClass {

    private int a = 100;


    private void foo(){
        System.out.println("foo");
    }



    public class InnerClass{

        private OuterClass outerClass;

        public InnerClass(){

        }

        public InnerClass(OuterClass outerClass){
            this.outerClass = outerClass;
        }

        private void f2(){
           outerClass.a = 200;
            outerClass.foo();

        }
    }
}
