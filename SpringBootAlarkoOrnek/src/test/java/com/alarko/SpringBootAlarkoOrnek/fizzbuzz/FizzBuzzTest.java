package com.alarko.SpringBootAlarkoOrnek.fizzbuzz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class FizzBuzzTest {

    FizzBuzz fb ;

    @BeforeEach
    public void setUp()
    {
        fb = new FizzBuzz();
    }

    @Test
    public void GonderilenSayiUceBolunuyorsa()
    {
        assertEquals("Fizz",fb.stringFor(3));
    }

    @Test
    public void GonderilenSayiBeseBolunuyorsa()
    {
        assertEquals("Buzz",fb.stringFor(5));
    }

    @Test
    public void HemUceHemdeBeseBolunuyorsa()
    {
        assertEquals("FizzBuzz",fb.stringFor(15));
    }

    @Test
    public void HemUceHemdeBeseBolunmuyorsa()
    {
        assertEquals("7",fb.stringFor(7));
    }

    @Test
    public void SifirdanKucukseYuzdenBuyukse()
    {
        assertThrows(IllegalArgumentException.class, ()-> fb.stringFor(-1));
        assertThrows(IllegalArgumentException.class, ()-> fb.stringFor(101));
    }


}