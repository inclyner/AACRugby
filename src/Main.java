import Users.Coach;
import Users.CommonFeatures;
import Users.Player;
import logic.Game;
import logic.Practise;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static Users.CommonFeatures.getDbConnection;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {
    public static void main(String[] args) {
        /*ArrayList <Long> cc = new ArrayList<Long>();
        try {
            Statement statement = getDbConnection().createStatement();
            String query = "DELETE FROM externalPunishments";
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Coach coach = null;
        try {
            coach = new Coach("fvgrgfg");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        /*try {
            coach.InsertUsersFromTxt();
        } catch (SQLException e){
            throw new RuntimeException();
        }*/

        Coach c= null;
        try {
            c=new Coach("asd");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ArrayList<Practise> g = c.getPractise();
        System.out.println(g);
        for (Practise p: g) {
            System.out.println(p.getId());
            System.out.println(c.getNamePractise(p));
        }
        System.out.println("w");
        //c.repportNonAttendance(585005353L);
        c.insertNotesAboutPlayer(585005355L, 1, "asdfasfa", true);

    }
}
