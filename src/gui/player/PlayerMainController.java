package gui.player;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import gui.Main;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.LoadException;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Timer;
import java.util.TimerTask;

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

    private void initializePlayerCalendarView() {
        try {
            Main main = new Main();

            // Calendario atualiza 5 em 5 segundos
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), ev -> {
                try {
                    calendarView.getCalendarSources().removeAll(calendarView.getCalendarSources());

                    Calendar calendar = new Calendar("AAC Season Calendar");
                    calendar.setStyle(Calendar.Style.STYLE3);
                    for(Entry<String> entry : main.getModelManager().getGamesForCalendar()){
                        calendar.addEntry(entry);
                    }

                    Calendar calendarMedic = new Calendar("Medical Appointments");
                    calendarMedic.setStyle(Calendar.Style.STYLE1);
                    for(Entry<String> entry : main.getModelManager().getMedicalAppointmentsForCalendar(main.getModelManager().getEmailLogged(),true)){
                        calendarMedic.addEntry(entry);
                    }

                    Calendar calendarPractices = new Calendar("Team Practices");
                    calendarPractices.setStyle(Calendar.Style.STYLE5);
                    for(Entry<String> entry : main.getModelManager().getPracticesForCalendar(true)){
                        calendarPractices.addEntry(entry);
                    }

                    calendar.readOnlyProperty().setValue(true);
                    calendarMedic.readOnlyProperty().setValue(true);
                    calendarPractices.readOnlyProperty().setValue(true);

                    CalendarSource myCalendarSource = new CalendarSource("My Calendars");
                    myCalendarSource.getCalendars().addAll(calendar);
                    myCalendarSource.getCalendars().addAll(calendarMedic);
                    myCalendarSource.getCalendars().addAll(calendarPractices);

                    calendarView.getCalendarSources().addAll(myCalendarSource);

                    calendarView.setShowSearchField(false);
                    calendarView.setShowAddCalendarButton(false);
                } catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
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
            stage.setMinWidth(1100);
            stage.setMinHeight(600);
            stage.setWidth(1200);
            stage.setHeight(800);

            initializePlayerCalendarView();


            /*
            Calendar calendar = new Calendar("AAC Season Calendar");
            calendar.setStyle(Calendar.Style.STYLE3);
            for(Entry<String> entry : main.getModelManager().getGamesForCalendar()){
                calendar.addEntry(entry);
            }

            Calendar calendarMedic = new Calendar("Medical Appointments");
            calendarMedic.setStyle(Calendar.Style.STYLE1);
            for(Entry<String> entry : main.getModelManager().getMedicalAppointmentsForCalendar(main.getModelManager().getEmailLogged(),true)){
                calendarMedic.addEntry(entry);
            }

            Calendar calendarPractices = new Calendar("Team Practices");
            calendarPractices.setStyle(Calendar.Style.STYLE5);
            for(Entry<String> entry : main.getModelManager().getPracticesForCalendar(true)){
                calendarPractices.addEntry(entry);
            }

            calendar.readOnlyProperty().setValue(true);
            calendarMedic.readOnlyProperty().setValue(true);
            calendarPractices.readOnlyProperty().setValue(true);

            CalendarSource myCalendarSource = new CalendarSource("My Calendars");
            myCalendarSource.getCalendars().addAll(calendar);
            myCalendarSource.getCalendars().addAll(calendarMedic);
            myCalendarSource.getCalendars().addAll(calendarPractices);

            calendarView.getCalendarSources().addAll(myCalendarSource);

            calendarView.setShowSearchField(false);
            calendarView.setShowAddCalendarButton(false);
*/

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
