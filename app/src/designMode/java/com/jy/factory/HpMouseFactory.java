package com.jy.factory;

/*
 * created by Cherry on 2019-11-21
 **/
public class HpMouseFactory implements MouseFactory {
    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }
}
