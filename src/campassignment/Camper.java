/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package campassignment;

/**
 *
 * @author tocas
 */
public class Camper implements Comparable<Camper>{
    private int ID;
    private String name;
    private int age;

    public Camper(int ID, String name, int age){
        this.ID = ID;
        this.name = name;
        this.age = age;
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
        return ID+" "+name+" "+age;
    }

    public int compareTo(Camper c){
        return this.age - c.getAge();
    }

    public static int getDifference(Camper a, Camper b){
       return Math.abs(a.age - b.age);
    }

    public Camper hadrCopy(){
        return new Camper(this.ID,this.name,this.age);

    }

}
