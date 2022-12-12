package gui.manager;
// not done not tested
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.calendarfx.view.DateControl;
import gui.Main;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ManagerMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAproveRequests;

    @FXML
    private Button btnDeleteUser;

    @FXML
    private Button btnInsertUser;

    @FXML
    private Label lbHello;

    @FXML
    private CalendarView calendarView;

    @FXML
    void onClickInsertUser(MouseEvent event) {
        try {
            Main main = new Main();
            main.changeScene("manager\\InsertUserView.fxml");

        } catch (SQLException e){
            System.err.println(e);
        }
    }

    @FXML
    void onClickDeleteUser(MouseEvent event) {
        try {
            Main main = new Main();
            main.changeScene("manager\\DeleteUserView.fxml");
            //main.getModelManager().getAllPlayer();
        } catch (SQLException e){
            System.err.println(e);
        }
    }

    @FXML
    void onClickApprovalRequests(MouseEvent event) {
        try {
            Main main = new Main();
            main.changeScene("manager\\ApproveRequestsView.fxml");

        } catch (SQLException e){
            System.err.println(e);
        }
    }

    @FXML
    void initialize() {
       try {
            //wait(2000);
            Main main = new Main();
            lbHello.setText("Hello manager,\n" + main.getModelManager().getNameLogged() +"!");

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

            /*
            Calendar holidays = new Calendar("AAC Rugby Team Calendar");
            holidays.setStyle(Calendar.Style.STYLE7);

            Entry<String> entry = new Entry<>("Appointment");
            entry.setInterval(ZonedDateTime.now(),ZonedDateTime.of(2022,12,12,17,0,0,0, ZoneId.systemDefault()));

            holidays.addEntry(entry);

            CalendarSource myCalendarSource = new CalendarSource("My Calendars");
            myCalendarSource.getCalendars().addAll(holidays);

           //entry.setCalendar(calendarView.getAvailabilityCalendar());
           calendarView.getCalendarSources().addAll(myCalendarSource);
           calendarView.setShowSearchField(false);
           calendarView.setShowAddCalendarButton(false);*/
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
