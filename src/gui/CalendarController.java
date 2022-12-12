package gui;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import com.calendarfx.util.CalendarFX;
import com.calendarfx.view.CalendarView;
import com.calendarfx.view.page.WeekPage;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import logic.Appointment;

import java.time.Clock;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.SignStyle;

public class CalendarController {

    @FXML
    private CalendarView calendarView;

    @FXML
    void initialize() {
        assert calendarView != null : "fx:id=\"calendarView\" was not injected: check your FXML file 'calendario.fxml'.";

        calendarView.createEntryAt(ZonedDateTime.now());
    }
}
