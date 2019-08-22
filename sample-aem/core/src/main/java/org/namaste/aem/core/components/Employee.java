package org.namaste.aem.core.components;

//Stores employee information
public class Employee {

    //Define private class members
    private String name;
    private String address;
    private String position;
    private String age;
    private String date;
    private String salary;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }


    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return this.age;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSalary() {
        return this.salary;
    }

}
