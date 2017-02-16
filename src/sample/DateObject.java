package sample;

import javafx.beans.property.SimpleStringProperty;

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
    private SimpleStringProperty endDate;
    private SimpleStringProperty startDate;
    private SimpleStringProperty stayPeriod;
    private ArrayList<LocalDate> enterDate;
    private ArrayList<LocalDate> exitDate;


    public DateObject() {
    }

    public DateObject(SimpleStringProperty endDate, SimpleStringProperty startDate) {
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public DateObject(SimpleStringProperty endDate, SimpleStringProperty startDate, SimpleStringProperty stayPeriod, ArrayList<LocalDate> enterDate,
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

    public SimpleStringProperty getStayPeriod() {
        return stayPeriod;
    }

    public void setStayPeriod(SimpleStringProperty stayPeriod) {
        this.stayPeriod = stayPeriod;
    }

    public SimpleStringProperty getStartDate() {
        return startDate;
    }

    public void setStartDate(SimpleStringProperty startDate) {
        this.startDate = startDate;
    }

    public SimpleStringProperty getEndDate() {
        return endDate;
    }

    public void setEndDate(SimpleStringProperty endDate) {
        this.endDate = endDate;
    }

}
