package Users.tests;

import Users.CommonFeatures;
import Users.Manager;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {



    @Test
    public void testInsertUser(){
        Manager manager = new Manager();
        assertEquals(manager.insertUser(1, 123456789L,"Joao Silva","jajaxe9324@eilnews.com","psword123!","male","12-10-99", 123456789L,true, 180F, 190F,"top"),"User is now in the System");

    }

   /* public void deleteUsertest()
    {
        try(Connection connection=getDbConnection())
        {
            try(Statement stCheck=connection.createStatement())
            {



                // Initial cleanup:
                stCheck.executeUpdate("DELETE FROM person");
                stCheck.executeUpdate("DELETE FROM contractor");

                // Setting input parameters:
                String name="a";
                String address="b";
                String email="c@d.e";
                String phone="001";
                String city="f";
                int cvr=11;
                /*
                // Do the call:
                Contractor contractor=myClass.create(name, address, email, phone, city, cvr);

                // Javabean Checks: Check the javabean contains the expected values:
                assertEquals(name, contractor.getName());
                assertEquals(address, contractor.getAddress());
                ...

                // Database Checks:
                int personId;
                // Check the Person table contains one row with the expected values:
                try(ResultSet rs=stCheck.executeQuery("SELECT * FROM person"))
                {
                    assertTrue(rs.next());
                    personId=rs.getInt("id");
                    asssertEquals(name, rs.getString("name"));
                    asssertEquals(address, rs.getString("address"));
                    ...
                    assertFalse(rs.next());
                }

                // Check the Contractor table contains one row with the expected values:
                try(ResultSet rs=stCheck.executeQuery("SELECT * FROM contractor WHERE person_id="+personId))
                {
                    assertTrue(rs.next());
                    asssertEquals(2666, rs.getInt("cvr"));
                    ...
                    assertFalse(rs.next());
                }
            }
            finally
            {
                // Undo the testing operations:
                connection.rollback();
            }
        }
        catch (SQLException e)
        {
            fail(e.toString());
        }

    }*/

}

