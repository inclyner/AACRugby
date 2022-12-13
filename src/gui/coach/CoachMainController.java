package gui.coach;
// not done not tested
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CoachMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCallUpPlayers;

    @FXML
    private Button btnInsertNotes;

    @FXML
    private Button btnPunishment;

    @FXML
    private Button btnReport;

    @FXML
    private Button btnSchedulePractice;

    @FXML
    private ImageView imageAccount;

    @FXML
    private Label lbHello;

    @FXML
    private CalendarView calendarView;

    @FXML
    void onClickAccountImage(MouseEvent event) {
        try {
            Main main = new Main();
            main.changeScene("UserPersonalDataView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickCallUpBtn(ActionEvent event) {
        try {
            Main main = new Main();
            main.changeScene("coach\\CallUpPlayersView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickInsertNotes(ActionEvent event) {
        try {
            Main main = new Main();
            main.changeScene("coach\\InsertPlayersNotesView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickPunishmentBtn(ActionEvent event) {
        try {
            Main main = new Main();
            main.changeScene("coach\\InsertExternalPunishmentView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickReportBtn(ActionEvent event) {
        try {
            Main main = new Main();
            main.changeScene("coach\\ReportNonAttendanceView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickScheduleBtn(ActionEvent event) {
        try {
            Main main = new Main();
            main.changeScene("coach\\SchedulePracticeView.fxml");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        try {
            Main main = new Main();
            lbHello.setText("Hello coach,\n" + main.getModelManager().getNameLogged() +"!");

            Stage stage = main.getStg();
            stage.setResizable(true);
            stage.setWidth(1100);
            stage.setHeight(800);

            Calendar calendar = new Calendar("AAC Season Calendar");
            calendar.setStyle(Calendar.Style.STYLE3);
            for(Entry<String> entry : main.getModelManager().getGamesForCalendar()){
                calendar.addEntry(entry);
            }


            Calendar calendarPractices = new Calendar("Team Practices");
            calendarPractices.setStyle(Calendar.Style.STYLE5);
            for(Entry<String> entry : main.getModelManager().getPracticesForCalendar(false)){
                calendarPractices.addEntry(entry);
            }


            calendar.readOnlyProperty().setValue(true);
            calendarPractices.readOnlyProperty().setValue(true);

            CalendarSource myCalendarSource = new CalendarSource("My Calendars");
            myCalendarSource.getCalendars().addAll(calendar);
            myCalendarSource.getCalendars().addAll(calendarPractices);

            calendarView.getCalendarSources().addAll(myCalendarSource);

            calendarView.setShowSearchField(false);
            calendarView.setShowAddCalendarButton(false);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

