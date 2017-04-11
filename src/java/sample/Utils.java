package sample;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * Created with Intellij IDEA.
 * Project name: DateTimeCounter.
 * Date: 28.02.2017.
 * Time: 18:25.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class Utils {
    public String daysBetweenDate(LocalDate startDate, LocalDate endDate) {
        long days = Duration.between(startDate.atTime(0, 0), endDate.atTime(0, 0)).toDays();
        return String.valueOf(days);
    }

    public String dateValidation(String date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                .toFormatter();

        LocalDate resultDate = null;

        String result = "не правильний формат дати.Введіть дату в вигляді 13.12.2012";
        boolean isParsed = false;
        try {
            resultDate = LocalDate.parse(date, formatter);
            isParsed = true;
        } catch (Exception ignored) {

        }


        if (!isParsed) {
            result = "x";
        } else {
            result = resultDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        return result;
    }

    String numberValidation(String value) {
        String result = "x";
        boolean isParsed = true;

        if (value.length() > 0) {
            try {
                Integer.parseInt(value);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                isParsed = false;
            }
        } else {
            isParsed = false;
        }


        if (isParsed) {
            result = value;
        }
        return result;
    }

}
