package sample;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created with Intellij IDEA.
 * Project name: DateTimeCounter.
 * Date: 16.02.2017.
 * Time: 18:43.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class DateObject {
    private LocalDate endDate;
    private LocalDate startDate;
    private Integer stayPeriod;
    private ArrayList<LocalDate[]> enterExitDate = new ArrayList<>();


    public DateObject() {
    }

    public DateObject(LocalDate endDate, LocalDate startDate) {
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public DateObject(LocalDate endDate, LocalDate startDate, Integer stayPeriod) {
        this.endDate = endDate;
        this.startDate = startDate;
        this.stayPeriod = stayPeriod;
    }

    Integer getStayPeriod() {
        return stayPeriod;
    }

    void setStayPeriod(Integer stayPeriod) {
        this.stayPeriod = stayPeriod;
    }

    LocalDate getStartDate() {
        return startDate;
    }

    void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    LocalDate getEndDate() {
        return endDate;
    }

    void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    ArrayList<LocalDate[]> getEnterExitDate() {
        return enterExitDate;
    }

    void addEnterExitDate(LocalDate enterDate, LocalDate exitDate) {
        LocalDate[] dates = new LocalDate[2];

        boolean isExist = true;


        for (LocalDate[] localDates : getEnterExitDate()) {
            if (localDates[0].isEqual(enterDate)) {
                if (localDates[1].isEqual(exitDate)) {
                    isExist = false;
                }
            }
        }


        if (isExist) {
            dates[0] = enterDate;
            dates[1] = exitDate;


            getEnterExitDate().add(dates);
        }
    }


    public LocalDate getEnterDateByIndex(int index) {
        LocalDate result;

        result = getEnterExitDate().get(index)[0];

        return result;
    }

    public LocalDate getExitDateByIndex(int index) {
        LocalDate result;

        result = getEnterExitDate().get(index)[1];

        return result;
    }

    boolean isHaveNotNullFiled(DateObject dateObject) {
        boolean result = true;
        for (Field field : dateObject.getClass().getFields()) {

            field.setAccessible(true);
            try {
                if (field.get(dateObject) == null) {
                    result = false;
                    break;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
