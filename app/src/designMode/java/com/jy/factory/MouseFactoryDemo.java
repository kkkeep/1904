package com.jy.factory;

/*
 * created by Cherry on 2019-11-21
 **/
public class MouseFactoryDemo {


    public static void main(String args []){

        MouseFactory factory  = new HwMouseFactory();

        createMouse(factory);

    }


    public static void createMouse(MouseFactory factory){

        Mouse mouse = factory.createMouse();


        mouse.sayHi();
    }


}
