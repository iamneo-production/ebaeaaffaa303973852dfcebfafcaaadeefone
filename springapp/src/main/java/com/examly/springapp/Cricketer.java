package com.examly.springapp;

public class Cricketer implements Comparable<Cricketer> {
    
    private String name;
    private int age;
   private String country;

    public Cricketer(String name, int age, String country) {
        this.name = name;
        this.age = age;
       this.country = country;
    }

    public Cricketer() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int compareTo(Cricketer other) {
        return this.name.compareTo(other.name);
    }
}
