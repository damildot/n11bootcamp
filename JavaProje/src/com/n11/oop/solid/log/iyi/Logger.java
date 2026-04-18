package com.n11.oop.solid.log.iyi;

public class Logger {

    private ILog iLog;

    public Logger(ILog iLog)
    {
        this.iLog = iLog;
    }

    public void LogYaz(String mesaj)
    {
        this.iLog.log(mesaj);
    }
}
