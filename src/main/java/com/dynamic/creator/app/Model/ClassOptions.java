package com.dynamic.creator.app.Model;

/**
 * Created by wahaba on 30/05/2018.
 */
public class ClassOptions {
    boolean createModel;
    boolean createControllers;
    boolean createRepository;
    boolean createView;

    public ClassOptions(boolean createModel, boolean createControllers, boolean createRepository, boolean createView) {
        this.createModel = createModel;
        this.createControllers = createControllers;
        this.createRepository = createRepository;
        this.createView = createView;
    }

    public boolean isCreateModel() {
        return createModel;
    }

    public void setCreateModel(boolean createModel) {
        this.createModel = createModel;
    }

    public boolean isCreateControllers() {
        return createControllers;
    }

    public void setCreateControllers(boolean createControllers) {
        this.createControllers = createControllers;
    }

    public boolean isCreateRepository() {
        return createRepository;
    }

    public void setCreateRepository(boolean createRepository) {
        this.createRepository = createRepository;
    }

    public boolean isCreateView() {
        return createView;
    }

    public void setCreateView(boolean createView) {
        this.createView = createView;
    }
}
