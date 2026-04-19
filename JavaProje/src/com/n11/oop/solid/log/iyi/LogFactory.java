package com.n11.oop.solid.log.iyi;

public class LogFactory {
    @SuppressWarnings("deprecation")
    public ILog create(String className)
    {
        Class c;
        ILog iLogTipi=null;

        try
        {
            c  = Class.forName("com.n11.oop.solid.log.iyi."+className);
            iLogTipi = (ILog) c.newInstance();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return iLogTipi;

    }
}



