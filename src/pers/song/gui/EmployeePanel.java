/*
 * Created by JFormDesigner on Wed Dec 09 17:49:53 CST 2020
 */

package pers.song.gui;

import pers.song.dao.DBController;
import pers.song.entity.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

/**
 * This panel can let you manage employees, and you need to add this panel to another panel or frame
 * @author Nathan Song
 * @version 2020-12-11
 */

public class EmployeePanel extends JPanel
{
    private DBController DBC;
    private Window owner;
    private DefaultTableModel DTM;

    public EmployeePanel (DBController DBC, Window owner)
    {
        this.DBC = DBC;
        this.owner = owner;
        initComponents();
        setTable();
    }

    private void searchButtonActionPerformed (ActionEvent e)
    {
        DTM.setRowCount(0);
        Employee employee = new Employee();
        if (!nameBox.getText().equals(""))
        {
            employee = DBC.SearchEmployee(nameBox.getText());
            Object [] result = {employee.getID(), employee.getName(), employee.getSex(), employee.getBirthday(), employee.getDept(), employee.getPosition()};
            DTM.addRow(result);
        }
        else
        {
            if(!WarningFrame.WFExisting)
            {
                WarningFrame WF = new WarningFrame(owner,"提示", "请输入姓名！");
            }
        }
    }

    private void searchAllButtonActionPerformed (ActionEvent e)
    {
        DTM.setRowCount(0);
        LinkedList<Employee> LLE = new LinkedList<Employee>();
        LLE = DBC.SearchEmployeeAll();
        for (int i = 1; i <= LLE.size(); i++)
        {
            Object [] result = {LLE.get(i-1).getID(), LLE.get(i-1).getName(), LLE.get(i-1).getSex(), LLE.get(i-1).getBirthday(), LLE.get(i-1).getDept(), LLE.get(i-1).getPosition()};
            DTM.addRow(result);
        }
    }

    private void deleteButtonActionPerformed (ActionEvent e)
    {
        if (resultTable.getSelectedRow() != -1)
        {
            String deleteName= resultTable.getValueAt(resultTable.getSelectedRow(), 1).toString();
            DBC.DeleteEmployee(deleteName);
            DTM.setRowCount(0);
            LinkedList<Employee> LLE = new LinkedList<Employee>();
            LLE = DBC.SearchEmployeeAll();
            for (int i = 1; i <= LLE.size(); i++)
            {
                Object [] result = {LLE.get(i-1).getID(), LLE.get(i-1).getName(), LLE.get(i-1).getSex(), LLE.get(i-1).getBirthday(), LLE.get(i-1).getDept(), LLE.get(i-1).getPosition()};
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
                Employee employee = new Employee();
                employee.setID(resultTable.getValueAt(resultTable.getSelectedRow(), 0).toString());
                employee.setName(resultTable.getValueAt(resultTable.getSelectedRow(), 1).toString());
                employee.setSex(resultTable.getValueAt(resultTable.getSelectedRow(), 2).toString());
                employee.setBirthday(resultTable.getValueAt(resultTable.getSelectedRow(), 3).toString());
                employee.setDept(resultTable.getValueAt(resultTable.getSelectedRow(), 4).toString());
                employee.setPosition(resultTable.getValueAt(resultTable.getSelectedRow(), 5).toString());
                if (employee.getID().equals(""))
                {
                    if (!WarningFrame.WFExisting)
                    {
                        WarningFrame WF = new WarningFrame(owner, "提示", "主键不能为空！");
                    }
                }
                else
                {
                    DBC.InsertEmployee(employee);
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
                Employee employee = new Employee();
                employee.setID(resultTable.getValueAt(resultTable.getSelectedRow(), 0).toString());
                employee.setName(resultTable.getValueAt(resultTable.getSelectedRow(), 1).toString());
                employee.setSex(resultTable.getValueAt(resultTable.getSelectedRow(), 2).toString());
                employee.setBirthday(resultTable.getValueAt(resultTable.getSelectedRow(), 3).toString());
                employee.setDept(resultTable.getValueAt(resultTable.getSelectedRow(), 4).toString());
                employee.setPosition(resultTable.getValueAt(resultTable.getSelectedRow(), 5).toString());
                if (employee.getID().equals(""))
                {
                    if (!WarningFrame.WFExisting)
                    {
                        WarningFrame WF = new WarningFrame(owner, "提示", "主键不能为空！");
                    }
                }
                else
                {
                    DBC.UpdateEmployee(employee);
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
        nameBox = new JTextField();
        nameLabel = new JLabel();
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
            upPanel.setLayout(new GridLayout(2, 0));

            //======== topPanel ========
            {
                topPanel.setLayout(new BorderLayout(50, 0));

                //---- nameBox ----
                nameBox.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
                topPanel.add(nameBox, BorderLayout.CENTER);

                //---- nameLabel ----
                nameLabel.setText("\u59d3\u540d");
                nameLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
                topPanel.add(nameLabel, BorderLayout.WEST);
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
                    insertButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
                    insertButton.setText("\u6dfb\u52a0\u9009\u4e2d\u884c");
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
            resultTable.setRowHeight(35);
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
        DTM.addColumn("Sex");
        DTM.addColumn("Birthday");
        DTM.addColumn("Dept");
        DTM.addColumn("Position");
        resultTable.getTableHeader().setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel upPanel;
    private JPanel topPanel;
    private JTextField nameBox;
    private JLabel nameLabel;
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
