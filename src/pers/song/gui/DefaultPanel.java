/*
 * Created by JFormDesigner on Wed Dec 09 21:56:26 CST 2020
 */

package pers.song.gui;

import javax.swing.*;
import java.awt.*;

/**
 * When you enter the main frame at first time, you will see this panel. Same as other panels, you need to add this panel to another panel or frame
 * @author Nathan Song
 * @version 2020-12-9
 */

public class DefaultPanel extends JPanel
{
    public DefaultPanel ()
    {
        initComponents();
    }

    private void initComponents ()
    {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tipLabel = new JLabel();

        //======== this ========
        setLayout(new BorderLayout());

        //---- tipLabel ----
        tipLabel.setText("\u9009\u62e9\u5176\u4e2d\u4e00\u4e2a\u9009\u9879\u4ee5\u5f00\u59cb");
        tipLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tipLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 30));
        add(tipLabel, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel tipLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
