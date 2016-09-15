package core.samples.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatExample {
    public static void main(String[] args) throws ParseException {
        exampleOfDataParseOne();
        exampleOfDataParseTwo();
        exampleOfDataParseThree();
    }

    static void exampleOfDataParseOne() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String dateInString = "15-Jun-2016";

        Date date = sdf.parse(dateInString);
        System.out.println(date);
    }

    static void exampleOfDataParseTwo() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = "15/09/2016";

        Date date = sdf.parse(dateInString);
        System.out.println(date);
    }

    static void exampleOfDataParseThree() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("E, MMM dd yyyy");
        String dateInString = "Fri, June 21 2016";

        Date date = sdf.parse(dateInString);
        System.out.println(date);
    }
}
