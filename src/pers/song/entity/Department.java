package pers.song.entity;

/**
 * This class encapsulate department
 * @author Nathan Song
 * @version 2020-12-9
 */

public class Department
{
    //region Fields of Department
    private String ID;
    private String name;
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
}
