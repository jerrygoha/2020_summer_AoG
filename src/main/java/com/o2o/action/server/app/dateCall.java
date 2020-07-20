package com.o2o.action.server.app;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class dateCall {

    private Date now;
    private int[] date = new int[70];
    private int[][] schedule;

    private String strr = "[\n" +
            "   {\n" +
            "      \"DATE\":20200617,\n" +
            "      \"T1\":2,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":2,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":1,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":1\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200618,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":1,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":2,\n" +
            "      \"HLE\":2,\n" +
            "      \"DYN\":1,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200619,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":2,\n" +
            "      \"DRX\":2,\n" +
            "      \"DWG\":1,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":1\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200620,\n" +
            "      \"T1\":2,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":2,\n" +
            "      \"DWG\":1,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":1\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200621,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":1,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":2,\n" +
            "      \"KT\":1,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":2,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200622,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200623,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200624,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":2,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":1,\n" +
            "      \"HLE\":2,\n" +
            "      \"DYN\":1,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200625,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":2,\n" +
            "      \"DRX\":1,\n" +
            "      \"DWG\":2,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":1\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200626,\n" +
            "      \"T1\":1,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":1,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":2,\n" +
            "      \"DYN\":2,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200627,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":1,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":2,\n" +
            "      \"KT\":2,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":1,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200628,\n" +
            "      \"T1\":2,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":1,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":1,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":2\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200629,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200630,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200701,\n" +
            "      \"T1\":1,\n" +
            "      \"GEN\":2,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":1,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":2\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200702,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":2,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":1,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":1,\n" +
            "      \"DYN\":2,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200703,\n" +
            "      \"T1\":2,\n" +
            "      \"GEN\":2,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":1,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":1,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200704,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":1,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":1,\n" +
            "      \"DYN\":2,\n" +
            "      \"SB\":2\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200705,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":1,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":2,\n" +
            "      \"AF\":2,\n" +
            "      \"SP\":1,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200706,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200707,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200708,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":1,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":1,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":2,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":2\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200709,\n" +
            "      \"T1\":1,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":2,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":2,\n" +
            "      \"SP\":1,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200710,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":1,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":2,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":1,\n" +
            "      \"SB\":2\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200711,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":1,\n" +
            "      \"DWG\":1,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":2,\n" +
            "      \"SP\":2,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200712,\n" +
            "      \"T1\":1,\n" +
            "      \"GEN\":2,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":2,\n" +
            "      \"DYN\":1,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200713,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200714,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200715,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":1,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":2,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":1,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":2,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200716,\n" +
            "      \"T1\":1,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":1,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":2,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":2\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200717,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":1,\n" +
            "      \"DWG\":2,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":2,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":1,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200718,\n" +
            "      \"T1\":2,\n" +
            "      \"GEN\":2,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":1,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":1\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200719,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":2,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":1,\n" +
            "      \"HLE\":1,\n" +
            "      \"DYN\":2,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200720,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200721,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200722,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200723,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":2,\n" +
            "      \"DRX\":1,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":1,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":2,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200724,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":1,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":2,\n" +
            "      \"SP\":2,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":1\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200725,\n" +
            "      \"T1\":1,\n" +
            "      \"GEN\":2,\n" +
            "      \"DRX\":2,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":1,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200726,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":2,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":1,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":2,\n" +
            "      \"SB\":1\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200727,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200728,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200729,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200730,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":2,\n" +
            "      \"DWG\":2,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":1,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":1,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200731,\n" +
            "      \"T1\":1,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":1,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":2,\n" +
            "      \"SB\":2\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200801,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":1,\n" +
            "      \"DRX\":2,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":2,\n" +
            "      \"SP\":1,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200802,\n" +
            "      \"T1\":2,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":1,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":1,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":2\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200803,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200804,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200805,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200806,\n" +
            "      \"T1\":2,\n" +
            "      \"GEN\":1,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":1,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":2,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200807,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":2,\n" +
            "      \"AF\":1,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":2,\n" +
            "      \"DYN\":1,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200808,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":1,\n" +
            "      \"DWG\":2,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":2,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":1\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200809,\n" +
            "      \"T1\":1,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":1,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200810,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200811,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200812,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200813,\n" +
            "      \"T1\":2,\n" +
            "      \"GEN\":1,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":2,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":1\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200814,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":2,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":1,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":1,\n" +
            "      \"HLE\":2,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200815,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":1,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":2,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":1,\n" +
            "      \"SB\":2\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200816,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":1,\n" +
            "      \"DRX\":2,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":2,\n" +
            "      \"HLE\":1,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200817,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200818,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200819,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200820,\n" +
            "      \"T1\":1,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":1,\n" +
            "      \"KT\":2,\n" +
            "      \"AF\":2,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200821,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":2,\n" +
            "      \"HLE\":1,\n" +
            "      \"DYN\":2,\n" +
            "      \"SB\":1\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200822,\n" +
            "      \"T1\":0,\n" +
            "      \"GEN\":1,\n" +
            "      \"DRX\":0,\n" +
            "      \"DWG\":2,\n" +
            "      \"KT\":2,\n" +
            "      \"AF\":1,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":0,\n" +
            "      \"DYN\":0,\n" +
            "      \"SB\":0\n" +
            "   },\n" +
            "   {\n" +
            "      \"DATE\":20200823,\n" +
            "      \"T1\":1,\n" +
            "      \"GEN\":0,\n" +
            "      \"DRX\":1,\n" +
            "      \"DWG\":0,\n" +
            "      \"KT\":0,\n" +
            "      \"AF\":0,\n" +
            "      \"SP\":0,\n" +
            "      \"HLE\":2,\n" +
            "      \"DYN\":2,\n" +
            "      \"SB\":0\n" +
            "   }\n" +
            "]";
    public dateCall() {

    }

    public String calcDate(Date currents) {

        this.now = currents;

        String result = null;

        //현재 날짜 저장
        try {
            SimpleDateFormat nowTime = new SimpleDateFormat("yyyyMMdd");
            Date current = new Date();
            String currentDateSt = nowTime.format(now);

            Date inputDate = nowTime.parse(currentDateSt);

            //날짜 비교
            int diffday;
            int compare = current.compareTo(inputDate);
            if (compare < 0) { //미래
                diffday = (int) ((inputDate.getTime() - current.getTime()) / (24 * 60 * 60 * 1000));

                if (diffday > 5) {
                    if (diffday > 26) {
                        result = "nextmonth" + diffday;
                    }else{
                        result = "nextweek" + diffday;
                    }

                }else{
                    result = "tomorrow" + diffday;
                }

            } else if (compare > 0) { //과거
                diffday = (int) ((current.getTime() - inputDate.getTime()) / (24 * 60 * 60 * 1000));

                if (diffday > 5) {
                    if (diffday > 26) {
                        result = "lastmonth" + diffday;
                    }else{
                        result =  "lastweek" + diffday;
                    }

                }else{
                    result =  "yesterday" + diffday;
                }

            } else { //오늘
                result =  "today";
            }
        }catch (ParseException ignored){

        }
        return result;
    }

    public int[][] schedule(){

        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(strr);

        String[] teamName = {"T1", "GEN", "DRX", "DWG", "KT", "AF", "SP", "HLE", "DYN", "SB"};

        schedule = new int[10][jsonArray.size()]; //[row][column]

        for(int row = 0; row < 10; row++ ){
            for(int column = 0; column < jsonArray.size(); column++){
                JsonObject object = (JsonObject) jsonArray.get(column);
                schedule[row][column] = object.get(teamName[row]).getAsInt();

            }
        }

        return schedule;

    }

    public int[] date(){

        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(strr);

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject object = (JsonObject) jsonArray.get(i);
            date[i] = object.get("DATE").getAsInt();

        }

        return date;
    }

    public String gameDaySchedule(Date yourDate) {

        //date -> int 날짜 schedule -> 2차원 int 경기스케줄

        SimpleDateFormat input = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat day = new SimpleDateFormat("E");

        String inputdate = input.format(yourDate);  //yymmdd형태로 날짜 받음
        String inputday = day.format(yourDate); //E형태로 날짜 받음

        Calendar cal = Calendar.getInstance();
        cal.setTime(yourDate);

        Date calSevenday;

        String text;
        String dayofinput = "";

            calSevenday = cal.getTime();
            dayofinput = input.format(calSevenday);
            String year = dayofinput.substring(0, 4);
            String month = dayofinput.substring(4, 6);
            String date = dayofinput.substring(6, 8);
            text = "__"+year+"년"+month+"월"+date+"일 "+inputday+" 경기__  " + vs(dayofinput);

        return text;
    }

    public String gameWeekSchedule(String whatYouWant, Date yourDate) throws ParseException {

        //date -> int 날짜 schedule -> 2차원 int 경기스케줄
        String[] teamName = {"T1", "GEN", "DRX", "DWG", "KT", "AF", "SP", "HLE", "DYN", "SB"};
        String want = whatYouWant;

        String returnString = "";

        //주 입력 받았으면 해당 주 전부다 출력하기위해 해당 주 의 월요일 날짜를 도출
        SimpleDateFormat input = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat day = new SimpleDateFormat("E");

        String inputdate = input.format(yourDate);  //yymmdd형태로 날짜 받음
        String inputday = day.format(yourDate); //E형태로 날짜 받음

        Calendar cal = Calendar.getInstance();
        cal.setTime(yourDate);
        String re =  inputday + "오류";
        Date calcDate;

        switch (inputday){

            case "Mon" :
                calcDate = cal.getTime();
                re = input.format(calcDate);
                break;
            case "Tue" :
                cal.add(Calendar.DATE, -1);
                calcDate = cal.getTime();
                re = input.format(calcDate);
                break;
            case "Wen" :
                cal.add(Calendar.DATE, -2);
                calcDate = cal.getTime();
                re = input.format(calcDate);
                break;
            case "Thu" :
                cal.add(Calendar.DATE, -3);
                calcDate = cal.getTime();
                re = input.format(calcDate);
                break;
            case "Fri" :
                cal.add(Calendar.DATE, -4);
                calcDate = cal.getTime();
                re = input.format(calcDate);
                break;
            case "Sat" :
                cal.add(Calendar.DATE, -5);
                calcDate = cal.getTime();
                re = input.format(calcDate);
                break;
            case "Sun" :
                cal.add(Calendar.DATE, -6);
                calcDate = cal.getTime();
                re = input.format(calcDate);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + inputday);
        }
        //re -> 해당 주 월요일

        //스케줄 작성


        Date calSevenday;

        String[] text = new String[7];
        String[] week = {"월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"};
        String dayofinput = "";
        int k = 0;
        for(int i = 0; i<7; i++){
            cal.add(Calendar.DATE, k);
            calSevenday = cal.getTime();
            dayofinput = input.format(calSevenday);
            String year = dayofinput.substring(0, 4);
            String month = dayofinput.substring(4, 6);
            String date = dayofinput.substring(6, 8);
            text[i] = "__"+year+"년"+month+"월"+date+"일 "+week[i]+" 경기__  " + vs(dayofinput);
            if(k==0){
                k++;
            }
        }

        //1라운드 2라운드 경기 팀 임시저장



        String test = text[0]+"  \n"+ text[1]+"  \n"+ text[2]+"  \n" + text[3]+"  \n" + text[4]+"  \n" + text[5]+"  \n" + text[6];




        return test;
    }

    public String vs(String dayofinput){
        //date  -> 입력받은날짜
        String dateinputforschedule = dayofinput;

        String[] teamName = {"T1", "GEN", "DRX", "DWG", "KT", "AF", "SP", "HLE", "DYN", "SB"};

        int[] daySchedull = new int[teamName.length]; //하루 스케줄 임시저장
        for(int i = 0; i<date.length; i++){
            if(date[i]==Integer.parseInt(dateinputforschedule)){
                for(int j = 0; j<teamName.length; j++){
                    System.out.println(schedule[j][i]);
                    System.out.println();
                    daySchedull[j] = schedule[j][i];
                }
                break;
            }
        }

        String[] firstRound = {"NoGame", "NoGame"};
        String[] secondRound = {"NoGame", "NoGame"};

        int fi = 0;
        int si = 0;
        for (int k = 0 ; k<teamName.length; k++) {
            if (daySchedull[k] == 1) {
                firstRound[fi] = teamName[k];
                if(fi!=2){
                    fi++;
                }
            } else if (daySchedull[k] == 2) {
                secondRound[si] = teamName[k];
                if(si!=2){
                    si++;
                }
            }
        }

        return  "  \n[1 Round] "+ firstRound[0]+" vs "+firstRound[1]+"  \n[2 Round] "+secondRound[0]+" vs "+secondRound[1];
    }

    public int findSchedule(int input) {
        String[] teamName = {"T1", "GEN", "DRX", "DWG", "KT", "AF", "SP", "HLE", "DYN", "SB"};
        int replaydateInt = input;
        int count = 0;
        for (int i = 0; i < date.length; i++) { //날짜테이블

            if (date[i] == replaydateInt) {    //날짜찾음
                for (int k = 0; k < teamName.length; k++) {
                    if (schedule[k][i] != 0) {
                        count = schedule[k][i];
                        return count;
                    }
                }return count;
            }

        }return count;
    }
}
