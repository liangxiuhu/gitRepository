package com.lxh.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ConnectionJsoupUtil {

    /**
     *
     * @param address
     * @return
     */
    public static String connection(String address){
        //Jsoup：用来对HTML进行解析

        Document doc = Jsoup.parse(address);

        return "";
    }
}
