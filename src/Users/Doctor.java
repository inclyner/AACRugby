package Users;

import java.sql.SQLException;

public class Doctor extends CommonFeatures{
    public Doctor(Long nCC, String name, String email, String sex, String birthDate, Long phoneNumber) throws SQLException {
        super(nCC, name, email, sex, birthDate, phoneNumber);
    }
}
