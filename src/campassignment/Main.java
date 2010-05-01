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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Camper> members = new ArrayList<Camper>();
        members.add(new Camper(1,"Franta",7));
        members.add(new Camper(2,"Karel",9));
        members.add(new Camper(3,"Dáša",8));
        members.add(new Camper(4,"Ivana",9));
        members.add(new Camper(5,"Jindra",8));

        Section root = new Section("Full members", members);


        ArrayList<Section> sections = new ArrayList<Section>();

        int countOfSections = 2;

        int membersInSection = root.largeOfSection() / countOfSections;

        int numberOverSections = root.largeOfSection() % countOfSections;;

        int normalSection = countOfSections - numberOverSections;

        for(int i = 0; i < normalSection;i++){
            Section tmp = new Section(root.assignCampers(membersInSection));
            sections.add(tmp);
        }

        for(int i = membersInSection;i<membersInSection+numberOverSections;i++){
            Section tmp = new Section(root.assignCampers(membersInSection+1));
            sections.add(tmp);
        }


        //Show Assignment

        for (Section section : sections) {
            System.out.println(section.toString());
        }
    }

}
