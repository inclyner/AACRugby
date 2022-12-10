package Users.tests;

import Users.Coach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static Users.CommonFeatures.getDbConnection;
import static org.junit.jupiter.api.Assertions.*;

class CoachTest {

    @Test
    public void InsertPunishment(){
        Coach coach = null;
        try {
            coach = new Coach("fvgrgfg");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertEquals("Insert notes",coach.InsertPlayersPunishement("Camilo Tavares", "ola as d", 2));
    }

    @Test
    public void UpdatePunishment(){
        Coach coach = null;
        try {
            coach = new Coach("fvgrgfg");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertEquals("Update notes",coach.InsertPlayersPunishement("Camilo Tavares", "ola as d", 2));
    }

    @Test
    public void DoCallUpInsertExtraPlayer(){
        Coach coach = null;
        try {
            coach = new Coach("fvgrgfg");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*try {
            coach.InsertUsersFromTxt();
        } catch (SQLException e){
            throw new RuntimeException();
        }*/
        ArrayList<Long> cc = coach.getCc();
        assertEquals("Extra players",coach.callUpPlayers(cc, 12));
    }

    @Test
    public void DoCallUpInsertLessPlayer(){
        Coach coach = null;
        try {
            coach = new Coach("fvgrgfg");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*try {
            coach.InsertUsersFromTxt();
        } catch (SQLException e){
            throw new RuntimeException();
        }*/
        ArrayList<Long> cc = coach.getCc();
        int i=0;
        while(i<10) {
            cc.remove(i);
            i++;
        }
        assertEquals("Not enough players",coach.callUpPlayers(cc, 12));
    }

    @Test
    public void DoCallUpInsertEnoughPlayer(){
        Coach coach = null;
        try {
            coach = new Coach("fvgrgfg");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*try {
            coach.InsertUsersFromTxt();
        } catch (SQLException e){
            throw new RuntimeException();
        }*/
        ArrayList<Long> cc = coach.getCc();
        int i=0;
        while(i<3) {
            cc.remove(i);
            i++;
        }
        assertEquals("Insert date about call up in the database",coach.callUpPlayers(cc, 12));
    }

    @Test
    public void DoCallUpInsertPlayerWhenCallUpAlreadyExist(){
        Coach coach = null;
        try {
            coach = new Coach("fvgrgfg");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*try {
            coach.InsertUsersFromTxt();
        } catch (SQLException e){
            throw new RuntimeException();
        }*/
        ArrayList<Long> cc = coach.getCc();
        int i=0;
        while(i<3) {
            cc.remove(i);
            i++;
        }
        assertEquals("Already have call up of this game",coach.callUpPlayers(cc, 12));
    }


    @Test
    //Only serves to delete all the data from the table "externalPunishments" to run the tests again
    public void DeleteAllData(){
        try {
            Statement statement = getDbConnection().createStatement();
            String query = "DELETE FROM externalPunishments";
            statement.execute(query);
            query = "DELETE FROM user";
            statement.execute(query);
            query = "DELETE FROM game_player";
            statement.execute(query);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}