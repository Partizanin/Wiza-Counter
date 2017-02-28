package sample;

import java.time.Duration;
import java.time.LocalDate;

/**
 * Created with Intellij IDEA.
 * Project name: DateTimeCounter.
 * Date: 28.02.2017.
 * Time: 18:25.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class Utils {
    public String daysBetweenDate(LocalDate startDate, LocalDate endDate) {
//        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
//        return String.valueOf(daysBetween);
        long days = Duration.between(startDate.atTime(0, 0), endDate.atTime(0, 0)).toDays();
        return String.valueOf(days);
    }

}
