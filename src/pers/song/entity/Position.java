package pers.song.entity;

/**
 * This class encapsulate position
 * @author Nathan Song
 * @version 2020-12-9
 */

public class Position
{
    //region Fields of Position
    private String ID;
    private String name;
    private String grade;
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

    public void setGrade (String grade)
    {
        this.grade = grade;
    }

    public String getGrade ()
    {
        return grade;
    }
}
