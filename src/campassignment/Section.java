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

    private int ID;
    private String name = "Default Section";
    private ArrayList<Camper> members;
    private Camper leader = null;
    private int maxSize = 0;


    public Section(int ID){
        this.ID = ID;
        members = new ArrayList<Camper>();
    }

    public Section(int ID,int maxSize){
        this.ID = ID;
        this.maxSize = maxSize;
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

    public int freePlace(){
        return maxSize - members.size();
    }
    

    public void addMember(Camper c){
        members.add(c);
    }
    public void addMembers(ArrayList<Camper> members){
        for (Camper camper : members) {
            this.members.add(camper);
        }
    }

    public static int sumDifference(Section a, Section b){
        int sum = 0;
        for (int i = 0; i < Math.max(a.getMemebers().size(), b.getMemebers().size())/2; i++) {
            sum = sum + Camper.getDifference(a.getMemebers().get(i), b.getMemebers().get(i));
            sum = sum + Camper.getDifference(a.getMemebers().get(a.getMemebers().size() - i-1),
                            b.getMemebers().get(b.getMemebers().size() - i-1));
        }
        return sum;
    }

    public int largeOfSection(){
        return members.size();
    }

    @Override
    public String toString(){
        String returnString = "List of members section " + this.name;
        returnString = returnString + "\n Leader is: ";
        int sum = 0;
        for (Camper camper : members) {
            sum = sum + camper.getAge();
        }
        if(leader == null) {returnString = returnString + "empty";}
        else {returnString = returnString +  leader.toString();}
        for(Camper c : members){
            returnString = returnString + "\n" + c.toString();
        }
        returnString = returnString + "\n Sum age of members: " + sum;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }




    

    public Section hardCopy(){
        Section tmp = new Section(this.ID);
        for (Camper camper : members) {
            tmp.addMember(camper.hadrCopy());
        }
        tmp.setName(this.name);
        if(leader != null) tmp.setLeader(leader.hadrCopy());
        return tmp;
    }



   public boolean isTogetherOK(){
       for (Camper camper : members) {
           for (Camper camper1 : camper.getCanBeWith()) {
               if(camper1.getSectionID() != this.getID()) return false;
           }
       }
       return true;
   }


    public ArrayList<Camper> canNotBeAMember(){
        ArrayList<Camper> canNot = new ArrayList<Camper>();
        for (Camper camper : members) {
            canNot.addAll(camper.getCanNotBeWith());
        }
        return canNot;
    }

    public ArrayList<Camper> mustBeAMember(){
        ArrayList<Camper> mustBe = new ArrayList<Camper>();
        for (Camper camper : members) {
            mustBe.addAll(camper.getCanBeWith());
        }
        return mustBe;
    }

    public Camper getGoldenRatio(){
        int size = members.size();
        int camperPosition = (int) (size / goldenRatio(5));
        return members.get(camperPosition);
    }

    private double goldenRatio(int n) {
       if (n == 0) return 1;
       return 1.0 + 1.0 / goldenRatio(n-1);
    }



}
