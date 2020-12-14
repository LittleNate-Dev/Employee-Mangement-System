package pers.song.entity;

/**
 * This class encapsulate employee
 * @author Nathan Song
 * @version 2020-12-9
 */

public class Employee
{
    //region Fields of Employee
    private String ID;
    private String name;
    private String sex;
    private String birthday;
    private String dept;
    private String position;
    //endregion

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getID ()
    {
        return ID;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getName ()
    {
        return name;
    }

    public void setSex (String sex)
    {
        this.sex = sex;
    }

    public String getSex ()
    {
        return sex;
    }

    public void setBirthday (String birthday)
    {
        this.birthday = birthday;
    }

    public String getBirthday ()
    {
        return birthday;
    }

    public void setDept (String dept)
    {
        this.dept = dept;
    }

    public String getDept ()
    {
        return dept;
    }

    public void setPosition (String position)
    {
        this.position = position;
    }

    public String getPosition ()
    {
        return position;
    }
}
