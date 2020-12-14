package pers.song.core;

import pers.song.gui.ExceptionFrame;
import pers.song.gui.LoginFrame;
import pers.song.gui.WarningFrame;

/**
 * This is the core of this program and it contains the main method
 * @author Nathan Song
 * @version 2020-12-11
 */

public class Core
{
    public static void main (String args[])
    {
        WarningFrame.WFExisting = false;
        ExceptionFrame.EFExisting = false;
        LoginFrame LF = new LoginFrame();
    }
}
