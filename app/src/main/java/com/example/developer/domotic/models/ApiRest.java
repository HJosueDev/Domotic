package com.example.developer.domotic.models;

import com.example.developer.domotic.models.Actions;

import java.util.List;

/**
 * Created by Developer on 26/11/2017.
 */

public class ApiRest {

    private int id;
    private int status;
    private String name;
    //private List<Actions> actions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*
    public List<Actions> getActions() {
        return actions;
    }

    public void setActions(List<Actions> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "ApiRest{" +
                "id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", actions=" + actions +
                '}';
    }
*/


    @Override
    public String toString() {
        return "ApiRest{" +
                "id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                '}';
    }
}
