package com.baluche.http.http;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.HashMap;

import static com.baluche.util.EncryptUtil.Getsign;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class Parameter {

    private HashMap kmap = new HashMap();//需要签名校验的sign
    private HashMap pMap = new HashMap();//最后传的参数
    private String time = Calendar.getInstance().getTimeInMillis() + "";
    private Gson gson = new Gson();

    public String returndefaultParame() {
        kmap.put("time", time);

        pMap.put("sign", Getsign(kmap));
        pMap.put("time", time);
        String s = gson.toJson(pMap);
        return s;
    }


}
