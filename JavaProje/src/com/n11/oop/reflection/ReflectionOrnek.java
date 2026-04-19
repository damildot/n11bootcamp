package com.n11.oop.reflection;

import java.lang.reflect.Method;

public class ReflectionOrnek {
    public static void main(String[] args) {
        try
        {
            Class cls = Class.forName("com.n11.oop.reflection.Deneme");
            // Deneme deneme = new Deneme();
            for(Method m: cls.getDeclaredMethods())
            {
                System.out.println("Metod ismi : "+m.getName() + "\n"
                        +" dönüş tipi : " +m.getReturnType()+ "\n"
                        +" Parametre sayısı : " +m.getParameterCount());
            }

            Object obj = cls.newInstance();
            // Class tipinde bir dizi oluşturuyoruz
            // Çünkü metodumuzu çağırdığımızda bizden argument istiyor ise
            // O tipte diziye parametre nesnesi atıyoruz
            Class[] paramStringa = new Class[1];
            paramStringa[0] = String.class;

            // Metodumuzu aşşağıdaki şekilde parametre dizisini göndererek çağırıyoruz
            Method m = cls.getMethod("gosterString", paramStringa);
            // Reflection kütüphanesinde metodları çektiğimizde invoke
            // metdoduyla parametreleri yollarız .
            // dikkat ettiyseniz 'obj' nesnesini sadece instance ettik runtime da
            // metodu çağırdık 'cls' referansıyla parametre yolladık
            Object o = m.invoke(obj, new String("www.opendart.com"));
            // ve ilk gitti metodu çalıştırdı ve
            // metodun içindekileri yazdı sonrasında
            // metodun dönüş değerini alıp yazdıralım
            String cikti = o.toString();
            System.out.println("En son method'un dönüş değeri  : " + cikti);
        }
        catch(Exception ex)
        {

        }
    }
}
