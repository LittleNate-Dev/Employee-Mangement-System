/*
 * Created by JFormDesigner on Wed Dec 09 18:24:52 CST 2020
 */

package pers.song.gui;

import pers.song.dao.DBController;
import pers.song.entity.Department;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

/**
 * This panel can let you manage department, and you need to add this panel to another panel or frame
 * @author Nathan Song
 * @version 2020-12-11
 */

public class DepartmentPanel extends JPanel
{
    private DBController DBC;
    private Window owner;
    private DefaultTableModel DTM;

    public DepartmentPanel (DBController DBC, Window owner)
    {
        this.DBC = DBC;
        this.owner = owner;
        initComponents();
        setTable();
    }

    private void searchButtonActionPerformed (ActionEvent e)
    {
        DTM.setRowCount(0);
        Department department = new Department();
        if (!deptBox.getText().equals(""))
        {
            department = DBC.SearchDepartment(deptBox.getText());
            Object [] result = {department.getID(), department.getName()};
            DTM.addRow(result);
        }
        else
        {
            if(!WarningFrame.WFExisting)
            {
                WarningFrame WF = new WarningFrame(owner,"提示", "请输入部门名称！");
            }
        }
    }

    private void searchAllButtonActionPerformed (ActionEvent e)
    {
        DTM.setRowCount(0);
        LinkedList<Department> LLD = new LinkedList<Department>();
        LLD = DBC.SearchDepartmentAll();
        for (int i = 1; i <= LLD.size(); i++)
        {
            Object [] result = {LLD.get(i-1).getID(), LLD.get(i-1).getName()};
            DTM.addRow(result);
        }
    }

    private void addNewRowActionPerformed (ActionEvent e)
    {
        DTM.addRow(new Object[0]);
    }

    private void insertButtonActionPerformed (ActionEvent e)
    {
        if (resultTable.getSelectedRow() != -1)
        {
            try
            {
                Department department = new Department();
                department.setID(resultTable.getValueAt(resultTable.getSelectedRow(), 0).toString());
                department.setName(resultTable.getValueAt(resultTable.getSelectedRow(), 1).toString());
                if (department.getID().equals(""))
                {
                    if (!WarningFrame.WFExisting)
                    {
                        WarningFrame WF = new WarningFrame(owner, "提示", "主键不能为空！");
                    }
                }
                else
                {
                    DBC.InsertDepartment(department);
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

    private void deleteButtonActionPerformed (ActionEvent e)
    {
        if (resultTable.getSelectedRow() != -1)
        {
            String deleteName= resultTable.getValueAt(resultTable.getSelectedRow(), 1).toString();
            DBC.DeleteDeaprtment(deleteName);
            DTM.setRowCount(0);
            LinkedList<Department> LLD = new LinkedList<Department>();
            LLD = DBC.SearchDepartmentAll();
            for (int i = 1; i <= LLD.size(); i++)
            {
                Object [] result = {LLD.get(i-1).getID(), LLD.get(i-1).getName()};
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

    private void updateButtonActionPerformed (ActionEvent e)
    {
        if (resultTable.getSelectedRow() != -1)
        {
            try
            {
                Department department = new Department();
                department.setID(resultTable.getValueAt(resultTable.getSelectedRow(), 0).toString());
                department.setName(resultTable.getValueAt(resultTable.getSelectedRow(), 1).toString());
                if (department.getID().equals(""))
                {
                    if (!WarningFrame.WFExisting)
                    {
                        WarningFrame WF = new WarningFrame(owner, "提示", "主键不能为空！");
                    }
                }
                else
                {
                    DBC.UpdateDepartment(department);
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
        deptLabel = new JLabel();
        deptBox = new JTextField();
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
                topPanel.setLayout(new BorderLayout(50, 0));

                //---- deptLabel ----
                deptLabel.setText("\u90e8\u95e8\u540d");
                deptLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
                topPanel.add(deptLabel, BorderLayout.WEST);

                //---- deptBox ----
                deptBox.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
                topPanel.add(deptBox, BorderLayout.CENTER);
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
            resultTable.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 30));
            resultTable.setRowHeight(30);
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
        resultTable.getTableHeader().setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel upPanel;
    private JPanel topPanel;
    private JLabel deptLabel;
    private JTextField deptBox;
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
