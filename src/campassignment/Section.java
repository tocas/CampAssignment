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
    private Camper leader = null;

    public Section(){
        members = new ArrayList<Camper>();
    }

    public Section(ArrayList<Camper> members){
        this.members = members;
        Collections.sort(this.members);
    }

    public Section(String name,ArrayList<Camper> members){
        this.name = name;
        this.members = members;
        //Collections.sort(this.members);
    }

    public void sortSection(){
        Collections.sort(this.members);
    }

    public ArrayList<Camper> getMemebers(){
        Collections.sort(members);
        return members;
    }

    public Camper getLeader() {
        return leader;
    }

    public void setLeader(Camper leader) {
        this.leader = leader;
    }
    

    public void addMember(Camper c){
        members.add(c);
    }
    public void addMembers(ArrayList<Camper> members){
        for (Camper camper : members) {
            this.members.add(camper);
        }
    }

    public int maxDifference(){
        int max = 0;
        if(members.isEmpty()) return 0;
        for(int i = 0;i < members.size();i++){
            max = Math.max(max, Camper.getDifference(leader, members.get(i)));
            for(int j = i;j < members.size();j++){
                max = Math.max(max, Camper.getDifference(members.get(i),members.get(j)));
            }
        }
        return max;
    }

    public int largeOfSection(){
        return members.size();
    }

    @Override
    public String toString(){
        String returnString = "List of members section " + this.name;
        returnString = returnString + "\n Leader is: ";
        if(leader == null) {returnString = returnString + "empty";}
        else {returnString = returnString +  leader.toString();}
        for(Camper c : members){
            returnString = returnString + "\n" + c.toString();
        }
        returnString = returnString +"\n Max difference is: " + maxDifference();
        return returnString;
    }

    public ArrayList<Camper> assignCampers(int count){
        if(count > members.size()) return new ArrayList<Camper>();
        ArrayList<Camper> tmp = new ArrayList<Camper>();
        for(int i = 0; i < count;i++){
            tmp.add(members.remove(0));
        }
        return tmp;
    }

    public Camper getCamperForSwitch(){
        return members.remove(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Section hardCopy(){
        Section tmp = new Section();
        for (Camper camper : members) {
            tmp.addMember(camper.hadrCopy());
        }
        tmp.setName(this.name);
        if(leader != null) tmp.setLeader(leader.hadrCopy());
        return tmp;
    }



}
