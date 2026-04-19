package com.n11.oop.solid.log.iyi;

public class TextLog implements  ILog{
    @Override
    public String log(String value) {
        return value + " text e kaydedil";

    }
}
