package kata5p1;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KATA5P1 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        Class.forName ("org.sqlite.JDBC");
        String urlConection = "jdbc:sqlite:KATA5.db";
        Connection connection = DriverManager.getConnection(urlConection);
        
        Statement statement = connection.createStatement();
        String query = "Select * from PEOPLE";
        ResultSet rs = statement.executeQuery(query);
        
        while(rs.next()){
            System.out.println(rs.getString("Name"));
            System.out.println(rs.getInt("Id"));
        }
        
        query = "CREATE TABLE IF NOT EXISTS MAIL ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'Mail' TEXT NOT NULL);";
        statement.executeUpdate(query);
        
        String nameFile = "emails.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new 
        File(nameFile)));

        String mail;
        while((mail=reader.readLine())!=null) {
            if(!mail.contains("@")) continue;
            query = "INSERT INTO MAIL (Mail) VALUES ('" + mail + "');";
            //System.out.println(query);
            statement.executeUpdate(query);
        }
        query = "Select count (*) from MAIL";
        rs = statement.executeQuery(query);
        System.out.println(rs.getInt("count (*)"));
    }
}
