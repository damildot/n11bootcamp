package com.n11.oop.solid.log.iyi;

public class Logger {

    private ILog iLog;

    public Logger(ILog iLog)
    {
        this.iLog = iLog;
    }

    public String LogYaz(String mesaj)
    {
       return this.iLog.log(mesaj);
    }
}
