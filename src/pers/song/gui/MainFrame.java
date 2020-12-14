/*
 * Created by JFormDesigner on Mon Dec 07 17:50:50 CST 2020
 */

package pers.song.gui;

import pers.song.dao.DBController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This frame contains functions of this program, including add, search, delete and modify
 * @author Nathan Song
 * @version 2020-12-11
 */

public class MainFrame extends JFrame
{
    private DBController DBC;
    private LoginFrame owner;

    //region Panels that be added to the mainPanel
    private CardLayout card;
    private EmployeePanel EP;
    private DepartmentPanel DP;
    private PositionPanel PP;
    private DefaultPanel DefaultP;
    //endregion

    public MainFrame (DBController DBC, LoginFrame owner)
    {
        this.DBC = DBC;
        this.owner = owner;
        initComponents();
        SetFrame();
        SetPanel();
        card.show(mainPanel, "DefaultP");
        DBC.SetOwner(this);
    }

    private void SetFrame () // Customize frame
    {
        setLocationRelativeTo(null); // Set the location of the frame to the center of the screen
        setBounds(this.getX(), this.getY(), 1100, 810);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void SetPanel() // Customize panels
    {
        card = new CardLayout();
        EP = new EmployeePanel(DBC, this);
        DP = new DepartmentPanel(DBC, this);
        PP = new PositionPanel(DBC, this);
        DefaultP = new DefaultPanel();
        mainPanel.setLayout(card);
        mainPanel.add("EP", EP);
        mainPanel.add("DP", DP);
        mainPanel.add("PP", PP);
        mainPanel.add("DefaultP", DefaultP);
    }

    private void exitButtonActionPerformed (ActionEvent e)
    {
        if (DBC.CloseConnection())
        {
            owner.ClearTextField();
            owner.setVisible(true);
            this.dispose();
        }
        else
        {
            if (!ExceptionFrame.EFExisting)
            {
                ExceptionFrame EF = new ExceptionFrame(this, "程序异常", "程序发生异常！错误信息：", DBC.GetExceptionAll());
            }
        }
    }

    private void employeeButtonActionPerformed (ActionEvent e)
    {
        card.show(mainPanel,"EP");
        mainPanel.validate();
    }

    private void departmentButtonActionPerformed (ActionEvent e)
    {
        card.show(mainPanel, "DP");
        mainPanel.validate();
    }

    private void positionButtonActionPerformed (ActionEvent e)
    {
        card.show(mainPanel, "PP");
        mainPanel.validate();
    }

    private void thisWindowClosed (WindowEvent e)
    {
        DBC.DisconnectDB();
    }

    private void initComponents ()
    {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane = new JScrollPane();
        buttonPanel = new JPanel();
        employeeButton = new JButton();
        departmentButton = new JButton();
        positionButton = new JButton();
        exitButton = new JButton();
        mainPanel = new JPanel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/pers/Song/resources/picture/Manger.png")).getImage());
        setTitle("\u793e\u755c\u7ba1\u7406\u5668");
        setMinimumSize(new Dimension(1100, 810));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                thisWindowClosed(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== scrollPane ========
        {
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

            //======== buttonPanel ========
            {
                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));

                //---- employeeButton ----
                employeeButton.setText("\u5458\u5de5\u7ba1\u7406");
                employeeButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));
                employeeButton.addActionListener(e -> {
			employeeButtonActionPerformed(e);
			employeeButtonActionPerformed(e);
		});
                buttonPanel.add(employeeButton);

                //---- departmentButton ----
                departmentButton.setText("\u90e8\u95e8\u7ba1\u7406");
                departmentButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));
                departmentButton.addActionListener(e -> departmentButtonActionPerformed(e));
                buttonPanel.add(departmentButton);

                //---- positionButton ----
                positionButton.setText("\u804c\u4f4d\u7ba1\u7406");
                positionButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));
                positionButton.addActionListener(e -> positionButtonActionPerformed(e));
                buttonPanel.add(positionButton);

                //---- exitButton ----
                exitButton.setText("\u9000\u51fa\u767b\u5f55");
                exitButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));
                exitButton.addActionListener(e -> exitButtonActionPerformed(e));
                buttonPanel.add(exitButton);
            }
            scrollPane.setViewportView(buttonPanel);
        }
        contentPane.add(scrollPane, BorderLayout.NORTH);

        //======== mainPanel ========
        {
            mainPanel.setLayout(new CardLayout());
        }
        contentPane.add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private JButton employeeButton;
    private JButton departmentButton;
    private JButton positionButton;
    private JButton exitButton;
    private JPanel mainPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
