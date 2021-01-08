package com.tencent.oa.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {
        //实现Excel写操作
        //设置写入文件夹地址和Excel文件名称
        String fileName = "E:\\write.xlsx";

        //EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(getData());
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    //创建方法返回List集合
    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("Lucy"+i);
            list.add(data);
        }
        return list;
    }
}
