package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created with Intellij IDEA.
 * Project name: DateTimeCounter.
 * Date: 16.02.2017.
 * Time: 19:00.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class TableObject {
    private SimpleStringProperty id;
    private SimpleStringProperty enterDate;
    private SimpleStringProperty exitDate;
    private SimpleStringProperty stayPeriod;

    public TableObject() {
    }

    public TableObject(String id, String enterDate, String exitDate, String stayPeriod) {
        this.id = new SimpleStringProperty(id);
        this.enterDate = new SimpleStringProperty(enterDate);
        this.exitDate = new SimpleStringProperty(exitDate);
        this.stayPeriod = new SimpleStringProperty(stayPeriod);
    }

    public String getStayPeriod() {
        return stayPeriod.get();
    }

    public String stayPeriodProperty() {
        return stayPeriod.get();
    }

    public void setStayPeriod(String stayPeriod) {
        this.stayPeriod.set(stayPeriod);
    }

    public String getExitDate() {
        return exitDate.get();
    }

    public String exitDateProperty() {
        return exitDate.get();
    }

    public void setExitDate(String exitDate) {
        this.exitDate.set(exitDate);
    }

    public String getEnterDate() {
        return enterDate.get();
    }

    public String enterDateProperty() {
        return enterDate.get();
    }

    public void setEnterDate(String enterDate) {
        this.enterDate.set(enterDate);
    }

    public String getId() {
        return id.get();
    }

    public String idProperty() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }
}
