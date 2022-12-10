package Utils;

import java.time.LocalDate;
import java.time.Period;
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
}
