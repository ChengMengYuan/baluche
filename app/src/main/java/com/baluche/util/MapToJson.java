package com.baluche.util;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class MapToJson {

    @NonNull
    public static String ToJson(HashMap hashMap) {
        String AllStr = "{";
        Iterator<Map.Entry<String, String>> it = hashMap.entrySet().iterator();


        for (int i = 0; i < hashMap.size(); i++) {
            Map.Entry next = it.next();
            AllStr = AllStr + "\"" + next.getKey() + "\"";
            AllStr = AllStr + ":";
            AllStr = AllStr + "\"" + next.getValue() + "\"";
            if (i != hashMap.size() - 1) {
                AllStr = AllStr + ",";
            } else {

            }
        }


        //        while (it.hasNext()) {
        //            Map.Entry<String, String> next = it.next();
        //            AllStr = AllStr + "\"" + next.getKey() + "\"";
        //            AllStr = AllStr + ":";
        //            AllStr = AllStr + "\"" + next.getValue() + "\"";
        //            AllStr = AllStr + ",";
        //        }
        AllStr = AllStr + "}";
        Log.d("AllStr", "" + AllStr);
        return AllStr;
    }
}
