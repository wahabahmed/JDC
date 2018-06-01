package com.dynamic.creator.app.View;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * Created by wahaba on 25/05/2018.
 */
public class ClassCreatorView {
    private JFrame mainFrame;
    private JTextField classNameTextField, mainPackageNameTextField, newPackageNameTextField, versionTextField, authorTextField;
    private String[] inputTypes = {"byte", "short", "int", "long", "float", "double", "String", "boolean", "char", "Date"};
    private JCheckBox modelCheckbox, controllersCheckbox, repoCheckbox, viewCheckbox;
    private JButton createButton, pathButton;
    private JTable table;
    private JFileChooser chooser;
    private JLabel directoryPathLabel;

    public ClassCreatorView() {
        init();
    }

    public void init() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension mainFrameSize = dim;//new Dimension(800, 600);
        Dimension gridPanelSize = new Dimension((int) mainFrameSize.getWidth() / 2, (int) mainFrameSize.getHeight() / 2);

        mainFrame = new JFrame();
        mainFrame.setSize(mainFrameSize);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(2, 2));
        mainPanel.setPreferredSize(mainFrameSize);

        JPanel inputFieldsPanel = new JPanel(new GridLayout(5, 2));
        inputFieldsPanel.setPreferredSize(gridPanelSize);
        inputFieldsPanel.setBackground(Color.yellow);

        mainPackageNameTextField = new JTextField();
        newPackageNameTextField = new JTextField();
        classNameTextField = new JTextField();
        authorTextField = new JTextField();
        versionTextField = new JTextField();
        createInputFields("Main Package Name", inputFieldsPanel, mainPackageNameTextField);
        createInputFields("New Package Name", inputFieldsPanel, newPackageNameTextField);
        createInputFields("Class Name", inputFieldsPanel, classNameTextField);
        createInputFields("author", inputFieldsPanel, authorTextField);
        createInputFields("version", inputFieldsPanel, versionTextField);

        /***
         * Set layout for Input Fields, Names and Types
         */
        JPanel inputFieldsTypesPanel = new JPanel(new GridLayout(0, 1));
        inputFieldsTypesPanel.setPreferredSize(gridPanelSize);
        inputFieldsTypesPanel.setBackground(Color.red);
        JScrollPane scrollPane = new JScrollPane();
        table = new JTable();
        table.setRowHeight(30);
        DefaultTableModel model = new DefaultTableModel(100, 2);
        model.setColumnIdentifiers(new Object[]{
                "Variable Name", "Type"});
        table.setModel(model);

        //Add Combo Box to the Table Type Column
        TableColumn sportColumn = table.getColumnModel().getColumn(1);
        JComboBox comboBox = new JComboBox();
        for (String inputType : inputTypes) {
            comboBox.addItem(inputType);
        }

        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
        inputFieldsTypesPanel.add(new JScrollPane(table));

        /***
         * File Chooser Panel
         */
        JPanel fileChooserPanel = new JPanel(new RelativeLayout(RelativeLayout.Y_AXIS, 5));
        fileChooserPanel.setPreferredSize(gridPanelSize);

        pathButton = new JButton("Select Project Main Directory");
        pathButton.setPreferredSize(new Dimension(200, 100));
        directoryPathLabel = new JLabel("Select Directory where you would like to create package/code/classes");

        fileChooserPanel.setBackground(Color.green);
        fileChooserPanel.add(Box.createGlue(), new Float(1));
        fileChooserPanel.add(pathButton, new Float(3));
        fileChooserPanel.add(pathButton, new Float(3));
        fileChooserPanel.add(directoryPathLabel, new Float(1));

        /***
         * Final Actions and Check Fox Panel
         */
        JPanel actionButtonsPanel = new JPanel(new GridLayout(2, 0));
        actionButtonsPanel.setPreferredSize(gridPanelSize);
        actionButtonsPanel.setBackground(Color.blue);

        JPanel checkBoxesPanel = new JPanel();
        modelCheckbox = new JCheckBox("Model");
        controllersCheckbox = new JCheckBox("Controllers");
        repoCheckbox = new JCheckBox("Repo");
        viewCheckbox = new JCheckBox("View");
        checkBoxesPanel.add(modelCheckbox);
        checkBoxesPanel.add(controllersCheckbox);
        checkBoxesPanel.add(repoCheckbox);
        checkBoxesPanel.add(viewCheckbox);

        JPanel createButtonPanel = new JPanel();
        createButton = new JButton("Create All ...!");
        createButtonPanel.add(createButton);

        actionButtonsPanel.add(checkBoxesPanel);
        actionButtonsPanel.add(createButtonPanel);


        mainPanel.add(inputFieldsPanel);
        mainPanel.add(inputFieldsTypesPanel);
        mainPanel.add(fileChooserPanel);
        mainPanel.add(actionButtonsPanel);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    private void createInputFields(String fieldName, JPanel panel, JTextField textFieldToAdd) {
        JLabel label = new JLabel(fieldName);
        label.setPreferredSize(new Dimension(80, 50));
        label.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        panel.add(label);
        panel.add(textFieldToAdd);
    }

    public static void main(String[] args) {
        new ClassCreatorView();
    }


    public JTextField getClassNameTextField() {
        return classNameTextField;
    }

    public JTextField getMainPackageNameTextField() {
        return mainPackageNameTextField;
    }

    public JTextField getNewPackageNameTextField() {
        return newPackageNameTextField;
    }

    public JTextField getVersionTextField() {
        return versionTextField;
    }

    public JTextField getAuthorTextField() {
        return authorTextField;
    }


    public JCheckBox getModelCheckbox() {
        return modelCheckbox;
    }

    public JCheckBox getControllersCheckbox() {
        return controllersCheckbox;
    }

    public JCheckBox getRepoCheckbox() {
        return repoCheckbox;
    }

    public JCheckBox getViewCheckbox() {
        return viewCheckbox;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getPathButton() {
        return pathButton;
    }

    public void showJDialog() {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select Project Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(new JPanel()) == JFileChooser.APPROVE_OPTION) {
            setFileChooserLabel(chooser.getSelectedFile().getPath());
        } else {
            System.out.println("No Selection ");
        }
    }

    public JFileChooser getChooser() {
        return chooser;
    }

    public void setFileChooserLabel(String path) {
        directoryPathLabel.setText(path);
    }
}
