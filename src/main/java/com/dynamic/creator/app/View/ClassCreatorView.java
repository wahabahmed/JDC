package com.dynamic.creator.app.View;


import com.dynamic.creator.app.Utils.ImagePanel;
import com.dynamic.creator.app.Utils.JGradientButton;
import com.dynamic.creator.app.Utils.MatchableComboBoxDocument;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * Created by wahaba on 25/05/2018.
 */
public class ClassCreatorView {
    private JFrame mainFrame;
    private JTextField classNameTextField, mainPackageNameTextField, newPackageNameTextField, versionTextField, authorTextField;
    private String[] inputTypes = {
            "byte",
            "short",
            "int",
            "long",
            "float",
            "double",
            "String",
            "boolean",
            "char",
            "Date",
            "List<String>",
            "List<Integer>",
            "List<Date>"};
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
        mainFrame.setTitle("JDC - MVC Package and Classes Creator");
        mainFrame.setSize(mainFrameSize);
        mainFrame.setExtendedState(mainFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("bg3.jpg"));
        JPanel mainPanel = new ImagePanel(imageIcon.getImage());
        mainPanel.setLayout(new GridLayout(2, 2));
        mainPanel.setPreferredSize(mainFrameSize);

        JPanel inputFieldsPanel = new JPanel();
        inputFieldsPanel.setPreferredSize(gridPanelSize);
        inputFieldsPanel.setOpaque(false);
        createHeading("Step1. Fill Out the Project and Class Details", inputFieldsPanel);
        JPanel fieldSubPanel = new JPanel(new GridLayout(5, 3));
        fieldSubPanel.setPreferredSize(new Dimension((int) mainFrameSize.getWidth() / 2 - 50, (int) inputFieldsPanel.getPreferredSize().getHeight() - 100));
        fieldSubPanel.setOpaque(false);

        mainPackageNameTextField = new JTextField();
        newPackageNameTextField = new JTextField();
        classNameTextField = new JTextField();
        authorTextField = new JTextField();
        versionTextField = new JTextField();
        createInputFields("Main Package Name *", fieldSubPanel, mainPackageNameTextField, "Add projects main package name like com.company.app");
        createInputFields("New Package Name *", fieldSubPanel, newPackageNameTextField, "Add name that will be used to create new package for current classes");
        createInputFields("Class Name *", fieldSubPanel, classNameTextField, "Give a name that will be used to create all classes");
        createInputFields("author *", fieldSubPanel, authorTextField, "Name of the coder like wahab ahmed etc..");
        createInputFields("version *", fieldSubPanel, versionTextField, "Example : v1.0 , 1.0 etc..");
        inputFieldsPanel.add(fieldSubPanel);

        /***
         * Set layout for Input Fields, Names and Types
         */
        JPanel inputFieldsTypesPanel = new JPanel();
        inputFieldsTypesPanel.setPreferredSize(gridPanelSize);
        inputFieldsTypesPanel.setOpaque(false);
        table = new JTable();
        table.setOpaque(false);
        table.setRowHeight(30);
        table.setGridColor(Color.LIGHT_GRAY);
        DefaultTableModel model = new DefaultTableModel(100, 2);
        model.setColumnIdentifiers(new Object[]{
                "Variable Name", "Type"});
        table.setModel(model);

        TableColumn col = table.getColumnModel().getColumn(0);
        DefaultCellEditor singleclick = new DefaultCellEditor(new JTextField());
        singleclick.setClickCountToStart(1);
        col.setCellEditor(singleclick);

        //Add Combo Box to the Table Type Column
        TableColumn sportColumn = table.getColumnModel().getColumn(1);
        JComboBox comboBox = new JComboBox();
        comboBox.setOpaque(false);
        for (String inputType : inputTypes) {
            comboBox.addItem(inputType);
        }

        comboBox.setEditable(true);
        JTextComponent editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
        editor.setDocument(new MatchableComboBoxDocument(comboBox));

        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
        createHeading("Step2. Select Class Variables and Their types", inputFieldsTypesPanel);
        JScrollPane scrollPane = new JScrollPane(table);
        inputFieldsTypesPanel.add(scrollPane);

        /***
         * File Chooser Panel
         */
        JPanel fileChooserPanel = new JPanel(new RelativeLayout(RelativeLayout.Y_AXIS, 5));
        createHeading("Step3. Select Project Directory", fileChooserPanel);
        fileChooserPanel.setPreferredSize(gridPanelSize);
        fileChooserPanel.setOpaque(false);

        pathButton = new JGradientButton("Select Project Main Directory");
        pathButton.setPreferredSize(new Dimension(200, 100));
        directoryPathLabel = new JLabel("Select Directory where src folder exists in your project. Default Java Directory = src/main/java");
        fileChooserPanel.add(pathButton, new Float(3));
        fileChooserPanel.add(directoryPathLabel, new Float(1));

        /***
         * Final Actions and Check Fox Panel
         */
        JPanel actionButtonsPanel = new JPanel(new GridLayout(2, 0));
        actionButtonsPanel.setPreferredSize(gridPanelSize);
        actionButtonsPanel.setOpaque(false);

        JPanel checkBoxesPanel = new JPanel();
        checkBoxesPanel.setOpaque(false);
        createHeading("Step4. Select what would you like to create", checkBoxesPanel);
        modelCheckbox = new JCheckBox("Model");
        setupCheckBox(modelCheckbox, checkBoxesPanel);
        controllersCheckbox = new JCheckBox("Controllers");
        setupCheckBox(controllersCheckbox, checkBoxesPanel);
        repoCheckbox = new JCheckBox("Repo");
        setupCheckBox(repoCheckbox, checkBoxesPanel);
        viewCheckbox = new JCheckBox("View");
        setupCheckBox(viewCheckbox, checkBoxesPanel);

        JPanel createButtonPanel = new JPanel();
        createButton = new JGradientButton("Create All ...!");
        createButton.setPreferredSize(new Dimension(200, 200));
        createButton.setBackground(new Color(146, 221, 253));
        createButtonPanel.add(createButton);
        createButtonPanel.setOpaque(false);

        actionButtonsPanel.add(checkBoxesPanel);
        actionButtonsPanel.add(createButtonPanel);


        mainPanel.add(inputFieldsPanel);
        mainPanel.add(inputFieldsTypesPanel);
        mainPanel.add(fileChooserPanel);
        mainPanel.add(actionButtonsPanel);
        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void createInputFields(String fieldName, JPanel panel, JTextField textFieldToAdd, String tooltipText) {
        JLabel label = new JLabel(fieldName);
        label.setPreferredSize(new Dimension(80, 50));
        label.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 15));
        textFieldToAdd.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20), textFieldToAdd.getBorder()));
        textFieldToAdd.setOpaque(false);
        textFieldToAdd.setToolTipText(tooltipText);
        panel.add(label);
        panel.add(textFieldToAdd);
        panel.add(new JLabel());
    }

    private void setupCheckBox(JCheckBox checkBox, JPanel panel) {
        checkBox.setFont(new Font(modelCheckbox.getFont().getName(), modelCheckbox.getFont().getStyle(), 22));
        checkBox.setOpaque(false);
        panel.add(checkBox);
    }

    private void createHeading(String title, JPanel panel) {
        JLabel heading = new JLabel(title);
        heading.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        heading.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        heading.setForeground(new Color(4, 213, 249));
        heading.setPreferredSize(new Dimension((int) mainFrame.getSize().getWidth() / 2, 70));
        panel.add(heading);
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
