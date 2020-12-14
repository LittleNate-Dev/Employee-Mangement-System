/*
 * Created by JFormDesigner on Mon Dec 07 12:04:54 CST 2020
 */

package pers.song.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Nathan
 * @version 2020-12-7
 */

public class ExceptionFrame extends JDialog
{
    //region Parameters of the frame
    String title;
    String warningMessage;
    String exceptionMessage;
    public static boolean EFExisting = false; // Detect whether the ExceptionFrame is already existed
    //endregion

    public ExceptionFrame (Window owner, String title, String warningMessage, String exceptionMessage)
    {
        super(owner);
        this.title = title;
        this.warningMessage = warningMessage;
        this.exceptionMessage = exceptionMessage;
        initComponents();
        SetFrame();
        SetText();
        ExceptionFrame.EFExisting = true;
    }

    private void thisWindowClosed (WindowEvent e)
    {
        ExceptionFrame.EFExisting = false;
    }

    private void initComponents ()
    {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        warningMessageLabel = new JLabel();
        exceptionPanel = new JScrollPane();
        exceptionInfoBox = new JTextArea();

        //======== this ========
        setMinimumSize(new Dimension(540, 500));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                thisWindowClosed(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 0));

        //---- warningMessageLabel ----
        warningMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        warningMessageLabel.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 22));
        contentPane.add(warningMessageLabel);

        //======== exceptionPanel ========
        {
            exceptionPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            exceptionPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            //---- exceptionInfoBox ----
            exceptionInfoBox.setEditable(false);
            exceptionInfoBox.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
            exceptionInfoBox.setColumns(20);
            exceptionPanel.setViewportView(exceptionInfoBox);
        }
        contentPane.add(exceptionPanel);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void SetFrame () // Customize frame
    {
        setLocationRelativeTo(null); // Set the location of the frame to the center of the screen
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(title);
        setVisible(true);
    }

    private void SetText () // Customize text
    {
        warningMessageLabel.setText(warningMessage);
        exceptionInfoBox.setText(exceptionMessage);
        exceptionInfoBox.setLineWrap(true);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel warningMessageLabel;
    private JScrollPane exceptionPanel;
    private JTextArea exceptionInfoBox;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
