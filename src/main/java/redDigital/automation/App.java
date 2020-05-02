package redDigital.automation;//package redDigital.automation;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
///**
// * Hello world!
// *
// */
//public class App
//{
//    public static void main( String[] args )
//    {
//
//        String cadena="alexander 1234";
//        System.out.println("cadena :" + cadena.substring(9));
//        Date fecha = new Date(Calendar.getInstance().getTimeInMillis());
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-yy");
//        Date date = new Date();
//        System.out.println(formatter.format(date));
//        Date date33=formatter.format(date);
//        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
//        Date date2 = new Date();
//        System.out.println(formatter1.format(date2));
//        System.out.println("buena fecha"+date +"  " +date2);
//
//        if (date2.after(date)) {
////            System.out.println("buena fecha"+);
//        }
////        System.out.println(java.time.LocalDate.now());
//    }
//
//
//
//
//
//}






//package com.pe.inretailpharma.digital.migration;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

public class App {
//    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static void main(String[] args) {
        Date currentDate = new Date();
        DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String str_date_1 = "22/04/2020";
        LocalDate local_date_1 = LocalDate.parse(str_date_1, formatter_1);
        System.out.println("la fecha a ingresar es ff: "+ local_date_1);
        Date dateFromDB = convertToDateViaInstant(local_date_1);
        ZonedDateTime zdtCurrentDate = ZonedDateTime.ofInstant(currentDate.toInstant(),ZoneId.systemDefault());
        ZonedDateTime zdtDateFromDB =  ZonedDateTime.ofInstant(dateFromDB.toInstant(),ZoneId.systemDefault());

        if (zdtDateFromDB.isBefore(zdtCurrentDate)) {
            Period period = Period.between(zdtDateFromDB.toLocalDate() , zdtCurrentDate.toLocalDate());
            int days = period.getDays();
            Date dateFromDBToAddUp = dateFromDB;

            String fechaIn = dateFormat.format(dateFromDBToAddUp);
            System.out.println("la fecha a ingresar es ff: "+ fechaIn);

            for(int i=0; i<days; i++) {
                Calendar c = Calendar.getInstance();
                c.setTime(dateFromDBToAddUp);
                c.add(Calendar.DATE, 1);
                dateFromDBToAddUp = c.getTime();
                String fechaIn2 = dateFormat.format(dateFromDBToAddUp);
                System.out.println("la fecha a ingresar es: "+ fechaIn2);
            }
        }






    }
}



