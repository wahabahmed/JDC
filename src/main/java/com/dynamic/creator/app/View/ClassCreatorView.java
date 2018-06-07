package com.dynamic.creator.app.View;


import com.dynamic.creator.app.Utils.ImagePanel;
import com.dynamic.creator.app.Utils.MatchableComboBoxDocument;

import javax.swing.*;
import javax.swing.border.Border;
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
        mainFrame.setSize(mainFrameSize);
        mainFrame.setExtendedState(mainFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("bg1.jpg"));
        JPanel mainPanel = new ImagePanel(imageIcon.getImage());//new JPanel(new GridLayout(2, 2));
        mainPanel.setLayout(new GridLayout(2, 2));
        mainPanel.setPreferredSize(mainFrameSize);

        JPanel inputFieldsPanel = new JPanel(new GridLayout(5, 2));
        inputFieldsPanel.setPreferredSize(gridPanelSize);
        inputFieldsPanel.setBackground(Color.yellow);
        inputFieldsPanel.setOpaque(false);

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
        //inputFieldsTypesPanel.setBackground(Color.red);
        inputFieldsTypesPanel.setOpaque(false);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        table = new JTable();
        table.setOpaque(false);
        //table.setShowGrid(false);
        table.setRowHeight(30);
        table.setGridColor(Color.LIGHT_GRAY);//setBorder(BorderFactory.createLineBorder(, 2));
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
        inputFieldsTypesPanel.add(new JScrollPane(table));

        /***
         * File Chooser Panel
         */
        JPanel fileChooserPanel = new JPanel(new RelativeLayout(RelativeLayout.Y_AXIS, 5));
        fileChooserPanel.setPreferredSize(gridPanelSize);
        fileChooserPanel.setOpaque(false);

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
        actionButtonsPanel.setOpaque(false);

        JPanel checkBoxesPanel = new JPanel();
        checkBoxesPanel.setOpaque(false);
        modelCheckbox = new JCheckBox("Model");
        modelCheckbox.setFont(new Font(modelCheckbox.getFont().getName(),modelCheckbox.getFont().getStyle(),22));
        modelCheckbox.setOpaque(false);
        controllersCheckbox = new JCheckBox("Controllers");
        controllersCheckbox.setFont(new Font(modelCheckbox.getFont().getName(),modelCheckbox.getFont().getStyle(),22));
        controllersCheckbox.setOpaque(false);
        repoCheckbox = new JCheckBox("Repo");
        repoCheckbox.setFont(new Font(modelCheckbox.getFont().getName(),modelCheckbox.getFont().getStyle(),22));
        repoCheckbox.setOpaque(false);
        viewCheckbox = new JCheckBox("View");
        viewCheckbox.setFont(new Font(modelCheckbox.getFont().getName(),modelCheckbox.getFont().getStyle(),22));
        viewCheckbox.setOpaque(false);
        checkBoxesPanel.add(modelCheckbox);
        checkBoxesPanel.add(controllersCheckbox);
        checkBoxesPanel.add(repoCheckbox);
        checkBoxesPanel.add(viewCheckbox);

        JPanel createButtonPanel = new JPanel();
        createButton = new JButton("Create All ...!");
        createButtonPanel.add(createButton);
        createButtonPanel.setOpaque(false);

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
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 20));
        Border line = BorderFactory.createLineBorder(Color.DARK_GRAY);
        textFieldToAdd.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20), textFieldToAdd.getBorder()));
        textFieldToAdd.setOpaque(false);
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
