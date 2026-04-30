package com.alarko.SpringBootAlarkoOrnek.fizzbuzz;

public class FizzBuzz {

    public String stringFor(int i)
    {
        // 0-100 aralığı dışındaki değerler için hata fırlatır
        if(i < 0 || i > 100 ) {
            throw new IllegalArgumentException();
        }

        // 15'e bölünebiliyorsa hem 3'e hem 5'e bölünür (Fizz + Buzz)
        if(i % 15 == 0 )
        {
            return "FizzBuzz";
        }
        // 3'e bölünebiliyorsa sadece Fizz döner
        else if(i % 3 == 0)
        {
            return "Fizz";
        }
        // 5'e bölünebiliyorsa sadece Buzz döner
        else if(i % 5 == 0)
        {
            return "Buzz";
        }

        // Hiçbirine bölünmüyorsa sayının kendisini string olarak döner
        return String.valueOf(i);
    }

}