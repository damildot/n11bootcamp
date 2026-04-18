package com.n11.oop.solid.log.kotu;

public class Test {

    public static void main(String[] args)
    {
        XmlLog xmlLog = new XmlLog();
        DbLog dbLog = new DbLog();
        TextLog text= new TextLog();
        Logger logger = new Logger(xmlLog,dbLog,text);
        logger.LogKayit(LogType.Text,"303 nolu hata kodu oluştu");

    }
}
