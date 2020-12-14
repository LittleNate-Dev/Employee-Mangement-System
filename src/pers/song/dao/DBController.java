package pers.song.dao;

import pers.song.entity.Department;
import pers.song.entity.Employee;
import pers.song.entity.Position;
import pers.song.gui.ExceptionFrame;
import pers.song.gui.WarningFrame;

import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.*;
import java.util.LinkedList;

/**
 * You can control the database by using this class
 * @author Nathan Song
 * @version 2020-12-11
 */

public class DBController
{
    //region Controller parameters
    private String URL;
    private String userName;
    private String password;
    private Connection connection;
    private Statement statement;
    private ResultSet rs;
    private PreparedStatement ps;
    //endregion

    private String exceptionMessageAll;
    private String exceptionMessage;
    private Writer writer;
    private PrintWriter PW;
    private Window owner;

    public DBController (String userName, String password)
    {
        URL = "jdbc:mysql://localhost:3306/ems?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true";
        this.userName = userName;
        this.password = password;
    }

    public void SetOwner (Window owner)
    {
        this.owner = owner;
    }

    public boolean CloseConnection () // Use this method to disconnect to the DB
    {
        try
        {
            connection.close();
            return true;
        }
        catch (Exception throwables)
        {
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            throwables.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            return false;
        }
    }

    public String GetException () // Return the exception message
    {
        return exceptionMessage;
    }

    public String GetExceptionAll () // Return full exception message
    {
        return exceptionMessageAll;
    }

    public boolean ConnectDB () // Connect to the database
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, userName, password);
            statement = connection.createStatement();
            return true;
        }
        catch (ClassNotFoundException e)
        {
            exceptionMessage = e.getMessage();
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            e.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            return false;
        }
        catch (SQLException throwables)
        {
            exceptionMessage = throwables.getMessage();
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            throwables.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            return false;
        }
    }

    public void  DisconnectDB ()
    {
        try
        {
            connection.close();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    //region Employee
    public Employee SearchEmployee (String searchName) // Use this method to search the certain employee
    {
        Employee employee = new Employee();
        try
        {
            ps = connection.prepareStatement(" Select * from Employee Where Name = ? ");
            if (!searchName.equals(""))
            {
                ps.setString(1,searchName);
                rs = ps.executeQuery();
                while (rs.next())
                {
                    employee.setID(rs.getString(1));
                    employee.setName(rs.getString(2));
                    employee.setSex(rs.getString(3));
                    employee.setBirthday(rs.getString(4));
                    employee.setDept(rs.getString(5));
                    employee.setPosition(rs.getString(6));
                }
            }
        }
        catch (SQLException throwables)
        {
            exceptionMessage = throwables.getMessage();
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            throwables.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            if (!ExceptionFrame.EFExisting)
            {
                ExceptionFrame EF = new ExceptionFrame(owner, "程序异常", "程序出现异常！异常信息如下：", exceptionMessageAll);
            }
        }
        return employee;
    }

    public LinkedList<Employee> SearchEmployeeAll () // Use this method to search all employee
    {
        LinkedList<Employee> LLE = new LinkedList<Employee>();
        try
        {
            ps = connection.prepareStatement(" Select * from Employee");
            rs = ps.executeQuery();
            while (rs.next())
            {
                Employee employee = new Employee();
                employee.setID(rs.getString(1));
                employee.setName(rs.getString(2));
                employee.setSex(rs.getString(3));
                employee.setBirthday(rs.getString(4));
                employee.setDept(rs.getString(5));
                employee.setPosition(rs.getString(6));
                LLE.add(employee);
            }
        }
        catch (SQLException throwables)
        {
            exceptionMessage = throwables.getMessage();
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            throwables.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            if (!ExceptionFrame.EFExisting)
            {
                ExceptionFrame EF = new ExceptionFrame(owner, "程序异常", "程序出现异常！异常信息如下：", exceptionMessageAll);
            }
        }
        return LLE;
    }

    public void DeleteEmployee (String deleteName) // Delete certain employee
    {
        try
        {
            ps = connection.prepareStatement("Delete from Employee Where Name = ? ");
            ps.setString(1, deleteName);
            ps.execute();
            if (!WarningFrame.WFExisting)
            {
                WarningFrame WF = new WarningFrame(owner, "提示", "员工删除成功！");
            }
        }
        catch (SQLException throwables)
        {
            exceptionMessage = throwables.getMessage();
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            throwables.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            if (!ExceptionFrame.EFExisting)
            {
                ExceptionFrame EF = new ExceptionFrame(owner, "程序异常", "程序出现异常！异常信息如下：", exceptionMessageAll);
            }
        }
    }

    public void InsertEmployee (Employee employee) // Insert new employee
    {
        try
        {
            ps = connection.prepareStatement("Insert into Employee values( ?, ?, ?, ?, ?, ? )");
            ps.setString(1, employee.getID());
            ps.setString(2, employee.getName());
            ps.setString(3, employee.getSex());
            ps.setString(4, employee.getBirthday());
            ps.setString(5, employee.getDept());
            ps.setString(6, employee.getPosition());
            ps.execute();
            if (!WarningFrame.WFExisting)
            {
                WarningFrame WF = new WarningFrame(owner, "提示", "记录插入成功！");
            }
        }
        catch
        (SQLException throwables)
        {
            exceptionMessage = throwables.getMessage();
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            throwables.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            if (!ExceptionFrame.EFExisting)
            {
                ExceptionFrame EF = new ExceptionFrame(owner, "程序异常", "程序出现异常！异常信息如下：", exceptionMessageAll);
            }
        }
    }

    public void UpdateEmployee (Employee employee) // Update the certain Employee
    {
        try
        {
            ps = connection.prepareStatement("Update Employee Set ID = ?, Name = ?, Sex = ?, Birthday = ?, Dept = ?, Position = ? Where ID = ?");
            ps.setString(1, employee.getID());
            ps.setString(2, employee.getName());
            ps.setString(3, employee.getSex());
            ps.setString(4, employee.getBirthday());
            ps.setString(5, employee.getDept());
            ps.setString(6, employee.getPosition());
            ps.setString(7, employee.getID());
            ps.execute();
            if (!WarningFrame.WFExisting)
            {
                WarningFrame WF = new WarningFrame(owner, "提示", "记录更新成功！");
            }
        }
        catch (SQLException throwables)
        {
            exceptionMessage = throwables.getMessage();
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            throwables.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            if (!ExceptionFrame.EFExisting)
            {
                ExceptionFrame EF = new ExceptionFrame(owner, "程序异常", "程序出现异常！异常信息如下：", exceptionMessageAll);
            }
        }
    }
    //endregion

    //region Department
    public Department SearchDepartment (String searchName) // Use this method to search the certain department
    {
        Department department = new Department();
        try
        {
            ps = connection.prepareStatement(" Select * from Department Where Name = ? ");
            if (!searchName.equals(""))
            {
                ps.setString(1,searchName);
                rs = ps.executeQuery();
                while (rs.next())
                {
                    department.setID(rs.getString(1));
                    department.setName(rs.getString(2));
                }
            }
        }
        catch (SQLException throwables)
        {
            exceptionMessage = throwables.getMessage();
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            throwables.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            if (!ExceptionFrame.EFExisting)
            {
                ExceptionFrame EF = new ExceptionFrame(owner, "程序异常", "程序出现异常！异常信息如下：", exceptionMessageAll);
            }
        }
        return department;
    }

    public LinkedList<Department> SearchDepartmentAll () // Use this method to search all department
    {
        LinkedList<Department> LLD = new LinkedList<Department>();
        try
        {
            ps = connection.prepareStatement(" Select * from Department");
            rs = ps.executeQuery();
            while (rs.next())
            {
                Department department = new Department();
                department.setID(rs.getString(1));
                department.setName(rs.getString(2));
                LLD.add(department);
            }
        }
        catch (SQLException throwables)
        {
            exceptionMessage = throwables.getMessage();
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            throwables.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            if (!ExceptionFrame.EFExisting)
            {
                ExceptionFrame EF = new ExceptionFrame(owner, "程序异常", "程序出现异常！异常信息如下：", exceptionMessageAll);
            }
        }
        return LLD;
    }

    public void DeleteDeaprtment (String deleteName) // Delete certain department
    {
        try
        {
            ps = connection.prepareStatement("Delete from Department Where Name = ? ");
            ps.setString(1, deleteName);
            ps.execute();
            if (!WarningFrame.WFExisting)
            {
                WarningFrame WF = new WarningFrame(owner, "提示", "部门删除成功！");
            }
        }
        catch (SQLException throwables)
        {
            exceptionMessage = throwables.getMessage();
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            throwables.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            if (!ExceptionFrame.EFExisting)
            {
                ExceptionFrame EF = new ExceptionFrame(owner, "程序异常", "程序出现异常！异常信息如下：", exceptionMessageAll);
            }
        }
    }

    public void InsertDepartment (Department department) // Insert new employee
    {
        try
        {
            ps = connection.prepareStatement("Insert into Department values( ?, ? )");
            ps.setString(1, department.getID());
            ps.setString(2, department.getName());
            ps.execute();
            if (!WarningFrame.WFExisting)
            {
                WarningFrame WF = new WarningFrame(owner, "提示", "记录插入成功！");
            }
        }
        catch
        (SQLException throwables)
        {
            exceptionMessage = throwables.getMessage();
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            throwables.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            if (!ExceptionFrame.EFExisting)
            {
                ExceptionFrame EF = new ExceptionFrame(owner, "程序异常", "程序出现异常！异常信息如下：", exceptionMessageAll);
            }
        }
    }

    public void UpdateDepartment (Department department) // Update the certain department
    {
        try
        {
            ps = connection.prepareStatement("Update Department Set ID = ?, Name = ? Where ID = ?");
            ps.setString(1, department.getID());
            ps.setString(2, department.getName());
            ps.setString(3, department.getID());
            ps.execute();
            if (!WarningFrame.WFExisting)
            {
                WarningFrame WF = new WarningFrame(owner, "提示", "记录更新成功！");
            }
        }
        catch (SQLException throwables)
        {
            exceptionMessage = throwables.getMessage();
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            throwables.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            if (!ExceptionFrame.EFExisting)
            {
                ExceptionFrame EF = new ExceptionFrame(owner, "程序异常", "程序出现异常！异常信息如下：", exceptionMessageAll);
            }
        }
    }
    //endregion

    //region Position
    public Position SearchPosition (String searchName) // Use this method to search the certain department
    {
        Position position = new Position();
        try
        {
            ps = connection.prepareStatement(" Select * from Position Where Name = ? ");
            if (!searchName.equals(""))
            {
                ps.setString(1,searchName);
                rs = ps.executeQuery();
                while (rs.next())
                {
                    position.setID(rs.getString(1));
                    position.setName(rs.getString(2));
                    position.setGrade(rs.getString(3));
                }
            }
        }
        catch (SQLException throwables)
        {
            exceptionMessage = throwables.getMessage();
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            throwables.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            if (!ExceptionFrame.EFExisting)
            {
                ExceptionFrame EF = new ExceptionFrame(owner, "程序异常", "程序出现异常！异常信息如下：", exceptionMessageAll);
            }
        }
        return position;
    }

    public LinkedList<Position> SearchPositionAll () // Use this method to search all department
    {
        LinkedList<Position> LLP = new LinkedList<Position>();
        try
        {
            ps = connection.prepareStatement(" Select * from Position");
            rs = ps.executeQuery();
            while (rs.next())
            {
                Position position = new Position();
                position.setID(rs.getString(1));
                position.setName(rs.getString(2));
                position.setGrade(rs.getString(3));
                LLP.add(position);
            }
        }
        catch (SQLException throwables)
        {
            exceptionMessage = throwables.getMessage();
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            throwables.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            if (!ExceptionFrame.EFExisting)
            {
                ExceptionFrame EF = new ExceptionFrame(owner, "程序异常", "程序出现异常！异常信息如下：", exceptionMessageAll);
            }
        }
        return LLP;
    }

    public void DeletePosition (String deleteName) // Delete certain department
    {
        try
        {
            ps = connection.prepareStatement("Delete from Position Where Name = ? ");
            ps.setString(1, deleteName);
            ps.execute();
            if (!WarningFrame.WFExisting)
            {
                WarningFrame WF = new WarningFrame(owner, "提示", "职位删除成功！");
            }
        }
        catch (SQLException throwables)
        {
            exceptionMessage = throwables.getMessage();
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            throwables.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            if (!ExceptionFrame.EFExisting)
            {
                ExceptionFrame EF = new ExceptionFrame(owner, "程序异常", "程序出现异常！异常信息如下：", exceptionMessageAll);
            }
        }
    }

    public void InsertPosition (Position position) // Insert new employee
    {
        try
        {
            ps = connection.prepareStatement("Insert into Position values( ?, ?, ? )");
            ps.setString(1, position.getID());
            ps.setString(2, position.getName());
            ps.setString(3, position.getGrade());
            ps.execute();
            if (!WarningFrame.WFExisting)
            {
                WarningFrame WF = new WarningFrame(owner, "提示", "记录插入成功！");
            }
        }
        catch
        (SQLException throwables)
        {
            exceptionMessage = throwables.getMessage();
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            throwables.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            if (!ExceptionFrame.EFExisting)
            {
                ExceptionFrame EF = new ExceptionFrame(owner, "程序异常", "程序出现异常！异常信息如下：", exceptionMessageAll);
            }
        }
    }

    public void UpdatePosition (Position position) // Update the certain department
    {
        try
        {
            ps = connection.prepareStatement("Update Position Set ID = ?, Name = ?, Grade = ? Where ID = ?");
            ps.setString(1, position.getID());
            ps.setString(2, position.getName());
            ps.setString(3, position.getGrade());
            ps.setString(4, position.getID());
            ps.execute();
            if (!WarningFrame.WFExisting)
            {
                WarningFrame WF = new WarningFrame(owner, "提示", "记录更新成功！");
            }
        }
        catch (SQLException throwables)
        {
            exceptionMessage = throwables.getMessage();
            writer = new StringWriter();
            PW = new PrintWriter(writer);
            throwables.fillInStackTrace().printStackTrace(PW);
            exceptionMessageAll = writer.toString();
            if (!ExceptionFrame.EFExisting)
            {
                ExceptionFrame EF = new ExceptionFrame(owner, "程序异常", "程序出现异常！异常信息如下：", exceptionMessageAll);
            }
        }
    }
    //endregion
}
