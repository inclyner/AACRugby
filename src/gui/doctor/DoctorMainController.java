package gui.doctor;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DoctorMainController {

    @FXML
    private Label lbHello;
    @FXML
    private ImageView accountImage;

    @FXML
    private Button btnAppointments;

    @FXML
    private Button btnInnsertNotes;

    @FXML
    private Button btnInsertDiet;

    @FXML
    private CalendarView calendarView;

    @FXML
    void onClickAccountIcon(MouseEvent event) throws SQLException {

            Main main = new Main();
            main.changeScene("UserPersonalDataView.fxml");
    }

    @FXML
    void onClickAppointmentsBtn(ActionEvent event) throws SQLException {

            Main main = new Main();
            main.changeScene("doctor\\ScheduleAppointmentsView.fxml");
    }

    @FXML
    void onClickInsertDietBtn(ActionEvent event) throws SQLException {

            Main main = new Main();
            main.changeScene("doctor\\InsertDietView.fxml");

    }

    @FXML
    void onClickInsertNotesBtn(ActionEvent event) throws SQLException {

            Main main = new Main();
            main.changeScene("doctor\\InsertNotesView.fxml");

    }

    @FXML
    void initialize() throws SQLException {
        assert btnAppointments != null : "fx:id=\"btnAppointments\" was not injected: check your FXML file 'DoctorMainView.fxml'.";
        assert btnInnsertNotes != null : "fx:id=\"btnInnsertNotes\" was not injected: check your FXML file 'DoctorMainView.fxml'.";
        assert btnInsertDiet != null : "fx:id=\"btnInsertDiet\" was not injected: check your FXML file 'DoctorMainView.fxml'.";

        Main main = new Main();
        lbHello.setText("Hello doctor,\n " + main.getModelManager().getNameLogged() +"!");

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
    }

}
