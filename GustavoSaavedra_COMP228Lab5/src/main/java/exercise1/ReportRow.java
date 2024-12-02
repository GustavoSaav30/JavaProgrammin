package exercise1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ReportRow {
    private final StringProperty gameTitle;
    private final StringProperty date;
    private final StringProperty score;

    public ReportRow(String gameTitle, String date, String score) {
        this.gameTitle = new SimpleStringProperty(gameTitle);
        this.date = new SimpleStringProperty(date);
        this.score = new SimpleStringProperty(score);
    }

    public StringProperty gameTitleProperty() {
        return gameTitle;
    }

    public StringProperty dateProperty() {
        return date;
    }

    public StringProperty scoreProperty() {
        return score;
    }
}
