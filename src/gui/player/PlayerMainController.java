package gui.player;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class PlayerMainController {
    @FXML
    private Label lbHello;

    @FXML
    private CalendarView calendarView;

    @FXML
    private ImageView imgAccount;


    @FXML
    void onClickAccountProfile(MouseEvent event) {
        try {
            Main main = new Main();
            main.changeScene("player\\PlayerPersonalDataView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        try {
            Main main = new Main();
            lbHello.setText("Hello player,\n" + main.getModelManager().getNameLogged() + "!");

            Stage stage = main.getStg();
            stage.setResizable(true);
            stage.setWidth(1100);
            stage.setHeight(800);

            Calendar holidays = new Calendar("AAC Rugby Team Calendar");
            holidays.setStyle(Calendar.Style.STYLE3);

            Entry<String> entry = new Entry<>("Appointment");
            entry.setInterval(ZonedDateTime.of(2022,12,12,15,0,0,0, ZoneId.systemDefault()),ZonedDateTime.of(2022,12,12,17,0,0,0, ZoneId.systemDefault()));

            holidays.addEntry(entry);

            CalendarSource myCalendarSource = new CalendarSource("My Calendars");
            myCalendarSource.getCalendars().addAll(holidays);

            calendarView.getCalendarSources().addAll(myCalendarSource);

            calendarView.setShowSearchField(false);
            calendarView.setShowAddCalendarButton(false);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
