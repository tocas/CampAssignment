/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package campassignment;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author tocas
 */
public class Section {

    private String name = "Default Section";
    private ArrayList<Camper> members;

    public Section(){
        members = new ArrayList<Camper>();
    }

    public Section(ArrayList<Camper> members){
        this.members = members;
    }

    public Section(String name,ArrayList<Camper> members){
        this.name = name;
        this.members = members;
    }

    public ArrayList<Camper> getMemebers(){
        Collections.sort(members);
        return members;
    }

    public void addMember(Camper c){
        members.add(c);
    }
    public int maxDifference(){
        int max = 0;
        if(members.isEmpty()) return 0;
        for(int i = 0;i < members.size();i++){
            for(int j = i;j < members.size();j++){
                max = Math.max(max, Camper.getDifference(members.get(i),members.get(j)));
            }
        }
        return max;
    }

    public int largeOfSection(){
        return members.size();
    }

    public String toString(){
        String retrunString = "List of members section " + this.name;
        for(Camper c : members){
            retrunString = retrunString + "\n" + c.toString();
        }
        return retrunString;
    }



}
