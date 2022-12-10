package Users.tests;

import Users.Coach;
import Users.CommonFeatures;
import Users.Doctor;
import Users.Manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.*;

import static Users.CommonFeatures.createDb;

public class ReadFilesToDB {

    public Connection connectDB() throws SQLException {
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        return DriverManager.getConnection(DATABASE_URL);
    }
    public void insertUsersFromTxt() throws SQLException {
        int type=0;
        Float height=null, weight=null;
        Long nCC=0L , phoneNumber=0L;
        Manager manager = new Manager();
        String name=null, email=null, sex=null, birthDate=null, password=null, position=null, typeS;
        boolean aptitude=true;
        try {
            File myObj = new File("dados\\users.txt");
            Scanner myReader = new Scanner(myObj);
            String[] array;
            String data=null;
            while (myReader.hasNextLine()){
                do{
                    data = myReader.nextLine();
                        array = data.split(":");
                        if (Objects.equals(array[0], "User")) {
                            System.out.println(array[1]);
                            switch (array[1]) {
                                case "Player" -> type = 2;
                                case "Manager" -> type = 4;
                                case "Coach" -> type = 3;
                                case "Doctor" -> type = 1;
                            }
                        }
                        else if (Objects.equals(array[0], "Name")) {
                            name = array[1];
                        }
                        else if (Objects.equals(array[0], "Email")) {
                            email = array[1];
                        } else if (Objects.equals(array[0], "BirthDate")) {
                            birthDate = array[1];
                        } else if (Objects.equals(array[0], "Password")) {
                            password = array[1];
                        }else if (Objects.equals(array[0], "Sex")) {
                            sex = array[1];
                        }else if (Objects.equals(array[0], "Phone")) {
                            phoneNumber = Long.parseLong(array[1]);
                        } else if (Objects.equals(array[0], "Citizen")) {
                            nCC = Long.parseLong(array[1]);
                        } else if(array[0].equals("Position")) {
                            if(array.length==1) position= null;
                            else position=array[1];
                        }
                        else if(array[0].equals("Aptitude")) {
                                if(array.length==1) aptitude= false;
                                else aptitude=Boolean.parseBoolean(array[1]);
                        }else if(array[0].equals("Height"))
                        {
                            if(array.length==1) height=null;
                            else height=Float.parseFloat(array[1]);
                        }else if(array[0].equals("Weight"))
                        {
                            if(array.length==1) weight=null;
                            else weight=Float.parseFloat(array[1]);
                        }
                    } while(!Objects.equals(data, "") && myReader.hasNextLine());
                    System.out.println(nCC + "    "+ name +"    "+ type +"    "+ email +"    "+ password +"    "+ sex + "    "+birthDate + "    "+phoneNumber+ "    "+ aptitude + "    "+position);
                    //System.out.println(manager.insertUser(type, nCC, name, email, password, sex, birthDate, phoneNumber, aptitude, height, weight, position));

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void insertGames() throws SQLException{
        Connection dbConn = connectDB();
        long coachCC = 0;
        String sqlQuery,date = null, equipaAdv=null, horaInicial=null, horaFinal=null, local=null;
        try {
            File myObj = new File("dados\\games.txt");
            Scanner myReader = new Scanner(myObj);
            String[] array;
            String data=null;
            while (myReader.hasNextLine()){
                do{
                    data = myReader.nextLine();
                    System.out.println(data);
                    array = data.split(":");
                    if (Objects.equals(array[0], "date")) {
                        date=array[1];
                    }
                    else if (Objects.equals(array[0], "equipaAdversaria")) {
                        equipaAdv = array[1];
                    }
                    else if (Objects.equals(array[0], "horaInicial")) {
                        horaInicial = array[1];
                    }
                    else if (Objects.equals(array[0], "horaFinal")) {
                        horaFinal = array[1];
                    }
                    else if (Objects.equals(array[0], "local")) {
                        local = array[1];
                    }
                    else if (Objects.equals(array[0], "coachCC")) {
                        coachCC = Long.parseLong(array[1]);
                    }
                } while(!Objects.equals(data, "") && myReader.hasNextLine());
                System.out.println(date+"\t"+equipaAdv+"\t"+horaInicial+"\t"+horaFinal+"\t"+local+"\t"+coachCC);
                Statement statement = dbConn.createStatement();
                sqlQuery= "INSERT INTO game VALUES(NULL,'"+date+"','"+equipaAdv+"','"+horaInicial+"','"+horaFinal+"','"+local+"','"+coachCC+"')";
                statement.executeUpdate(sqlQuery);
                statement.close();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void insertPractice() throws SQLException{

        long coachCC = 0;
        Coach coach = new Coach("camilasantos@acc.com");
        String sqlQuery = null,date = null, equipaAdv=null, horaInicial=null, horaFinal=null, local=null;
        try {
            File myObj = new File("dados\\practice.txt");
            Scanner myReader = new Scanner(myObj);
            String[] array;
            String data=null;
            while (myReader.hasNextLine()){
                do{
                    data = myReader.nextLine();
                    array = data.split(";");
                    if (Objects.equals(array[0], "local")) {
                        local=array[1];
                    }
                    else if (Objects.equals(array[0], "date")) {
                        date = array[1];
                    }
                    else if (Objects.equals(array[0], "startTime")) {
                        horaInicial = array[1];
                    }
                    else if (Objects.equals(array[0], "endTime")) {
                        horaFinal = array[1];
                    }
                    else if (Objects.equals(array[0], "coachCC")) {
                        coachCC = Long.parseLong(array[1]);
                    }
                } while(!Objects.equals(data, "") && myReader.hasNextLine());
                System.out.println(date+"\t"+local+"\t"+horaInicial+"\t"+horaFinal+"\t"+coachCC);
                Long[] anotherList = new Long[] {585005353L, 412191513L,837587143L,451707431L,255345124L};
                ArrayList<Long> playersCC = new ArrayList<>(List.of(anotherList));
                System.out.println(coach.scheduleTrainingSession(playersCC, local, date, horaInicial, horaFinal));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public void insertAppointments() throws SQLException{
        Doctor doc=new Doctor("mariaamelia@acc.com");
        long playerCC=0;
        String sqlQuery = null,date = null, horaInicial=null, horaFinal=null;
        try {
            File myObj = new File("dados\\appointments.txt");
            Scanner myReader = new Scanner(myObj);
            String[] array;
            String data=null;
            while (myReader.hasNextLine()){
                do{
                    data = myReader.nextLine();
                    array = data.split(";");
                    if (Objects.equals(array[0], "date")) {
                        date = array[1];
                    }
                    else if (Objects.equals(array[0], "startTime")) {
                        horaInicial = array[1];
                    }
                    else if (Objects.equals(array[0], "endTime")) {
                        horaFinal = array[1];
                    } else if (Objects.equals(array[0], "playerCC")) {
                        playerCC = Long.parseLong(array[1]);
                    }

                } while(!Objects.equals(data, "") && myReader.hasNextLine());
                System.out.println(playerCC+"\t"+date+"\t"+horaInicial+"\t"+horaFinal+"\t");
                System.out.println(doc.ScheduleMedicalAppointments(playerCC,date,horaInicial,horaFinal));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertExternalPunishments() throws SQLException{


        Coach coach = new Coach("camilasantos@acc.com");
        String sqlQuery = null, notes=null;
        int numberGames=0;
        long playerCC = 0;
        try {
            File myObj = new File("dados\\externalPunishments.txt");
            Scanner myReader = new Scanner(myObj);
            String[] array;
            String data=null;
            while (myReader.hasNextLine()){
                do{
                    data = myReader.nextLine();
                    array = data.split(";");
                    if (Objects.equals(array[0], "playerCC")) {
                        playerCC =Long.parseLong(array[1]);
                    }
                    else if (Objects.equals(array[0], "notes")) {
                        notes = array[1];
                    }
                    else if (Objects.equals(array[0], "numberGames")) {
                        numberGames = Integer.parseInt(array[1]);
                    }

                } while(!Objects.equals(data, "") && myReader.hasNextLine());
                System.out.println(playerCC+"\t"+notes+"\t"+numberGames+"\t");
                System.out.println(coach.InsertPlayersPunishement(playerCC,notes,numberGames));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        ReadFilesToDB readFilestoDB = new ReadFilesToDB();
        //readFilestoDB.insertUsersFromTxt(); //woks
        //readFilestoDB.insertGames();//works
        //readFilestoDB.insertPractice(); //works
        //readFilestoDB.insertAppointments(); //work
        //readFilestoDB.insertExternalPunishments();//work
    }
}