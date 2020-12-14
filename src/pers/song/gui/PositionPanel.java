/*
 * Created by JFormDesigner on Wed Dec 09 18:26:31 CST 2020
 */

package pers.song.gui;

import pers.song.dao.DBController;
import pers.song.entity.Position;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

/**
 * This panel can let you manage position, and you need to add this panel to another panel or frame
 * @author Nathan Song
 * @version 2020-12-9
 */

public class PositionPanel extends JPanel
{
    private DBController DBC;
    private Window owner;
    private DefaultTableModel DTM;

    public PositionPanel (DBController DBC, Window owner)
    {
        this.DBC = DBC;
        this.owner = owner;
        initComponents();
        setTable();
    }

    private void searchButtonActionPerformed (ActionEvent e)
    {
        DTM.setRowCount(0);
        Position position = new Position();
        if (!nameBox.getText().equals(""))
        {
            position = DBC.SearchPosition(nameBox.getText());
            Object [] result = {position.getID(), position.getName(), position.getGrade()};
            DTM.addRow(result);
        }
        else
        {
            if(!WarningFrame.WFExisting)
            {
                WarningFrame WF = new WarningFrame(owner,"提示", "请输入职位名称！");
            }
        }
    }

    private void searchAllButtonActionPerformed (ActionEvent e)
    {
        DTM.setRowCount(0);
        LinkedList<Position> LLP = new LinkedList<Position>();
        LLP = DBC.SearchPositionAll();
        for (int i = 1; i <= LLP.size(); i++)
        {
            Object [] result = {LLP.get(i-1).getID(), LLP.get(i-1).getName(), LLP.get(i-1).getGrade()};
            DTM.addRow(result);
        }
    }

    private void deleteButtonActionPerformed (ActionEvent e)
    {
        if (resultTable.getSelectedRow() != -1)
        {
            String deleteName= resultTable.getValueAt(resultTable.getSelectedRow(), 1).toString();
            DBC.DeletePosition(deleteName);
            DTM.setRowCount(0);
            LinkedList<Position> LLP = new LinkedList<Position>();
            LLP = DBC.SearchPositionAll();
            for (int i = 1; i <= LLP.size(); i++)
            {
                Object [] result = {LLP.get(i-1).getID(), LLP.get(i-1).getName(), LLP.get(i-1).getGrade()};
                DTM.addRow(result);
            }
        }
        else
        {
            if (!WarningFrame.WFExisting)
            {
                WarningFrame WF = new WarningFrame(owner,"提示","请选中其中一行！");
            }
        }
    }

    private void insertButtonActionPerformed (ActionEvent e)
    {
        if (resultTable.getSelectedRow() != -1)
        {
            try
            {
                Position position = new Position();
                position.setID(resultTable.getValueAt(resultTable.getSelectedRow(), 0).toString());
                position.setName(resultTable.getValueAt(resultTable.getSelectedRow(), 1).toString());
                position.setGrade(resultTable.getValueAt(resultTable.getSelectedRow(), 2).toString());
                if (position.getID().equals(""))
                {
                    if (!WarningFrame.WFExisting)
                    {
                        WarningFrame WF = new WarningFrame(owner, "提示", "主键不能为空！");
                    }
                }
                else
                {
                    DBC.InsertPosition(position);
                }
            }
            catch (Exception exception)
            {
                if (exception.getMessage().equals("Cannot invoke \"Object.toString()\" because the return value of \"javax.swing.JTable.getValueAt(int, int)\" is null"))
                {
                    if (!WarningFrame.WFExisting)
                    {
                        WarningFrame WF = new WarningFrame(owner, "提示", "请输入内容！");
                    }
                }
            }
        }
        else
        {
            if (!WarningFrame.WFExisting)
            {
                WarningFrame WF = new WarningFrame(owner, "提示", "请选中一行数据！");
            }
        }
    }

    private void addNewRowActionPerformed (ActionEvent e)
    {
        DTM.addRow(new Object[0]);
    }

    private void updateButtonActionPerformed (ActionEvent e)
    {
        if (resultTable.getSelectedRow() != -1)
        {
            try
            {
                Position position = new Position();
                position.setID(resultTable.getValueAt(resultTable.getSelectedRow(), 0).toString());
                position.setName(resultTable.getValueAt(resultTable.getSelectedRow(), 1).toString());
                position.setGrade(resultTable.getValueAt(resultTable.getSelectedRow(), 2).toString());
                if (position.getID().equals(""))
                {
                    if (!WarningFrame.WFExisting)
                    {
                        WarningFrame WF = new WarningFrame(owner, "提示", "主键不能为空！");
                    }
                }
                else
                {
                    DBC.UpdatePosition(position);
                }
            }
            catch (Exception exception)
            {
                if (exception.getMessage().equals("Cannot invoke \"Object.toString()\" because the return value of \"javax.swing.JTable.getValueAt(int, int)\" is null"))
                {
                    if (!WarningFrame.WFExisting)
                    {
                        WarningFrame WF = new WarningFrame(owner, "提示", "请输入内容！");
                    }
                }
            }
        }
        else
        {
            if (!WarningFrame.WFExisting)
            {
                WarningFrame WF = new WarningFrame(owner, "提示", "请选中一行数据！");
            }
        }
    }

    private void initComponents ()
    {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        upPanel = new JPanel();
        topPanel = new JPanel();
        nameLabel = new JLabel();
        nameBox = new JTextField();
        scrollPanel = new JScrollPane();
        buttonPanel = new JPanel();
        searchButton = new JButton();
        searchAllButton = new JButton();
        deleteButton = new JButton();
        updateButton = new JButton();
        insertButton = new JButton();
        addNewRow = new JButton();
        downPanel = new JScrollPane();
        resultTable = new JTable();

        //======== this ========
        setLayout(new BorderLayout());

        //======== upPanel ========
        {
            upPanel.setLayout(new GridLayout(2, 0, 50, 0));

            //======== topPanel ========
            {
                topPanel.setLayout(new BorderLayout(20, 0));

                //---- nameLabel ----
                nameLabel.setText("\u804c\u4f4d\u540d\u79f0");
                nameLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
                topPanel.add(nameLabel, BorderLayout.WEST);

                //---- nameBox ----
                nameBox.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
                topPanel.add(nameBox, BorderLayout.CENTER);
            }
            upPanel.add(topPanel);

            //======== scrollPanel ========
            {
                scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

                //======== buttonPanel ========
                {
                    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));

                    //---- searchButton ----
                    searchButton.setText("\u67e5\u8be2");
                    searchButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
                    searchButton.addActionListener(e -> searchButtonActionPerformed(e));
                    buttonPanel.add(searchButton);

                    //---- searchAllButton ----
                    searchAllButton.setText("\u67e5\u8be2\u5168\u90e8");
                    searchAllButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
                    searchAllButton.addActionListener(e -> searchAllButtonActionPerformed(e));
                    buttonPanel.add(searchAllButton);

                    //---- deleteButton ----
                    deleteButton.setText("\u5220\u9664\u9009\u4e2d\u884c");
                    deleteButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
                    deleteButton.addActionListener(e -> deleteButtonActionPerformed(e));
                    buttonPanel.add(deleteButton);

                    //---- updateButton ----
                    updateButton.setText("\u66f4\u65b0\u9009\u4e2d\u884c");
                    updateButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
                    updateButton.addActionListener(e -> updateButtonActionPerformed(e));
                    buttonPanel.add(updateButton);

                    //---- insertButton ----
                    insertButton.setText("\u6dfb\u52a0\u9009\u4e2d\u884c");
                    insertButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
                    insertButton.addActionListener(e -> insertButtonActionPerformed(e));
                    buttonPanel.add(insertButton);

                    //---- addNewRow ----
                    addNewRow.setText("\u65b0\u5efa\u884c");
                    addNewRow.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
                    addNewRow.addActionListener(e -> addNewRowActionPerformed(e));
                    buttonPanel.add(addNewRow);
                }
                scrollPanel.setViewportView(buttonPanel);
            }
            upPanel.add(scrollPanel);
        }
        add(upPanel, BorderLayout.NORTH);

        //======== downPanel ========
        {

            //---- resultTable ----
            resultTable.setRowHeight(30);
            resultTable.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 30));
            downPanel.setViewportView(resultTable);
        }
        add(downPanel, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void setTable () // Customize table
    {
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableCellRenderer CR = new DefaultTableCellRenderer();
        CR.setHorizontalAlignment(JLabel.CENTER);
        resultTable.setDefaultRenderer(Object.class, CR);
        DTM = (DefaultTableModel)resultTable.getModel();
        DTM.addColumn("ID");
        DTM.addColumn("Name");
        DTM.addColumn("Grade");
        resultTable.getTableHeader().setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel upPanel;
    private JPanel topPanel;
    private JLabel nameLabel;
    private JTextField nameBox;
    private JScrollPane scrollPanel;
    private JPanel buttonPanel;
    private JButton searchButton;
    private JButton searchAllButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton insertButton;
    private JButton addNewRow;
    private JScrollPane downPanel;
    private JTable resultTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
