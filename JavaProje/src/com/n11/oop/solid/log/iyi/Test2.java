package com.n11.oop.solid.log.iyi;

import java.util.Scanner;

public class Test2 {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int secim = 0;

        while (secim != 1) {

            System.out.print("Log Tipi Giriniz : ");
            // String okuma
            String logTipi = scanner.nextLine();

            System.out.print("Mesajınızı yazınız : ");
            // String okuma
            String mesaj = scanner.nextLine();
            ILog iLogTipi = null;
            Logger logger = null;

            try {
                LogFactory factory = new LogFactory();
                iLogTipi = factory.create(logTipi);
                logger = new Logger(iLogTipi);
                String gelenmesaj = logger.LogYaz(mesaj);
                System.out.println(gelenmesaj);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }
}
