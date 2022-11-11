package com.hope.ds.work;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LogAnalysis {


    static int count;


    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 25; i++) {
            String fileName = "/Users/dongchao/dc/desk/" + i;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            while(reader.ready()) {
                String line = reader.readLine();
                resolve(line);
            }
        }

        PriorityQueue<Item> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            priorityQueue.add(new Item(entry.getKey(), entry.getValue()));
        }

        File resultFile = new File("/Users/dongchao/dc/desk/result.text");
        resultFile.delete();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultFile)));

        Item item = null;
        while ((item = priorityQueue.poll()) != null) {
            writer.write(item.getCount() + "||" + item.getUrl());
            writer.newLine();
            writer.flush();
        }

        System.out.printf("");

    }
    static Map<String, Integer> map = new HashMap<>(50000);
    static int mcount = 0;
    static int dcount = 0;
    static int ccount = 0;
    static int scount = 0;
    static int fcount = 0;
    static int officialcount = 0;
    static int giftcount = 0;
    static int imagecounrt = 0;
    static int headcounrt = 0;

    public static void resolve(String value) {
        String[] strArray = value.split(" ");
        String urlStr = strArray[6];

        if(urlStr.contains("headers/m") || urlStr.contains("headers/f")) {
            mcount++;
        }
        else if (urlStr.contains("/gameres/bubble")) {
            dcount ++;
        } else if (urlStr.contains("community/image/")) {
            ccount++;
        } else if (urlStr.contains("/snapshot")) {
            scount ++;
        } else if (urlStr.contains("gameres/frame")) {
            fcount++;
        } else if (urlStr.contains("emoji/official")) {
            officialcount ++;
        } else if (urlStr.contains("upload/gift/")) {
            giftcount++;
        } else if (urlStr.contains("/image/")) {
            imagecounrt ++;
        } else if (urlStr.contains("/head/")) {
            headcounrt ++;
        }


        else {
            count++;
        }

        Integer v = map.get(urlStr);
        if (v == null) {
            map.put(urlStr, 1);
        } else {
            v = v + 1;
            map.put(urlStr, v);
        }

    }

    static class Item implements Comparable<Item>{
        public Item(String url, Integer count) {
            this.url = url;
            this.count = count;
        }

        private String url;
        private int count;

        public String getUrl() {
            return url;
        }

        public Item setUrl(String url) {
            this.url = url;
            return this;
        }

        public int getCount() {
            return count;
        }

        public Item setCount(int count) {
            this.count = count;
            return this;
        }

        @Override
        public int compareTo(Item o) {
            return this.getCount() > o.getCount()? 1:0;
        }
    }

}
