package com.example.developer.domotic.models;

/**
 * Created by Developer on 26/11/2017.
 */

class Actions {
    private String on;
    private String of;

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }

    public String getOf() {
        return of;
    }

    public void setOf(String of) {
        this.of = of;
    }


    @Override
    public String toString() {
        return "Actions{" +
                "on='" + on + '\'' +
                ", of='" + of + '\'' +
                '}';
    }
}
