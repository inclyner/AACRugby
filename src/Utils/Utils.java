package Utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public static LocalDate getCurrentDate(){
        return LocalDate.now();
    }
    public static LocalDate getDateAsLocalDate(String date){
        return LocalDate.parse(date.formatted(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

    }

    public static ZonedDateTime convertDateTimeToEntryCalendar(String date, String time){
        String[] dateParts = date.split("-");

        String day = dateParts[0];
        String month = dateParts[1];
        String year = dateParts[2];

        int yearInt = Integer.parseInt(year);
        int monthInt = Integer.parseInt(month);
        int dayInt = Integer.parseInt(day);

        String[] beginTimeParts = time.split(":");

        String hourBegin = beginTimeParts[0];
        String minutesBegin = beginTimeParts[1];
        int hourBeginInt = Integer.parseInt(hourBegin);
        int minutesBeginInt = Integer.parseInt(minutesBegin);

        return ZonedDateTime.of(yearInt,monthInt,dayInt,hourBeginInt,minutesBeginInt,0,0, ZoneId.systemDefault());
    }
}
