/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package campassignment;

import java.util.ArrayList;

/**
 *
 * @author tocas
 */
public class Camper implements Comparable<Camper>{
    private int ID;
    private String name;
    private int gendar;
    private int age;
    private ArrayList<Camper> canBeWith;
    private ArrayList<Camper> canNotBeWith;
    private int sectionID;


    public Camper(int ID, String name, int age,int gendar){
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.canBeWith = new ArrayList<Camper>();
        this.canNotBeWith = new ArrayList<Camper>();
        sectionID = -1;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return ID+" "+name+" "+age + " " + gendar + " Section: " + sectionID;
    }

    public int compareTo(Camper c){
        return this.age - c.getAge();
    }

    public static int getDifference(Camper a, Camper b){
       if(a == null) return 0;
       return Math.abs(a.age - b.age);
    }

    public Camper hadrCopy(){
        return new Camper(this.ID,this.name,this.age,this.gendar);
    }

    public ArrayList<Camper> getCanBeWith() {
        return canBeWith;
    }

    public void setCanBeWith(Camper camper) {
        this.canBeWith.add(camper);
    }

    public ArrayList<Camper> getCanNotBeWith() {
        return canNotBeWith;
    }

    public void setCanNotBeWith(Camper camper) {
        this.canNotBeWith.add(camper);
    }

    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }




}
