package FFFNJU;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by Daniel on 2016/8/14.
 */
public class FundInfos {
    public static void main(String[] args) {
        String filePath = "d:/fund_infos.txt";
        String urls = "jdbc:mysql://localhost:3306/fofquant?"
                + "user=root&password=&useUnicode=true&characterEncoding=UTF8";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(urls);
            Statement statement = conn.createStatement();
            String str = reader.readLine();
            String regex = "INSERT INTO fund_infos(establish_date,code,sec_name) VALUES (%s,%s,%s);";
            while ((str = reader.readLine()) != null) {
                String[] arr = str.split("\t");
                System.out.println(String.format(regex, arr[0].split(" ")[0], arr[1], arr[2]));
                statement.execute(String.format(regex, arr[0].split(" ")[0], arr[1], arr[2]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
