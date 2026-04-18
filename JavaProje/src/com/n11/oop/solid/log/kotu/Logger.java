package com.n11.oop.solid.log.kotu;

public class Logger {

    private DbLog dbLog;
    private XmlLog xmlLog;
    private TextLog textLog;


    public Logger(XmlLog xmlLog, DbLog dbLog,TextLog textLog)
    {
        this.xmlLog = xmlLog;
        this.dbLog = dbLog;
        this.textLog = textLog;

    }

    public void LogKayit(LogType type, String value)
    {
        switch(type)
        {
            case Xml:
                xmlLog.xmlKayit(value);
                break;
            case Db:
                dbLog.dbKayit(value);
                break;
            case Text:
                textLog.textKayit(value);
                break;
        }
    }

}

