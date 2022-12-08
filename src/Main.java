import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final String DB_URL = "jdbc:mysql://localhost:3306/cms";
    static final String USER = "root";
    static final String PASS = "thuong";
    static List<Employee> employeeList=new ArrayList<>();
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);


            conn.setAutoCommit(false);
            System.out.println("Create SQL");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            System.out.println("Insert 1 row");
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter id:");
            int id=sc.nextInt();
            System.out.println("Enter name:");
            sc.nextLine();
            String name = sc.nextLine();

            System.out.println("enter Salary");

            Double salary = sc.nextDouble();


            String SQL = "insert into employees(id,`name`,salary)" +
                    "values(?,?,?)";

PreparedStatement ps=conn.prepareStatement(SQL);
ps.setInt(1, id);
ps.setString(2,name);
ps.setDouble(3,salary);
           ps.executeUpdate();
            System.out.println("confirm change");
            conn.commit();
            String sql = "select *from employees";
            PreparedStatement PS=conn.prepareStatement(sql);
            ResultSet rs = PS.executeQuery();
            while (rs.next()){
                int id1=rs.getInt("id");
                String name1=rs.getString("name");
                Double salary1=rs.getDouble("salary");
                employeeList.add(new Employee(id1,name1,salary1));
            }
            System.out.println("list to references");
printRs();
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("turn back status from to change");
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public static void printRs(){
        for (Employee em:employeeList
             ) {
            System.out.println(em.toString());
        }
    }

}
