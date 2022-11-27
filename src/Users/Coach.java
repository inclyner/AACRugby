package Users;

import java.sql.SQLException;

public class Coach extends CommonFeatures{

    public Coach(Long nCC, String name, String email, String sex, String birthDate, Long phoneNumber) throws SQLException {
        super(nCC, name, email, sex, birthDate, phoneNumber);
    }

}
