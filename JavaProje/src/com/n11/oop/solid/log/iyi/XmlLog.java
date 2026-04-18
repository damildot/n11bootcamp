package com.n11.oop.solid.log.iyi;

public class XmlLog implements  ILog{
    @Override
    public boolean log(String value) {
        System.out.println(value + " xml  ee kaydedildi");
        return true;
    }
}
