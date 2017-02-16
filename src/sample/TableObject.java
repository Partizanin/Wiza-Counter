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
    private SimpleStringProperty id = new SimpleStringProperty();
    private SimpleStringProperty enterDate = new SimpleStringProperty();
    private SimpleStringProperty exitDate = new SimpleStringProperty();
    private SimpleStringProperty stayPeriod = new SimpleStringProperty();

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

    public SimpleStringProperty stayPeriodProperty() {
        return stayPeriod;
    }

    public void setStayPeriod(String stayPeriod) {
        this.stayPeriod.set(stayPeriod);
    }

    public String getExitDate() {
        return exitDate.get();
    }

    public SimpleStringProperty exitDateProperty() {
        return exitDate;
    }

    public void setExitDate(String exitDate) {
        this.exitDate.set(exitDate);
    }

    public String getEnterDate() {
        return enterDate.get();
    }

    public SimpleStringProperty enterDateProperty() {
        return enterDate;
    }

    public void setEnterDate(String enterDate) {
        this.enterDate.set(enterDate);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }
}
