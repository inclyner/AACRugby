package Users;

import java.sql.SQLException;

public class Manager extends CommonFeatures {

    public Manager(Long nCC, String name, String email, String sex, String birthDate, Long phoneNumber) throws SQLException {
        super(nCC, name, email, sex, birthDate, phoneNumber);
    }

    private void InsertUser(){}

    private void deleteUser(){}

    private void AproveChangeRequest(){}

}
