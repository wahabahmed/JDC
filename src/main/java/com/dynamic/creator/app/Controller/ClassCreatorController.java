package com.dynamic.creator.app.Controller;

import com.dynamic.creator.app.Model.ClassCreator;
import com.dynamic.creator.app.Model.ClassOptions;
import com.dynamic.creator.app.Services.ClassCreatorService;
import com.dynamic.creator.app.View.ClassCreatorView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

@Controller
public class ClassCreatorController implements MouseListener {

    @Autowired
    private ClassCreatorService ClassCreatorService;
    private ClassCreatorView view;


    @PostConstruct
    public void createViewAndShow() {
        generateClass();
    }

    private void generateClass() {
        view = new ClassCreatorView();
        view.getCreateButton().addMouseListener(this);
        view.getPathButton().addMouseListener(this);
        view.getExitButton().addMouseListener(this);
    }

    private void prepareObjectsAndPassToService() {
        HashMap<String, String> variablesToPass = new HashMap<String, String>();
        JTable table = view.getTable();
        int totalRows = table.getRowCount();
        String finalNamesForOutput = "";

        for (int i = 0; i < totalRows; i++) {
            String variableName = "";
            String variableType = "";
            if (table.getValueAt(i, 0) != null && table.getValueAt(i, 1) != null) {
                variableName = table.getValueAt(i, 0).toString();
                variableType = table.getValueAt(i, 1).toString();

                if (!variableName.isEmpty() || !variableType.isEmpty()) {
                    variablesToPass.put(variableName, variableType);
                    finalNamesForOutput += variableName + " : " + variableType + "\n";
                }
            }
        }

        JOptionPane.showMessageDialog(null, finalNamesForOutput);

        ClassCreator classCreator = new ClassCreator(
                view.getMainPackageNameTextField().getText(),
                view.getNewPackageNameTextField().getText(),
                view.getAuthorTextField().getText(),
                view.getVersionTextField().getText(),
                view.getClassNameTextField().getText(),
                variablesToPass,
                view.getChooser().getSelectedFile().getPath()
        );

        ClassOptions classOptions = new ClassOptions(
                view.getModelCheckbox().isSelected(),
                view.getControllersCheckbox().isSelected(),
                view.getRepoCheckbox().isSelected(),
                view.getViewCheckbox().isSelected()
        );

        ClassCreatorService.createClass(classCreator, classOptions);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource().equals(view.getCreateButton())) {
            prepareObjectsAndPassToService();
        } else if (e.getSource().equals(view.getPathButton())) {
            view.showJDialog();
        }else if (e.getSource().equals(view.getExitButton())) {
            System.exit(1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
