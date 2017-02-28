package sample;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created with Intellij IDEA.
 * Project name: DateTimeCounter.
 * Date: 28.02.2017.
 * Time: 18:25.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
class Utils {

    String daysBetweenDate(LocalDate startDate, LocalDate endDate) {

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return String.valueOf(daysBetween);
    }

}
