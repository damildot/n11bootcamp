package com.n11.oop.solid.log.iyi;

public class DbLog implements  ILog{
    @Override
    public String log(String value) {

        return value + " db ye kaydedildi";

    }
}
