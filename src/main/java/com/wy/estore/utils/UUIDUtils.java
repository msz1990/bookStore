package com.wy.estore.utils;

import java.util.UUID;

//Random String
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
