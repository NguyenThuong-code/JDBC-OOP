import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class Connnection {
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/cms";
    static final String USER = "root";
    static final String PASS = "thuong";

    public static void main(String[] args) {
        Connection conn=null;
        Statement stmt=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database");
            conn= DriverManager.getConnection(DB_URL,USER,PASS);


            conn.setAutoCommit(false);

            System.out.println("Insert 1 row");
            String SQL = "insert into students(id, firstname,lastname, score)" +
                    "values(3, 'Nguyen','Thuong',7.5)";
            stmt.executeUpdate(SQL);
            SQL = "insert into students(id, firstname,lastname, score)" +
                    "values(4, 'Nguyen','Thuong',8.5)";
            stmt.executeUpdate(SQL);
            System.out.println("Create SQL");
            stmt= conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            System.out.println("confirm change");
            conn.commit();
            String sql="select *from students";
            ResultSet rs=stmt.executeQuery(sql);
            System.out.println("list to references");
            printRs(rs);
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("turn back status from to change");
            try {
                if (conn!=null)
                    conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (Exception se){
            se.printStackTrace();
        }finally {
            try {
                if (conn!=null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn!=null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public static void printRs(ResultSet rs)throws SQLException{
        rs.beforeFirst();
        while (rs.next()){
            int id= rs.getInt("id");
            int score =rs.getInt("score");
            String firstName=rs.getString("firstname");
            String lastName=rs.getString("lastname");
            System.out.println("\nID: " +id);
            System.out.println("\nFirstName: " +firstName);
            System.out.println("\nLastName: " +lastName);
            System.out.println("\nScore: " +score);
            System.out.println("\n==============");
        }
        System.out.println();
    }
}
