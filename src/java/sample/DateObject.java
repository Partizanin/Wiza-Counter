package sample;

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

    public Integer getStayPeriod() {
        return stayPeriod;
    }

    public void setStayPeriod(Integer stayPeriod) {
        this.stayPeriod = stayPeriod;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ArrayList<LocalDate[]> getEnterExitDate() {
        return enterExitDate;
    }

    public void addEnterExitDate(LocalDate enterDate, LocalDate exitDate) {
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
}
