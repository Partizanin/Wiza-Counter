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
    private ArrayList<LocalDate> enterDate = new ArrayList<>();
    private ArrayList<LocalDate> exitDate = new ArrayList<>();



    public DateObject() {
    }

    public DateObject(LocalDate endDate, LocalDate startDate) {
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public DateObject(LocalDate endDate, LocalDate startDate, Integer stayPeriod, ArrayList<LocalDate> enterDate,
                      ArrayList<LocalDate> exitDate) {
        this.endDate = endDate;
        this.startDate = startDate;
        this.stayPeriod = stayPeriod;
        this.enterDate = enterDate;
        this.exitDate = exitDate;
    }


    public ArrayList<LocalDate> getExitDate() {
        return exitDate;
    }

    public void setExitDate(ArrayList<LocalDate> exitDate) {
        this.exitDate = exitDate;
    }

    public ArrayList<LocalDate> getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(ArrayList<LocalDate> enterDate) {
        this.enterDate = enterDate;
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

}
