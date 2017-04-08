package sample;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created with Intellij IDEA.
 * Project name: DateTimeCounter.
 * Date: 28.02.2017.
 * Time: 18:25.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class Utils {

    public static void main(String[] args) {
        new Utils().daysBetweenDate(LocalDate.parse(""), LocalDate.parse(""));
    }
    public String daysBetweenDate(LocalDate startDate, LocalDate endDate) {
        long days = Duration.between(startDate.atTime(0, 0), endDate.atTime(0, 0)).toDays();
        return String.valueOf(days);
    }

    String dateValidation(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM.dd.yyyy");

        LocalDate resultDate = null;

        String result = "не правильний формат дати.Введіть дату в вигляді 12.12.2012";
        boolean isParsed = false;
        try {
            resultDate = LocalDate.parse(date,formatter);
            isParsed = true;
        } catch (Exception ignored) {

        }

        if (!isParsed) {
            try {
                resultDate =  LocalDate.parse(date,formatter2);
                isParsed = true;
            } catch (Exception ignored) {

            }
        }


        if (!isParsed) {
            result = "x";
        }else {
            result = formatter.format(resultDate);
        }

        return result;
    }

    String numberValidation(String value) {
        String result = "x";
        boolean isParsed = true;
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            isParsed = false;
        }
        if (isParsed) {
            result = value;
        }
        return result;
    }

}
