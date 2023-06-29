package com.example.tugasbesar;

public class User {
    private String name;
    private double salary;
    private int id;

    public User (String name, double salary, int id) {
        this.name = name;
        this.salary = salary;
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public double getSalary () {
        return salary;
    }

    public void setSalary (double salary) {
        this.salary = salary;
    }

    public int getID () {
        return id;
    }

    public void setID (int id) {
        this.id = id;
    }
}