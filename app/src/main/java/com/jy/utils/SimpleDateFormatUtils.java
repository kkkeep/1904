package com.jy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * created by Cherry on 2019-12-04
 **/
public class SimpleDateFormatUtils {

    private static final String  FROMAT_1 = "YYYY-MM-DD hh:mm:ss";
    public static  String formatFromLong(long date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FROMAT_1);

        return simpleDateFormat.format(new Date(date));
    }
}
