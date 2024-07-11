//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.lang.reflect.Type;
import java.sql.*;
import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) throws Exception {
        BatchDemo();

    }
    public static void readRecords() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbcdemo";
        String userName="root";
        String passWord="sakthi";
        String query="select * from employee1";
        Connection con=DriverManager.getConnection(url,userName,passWord);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        while( rs.next()) {
            System.out.println("Id is:" + rs.getInt(1));
            System.out.println("Name is:" + rs.getString(2));
            System.out.println("Salary is:" + rs.getInt(3));
        }
        con.close();
    }
    public static void insertRecords() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbcdemo";
        String userName="root";
        String passWord="sakthi";
        String query="insert into employee1 values(5,'priya',250000)";
        Connection con=DriverManager.getConnection(url,userName,passWord);
        Statement st=con.createStatement();
        int rows=st.executeUpdate(query);
        System.out.println("No of rows Affected "+rows);
        con.close();
    }
    public static void insertValues() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbcdemo";
        String userName="root";
        String passWord="sakthi";
        int id=25;
        String name="kanishka";
        int salary=250000;
        String query="insert into employee1 values("+id+",'"+name+"',"+salary+")";
        Connection con=DriverManager.getConnection(url,userName,passWord);
        Statement st=con.createStatement();
        int rows=st.executeUpdate(query);
        System.out.println("No of rows Affected "+rows);
        con.close();
    }
    public static void insertUsingPre() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbcdemo";
        String userName="root";
        String passWord="sakthi";
        int id=26;
        String name="karthika";
        int salary=250000;
        String query="insert into employee1 values(?,?,?)";
        Connection con=DriverManager.getConnection(url,userName,passWord);
        PreparedStatement pt=con.prepareStatement(query);
        pt.setInt(1,id);
        pt.setString(2,name);
        pt.setInt(3,salary);
        int rows=pt.executeUpdate();
        System.out.println("No of rows Affected "+rows);
        con.close();
    }
    public static void DeleteData() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbcdemo";
        String userName="root";
        String passWord="sakthi";
        int id=25;
        String query="delete from employee1 where emp_id=?";
        Connection con=DriverManager.getConnection(url,userName,passWord);
        PreparedStatement pre=con.prepareStatement(query);
        pre.setInt(1,id);
        int row=pre.executeUpdate();
        System.out.println(row);
        con.close();
    }
    public static void DeleteDataSt() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbcdemo";
        String userName="root";
        String passWord="sakthi";
        int id=26;
        String query="delete from employee1 where emp_id="+id;
        Connection con=DriverManager.getConnection(url,userName,passWord);
        Statement st=con.createStatement();
        int row=st.executeUpdate(query);
        System.out.println(row);
        con.close();
    }
    public static void UpdatePre() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbcdemo";
        String userName="root";
        String passWord="sakthi";
        int id=1;
        int salary=25000;
        String query="update employee1 set salary=? where emp_id=?";
        Connection con=DriverManager.getConnection(url,userName,passWord);
        PreparedStatement pre=con.prepareStatement(query);
        pre.setInt(2,id);
        pre.setInt(1,salary);
        int row=pre.executeUpdate();
        System.out.println("No of row Affected:"+row);
        con.close();
    }
    public static void UpdateSt() throws Exception
    {
        String url="jdbc:mysql://localhost:3306/jdbcdemo";
        String userName="root";
        String passWord="sakthi";
        int id=2;
        int salary=105000;
        String query="update employee1 set salary="+salary+" where emp_id="+id;
        Connection con=DriverManager.getConnection(url,userName,passWord);
        Statement st=con.createStatement();
        int row= st.executeUpdate(query);
        System.out.println(row);
        con.close();
    }
    public static void SimpleStore() throws Exception
    {
       String url="jdbc:mysql://localhost:3306/jdbcdemo";
       String userName="root";
       String passWord="sakthi";
       Connection con=DriverManager.getConnection(url,userName,passWord);
       CallableStatement cst=con.prepareCall("{Call GetEmp()}");
       ResultSet rs=cst.executeQuery();
       while(rs.next())
       {
           System.out.println("Id is:"+rs.getInt(1));
           System.out.println("Name is:"+rs.getString(2));
           System.out.println("Salary is:"+rs.getInt(3));
       }
       con.close();


    }
    public static void SimpleStoreId() throws Exception
    {
        String url="jdbc:mysql://localhost:3306/jdbcdemo";
        String userName="root";
        String passWord="sakthi";
        int id=3;
        Connection con=DriverManager.getConnection(url,userName,passWord);
        CallableStatement cst=con.prepareCall("{Call GetEmpId(?)}");
        cst.setInt(1,id);
        ResultSet rs=cst.executeQuery();
        rs.next();
        System.out.println("Id is:"+rs.getInt(1));
        System.out.println("Name is:"+rs.getString(2));
        System.out.println("Salary is:"+rs.getInt(3));
        con.close();
    }
    public static void SimpleStoreOut() throws Exception
    {
        String url="jdbc:mysql://localhost:3306/jdbcdemo";
        String userName="root";
        String passWord="sakthi";
        int id=2;
        Connection con=DriverManager.getConnection(url,userName,passWord);
        CallableStatement cst= con.prepareCall("{Call GetNameById(?,?)}");
        cst.setInt(1,id);
        cst.registerOutParameter(2, Types.VARCHAR);
        cst.executeUpdate();
        System.out.println(cst.getString(2));
        con.close();

    }
    public static void CommitDemo() throws Exception
    {
        String url="jdbc:mysql://localhost:3306/jdbcdemo";
        String userName="root";
        String passWord="sakthi";
        String query1="update employee1 set salary=660000 where emp_id=1";
        String query2="update employee1 set salary=660000 where emp_id=2";
        Connection con=DriverManager.getConnection(url,userName,passWord);
        con.setAutoCommit(false);
        Statement st=con.createStatement();
        int row1=st.executeUpdate(query1);
        System.out.println("The row 1 affected:"+row1);
        int row2=st.executeUpdate(query2);
        System.out.println("The row 2 affected:"+row2);
        if(row1>0&&row2>0)
        {
            con.commit();
        }
        con.close();
    }
    public static void BatchDemo() throws Exception
    {
        String url="jdbc:mysql://localhost:3306/jdbcdemo";
        String userName="root";
        String passWord="sakthi";
        String query1="update employee1 set salary=40000000 where emp_id=1";
        String query2="update employee1 set salary=40000000 where emp_id=2";
        String query3="update employee1 set salary=40000000 where emp_id=3";
        Connection con=DriverManager.getConnection(url,userName,passWord);
        con.setAutoCommit(false);
        Statement st=con.createStatement();
        st.addBatch(query1);
        st.addBatch(query2);
        st.addBatch(query3);
        int[] row=st.executeBatch();
        for(int i:row)
        {
            if(i>0) {
                System.out.println("row affected:" + i);
                continue;
            }
            else
            {
                con.rollback();
            }
        }
        con.commit();
        con.close();

    }
}