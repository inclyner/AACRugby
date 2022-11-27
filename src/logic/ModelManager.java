package logic;

import Users.CommonFeatures;

import java.sql.SQLException;

public class ModelManager extends CommonFeatures {
    CommonFeatures cF;

    public ModelManager() throws SQLException {
        //this.cF = new CommonFeatures();
    }

    public boolean login(String email, String password) throws SQLException {
        return cF.login(email, password);
    }
}
