/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package campassignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 *
 * @author tocas
 */
public class Camp {

    private ArrayList<Section> sections = new ArrayList<Section>();
    private ArrayList<Camper> leaders = new ArrayList<Camper>();
    private Section root = new Section(0);
    private int countOfSections = 0;
    private Camper[] allCamperID = new Camper[1000];
    private ArrayList<Camper> allTogether = new ArrayList<Camper>();
    private ArrayList<Camper> allSeparate = new ArrayList<Camper>();
    private int countOfOverSections = 0;
    private int sizeOfSection = 0;
    private int sizeOfOverSections = 0;
    private int useOverSections = 0;

    public Camp(int countOfSections) {
        this.countOfSections = countOfSections;
        this.leaders = new ArrayList<Camper>(countOfSections);
        countOfOverSections = root.getMemebers().size()%countOfSections;
        sizeOfSection = root.getMemebers().size()/countOfSections;
        sizeOfOverSections = sizeOfSection +1;
        for(int i = 0; i < countOfSections;i++){
            sections.add(new Section(i));
        }
    }

    private int computeAge(String stringDate) {
        StringTokenizer st =  new StringTokenizer(stringDate, ".");

        int day = Integer.valueOf(st.nextToken());
        int month =  Integer.valueOf(st.nextToken());
        int year = Integer.valueOf(st.nextToken());
        
        Calendar birthCal = new GregorianCalendar(year,month,day);
        Calendar nowCal = new GregorianCalendar();
        int age = nowCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);

        boolean isMonthGreater = birthCal.get(Calendar.MONTH) >= nowCal.get(Calendar.MONTH);
        boolean isMonthSameButDayGreater = birthCal.get(Calendar.MONTH) == nowCal
        .get(Calendar.MONTH)&& birthCal.get(Calendar.DAY_OF_MONTH) > nowCal.get(Calendar.DAY_OF_MONTH);
        if (isMonthGreater || isMonthSameButDayGreater) {
            age = age - 1;
        }
        return age;
    }

    public void importData(String fileName) {
        ArrayList<Camper> members = new ArrayList<Camper>();
        /*
        members.add(new Camper(1,"Franta",7));
        members.add(new Camper(2,"Karel",9));
        members.add(new Camper(3,"Dáša",8));
        members.add(new Camper(4,"Ivana",9));
        members.add(new Camper(5,"Jindra",8));
        members.add(new Camper(6,"Jitka",6));
        members.add(new Camper(7,"Pepa",7));
        members.add(new Camper(8,"Tomas",7));

         */

        int ID = 0;
        String name = "";
        String stringDate = "";
        int gendar = 0;

        try {

            //create BufferedReader to read csv file
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String strLine = "";
            StringTokenizer st = null;
            Camper c;
            int lineNumber = 0, tokenNumber = 0;

            //read comma separated file line by line
            while ((strLine = br.readLine()) != null) {
                lineNumber++;
                if(lineNumber == 1) continue;
                //break comma separated line using ","
                st = new StringTokenizer(strLine, ";");

                while (st.hasMoreTokens()) {
                    //display csv values
                    tokenNumber++;
                    switch (tokenNumber) {
                        case 1:
                            ID = Integer.valueOf(st.nextToken());
                        case 2:
                            name = st.nextToken();
                        case 3:
                            name = name + " " + st.nextToken() + ".";
                        case 4:
                            gendar = Integer.valueOf(st.nextToken());
                        case 5:
                            stringDate = st.nextToken();
                    }
                    c = new Camper(ID, name, computeAge(stringDate), gendar);
                   members.add(c);
                   allCamperID[c.getID()] = c;
                }

                //reset token number
                tokenNumber = 0;

            }


        } catch (Exception e) {
            System.out.println("Exception while reading csv file: " + e);
        }
        root = new Section("Full members", members);
    }

    public void setupLeaders(){
        int i = 0;
        for (Camper camper : root.assignCampers(countOfSections)) {
            leaders.add(camper);
            sections.get(i++).setLeader(camper);
        }
    }

    public void setupTogether(String str){
        StringTokenizer stdd;
        StringTokenizer std = new StringTokenizer(str,";");
        int a,b;
        while (std.hasMoreTokens()){
            stdd = new StringTokenizer(std.nextToken(),":");
            while(stdd.hasMoreTokens()){
                a = Integer.valueOf(stdd.nextToken());
                b = Integer.valueOf(stdd.nextToken());
                allCamperID[a].setCanBeWith(allCamperID[b]);
                allCamperID[b].setCanBeWith(allCamperID[a]);
                allTogether.add(allCamperID[a]);
                allTogether.add(allCamperID[b]);
            }
        }
    }

    public void setupSeparate(String str){
        StringTokenizer stdd;
        StringTokenizer std = new StringTokenizer(str,";");
        while (std.hasMoreTokens()){
            stdd = new StringTokenizer(std.nextToken(),":");
            int a,b;
            while(stdd.hasMoreTokens()){
                a = Integer.valueOf(stdd.nextToken());
                b = Integer.valueOf(stdd.nextToken());
                allCamperID[a].setCanNotBeWith(allCamperID[b]);
                allCamperID[b].setCanNotBeWith(allCamperID[a]);
                allSeparate.add(allCamperID[a]);
                allSeparate.add(allCamperID[b]);
            }
        }
    }


    public void assignmentCampers() {
        root.sortSection();
        if (countOfSections == 0) {
            return;
        }

        int size = root.getMemebers().size()-1;
        int index = 0;
        for (int i =size; i > 0; i--){
            Camper c = root.getMemebers().get(i);
            index = i%countOfSections;
            c.setSectionID(index);
            sections.get(index).addMember(c);
        }
    }


    public boolean assignmentCampersWithOptimum(){
        root.sortSection();
        if (countOfSections == 0) {
            return false;
        }

        //precondition more single campers than duplicate campers
        int index = 0;
        for(int j = 0; j < allTogether.size();j++){
            Camper c = allTogether.get(j);
            root.getMemebers().remove(c);
            if(c.getCanBeWith().size()+1 > maxFreeSection())
            index = j%countOfSections;
            for (Camper camper : c.getCanBeWith()) {
                camper.setSectionID(index);
                root.getMemebers().remove(camper);
                sections.get(index).addMember(camper);
            }
            c.setSectionID(index);
            sections.get(index).addMember(c);
        }
        //fill sections into the same level 
        int maxFullSection = 0;
        for (Section section : sections) {
            maxFullSection = Math.max(maxFullSection, section.largeOfSection());
        }
        int diference = 0;
        int tmpLarge = 0;
        for (int j = 0; j<sections.size(); j++) {
            tmpLarge = sections.get(j).largeOfSection();
            for(int i = 0; i < maxFullSection-tmpLarge;i++){
                // Can I assign camper into section
                Camper camper = root.getMemebers().remove(root.getMemebers().size()-1);
                if(sections.get(j-diference).canNotBeAMember().contains(camper)){
                    camper.setSectionID(j+1);
                    sections.get(j+1).addMember(camper);
                    diference = diference+1;
                } else {
                    sections.get(j-diference).addMember(camper);
                    camper.setSectionID(j-diference);
                    diference = 0;
                }
            }
        }
        //precondition camper cannot belong into more then one section
        int size = root.getMemebers().size()-1;
        index = 0;
        diference = 0;
        for (int i =size; i > 0; i--){
            Camper c = root.getMemebers().get(i);
            index = i%countOfSections;
            if(sections.get(index).canNotBeAMember().contains(c)){
                c.setSectionID((index+1)%countOfSections);
                sections.get(index+1).addMember(c);
                diference = diference + 1;
            }else{
                c.setSectionID(index-diference);
                sections.get(index-diference).addMember(c);
                diference = 0;
            }
        }
        return true;
    }

    private int maxFreeSection(){
        int max = 0;
        for (Section section : sections) {
            max = Math.max(max,section.freePlace());
        }
        return max;
    }



    public int getSumMaxDifference() {
        int maxDifference = 0;
        int localDifference = 0;
        for (Section section : sections) {
            for (Section section1 : sections) {
                if(!section.equals(section1)){
                    localDifference = Section.sumDifference(section, section1);
                    /* System.out.println( section.getLeader().toString()
                                        + " vs. "+
                                        section1.getLeader().toString()
                                        + "\n Ma odchylku " +
                                        localDifference);
                     */
                    maxDifference = maxDifference + localDifference;
                }
            }
        }
        return maxDifference;
    }

    private boolean sameLenght() {
        if (sections.isEmpty()) {
            return true;
        }
        int size = sections.get(0).largeOfSection();
        for (int i = 1; i < sections.size(); i++) {
            if (size != sections.get(i).largeOfSection()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String returnString = "";
        for (Section section : sections) {
            returnString = returnString + "\n" + section.toString();
        }
        returnString = returnString + "\n Sum max difference: " + getSumMaxDifference();
        return returnString;
    }

    public Camp hardCopy() {
        Camp tmp = new Camp(this.countOfSections);
        tmp.countOfOverSections = this.countOfOverSections;
        for (Section section : sections) {
            tmp.sections.add(section.hardCopy());
        }
        return tmp;
    }

    public Camp switchCampers(int from, int to) {
        if (sections.get(from).largeOfSection() < sections.get(to).largeOfSection()) {
            return this;
        }
        sections.get(to).addMember(sections.get(from).getCamperForSwitch());
        return this;
    }

    public Camp shiftOverSectionts(int largeShift) {
        if (countOfOverSections == 0) {
            return this;
        }
        int stopIndex = countOfSections - countOfOverSections;
        for (int i = 0; i < largeShift; i++) {
            for (int j = countOfSections - 1 - i; j < stopIndex - i; j--) {
                this.switchCampers(j, j - 1);
            }
        }
        return this;
    }

    //Precondition optimization was never perform
    public static Camp optimization(Camp camp) {
        if (camp.sameLenght()) {
            return camp;
        }
        ArrayList<Camp> allPosibility = new ArrayList<Camp>();
        allPosibility.add(camp);
        Camp tmp = camp.hardCopy();
        int startIndex = camp.countOfSections - camp.countOfOverSections;

        for (int i = startIndex; i < camp.countOfSections; i++) {
            tmp = camp.hardCopy();
            tmp = tmp.shiftOverSectionts(i - startIndex);
            for (int j = i; j > 0; j--) {
                allPosibility.add(tmp.switchCampers(j, j - 1));
            }
        }
        int cmax = Integer.MAX_VALUE;
        Camp solution = new Camp(camp.countOfOverSections);
        for (Camp camp1 : allPosibility) {
            if (cmax > camp1.getSumMaxDifference()) {
                cmax = camp1.getSumMaxDifference();
                solution = camp1;
            }
        }
        return solution;
    }

    public boolean isTogetherOK(){
        for (Camper camper : allTogether) {
            for (Camper camper1 : camper.getCanBeWith()) {
                if(camper.getSectionID() != camper1.getSectionID()) return false;
            }
        }
        return true;
    }

    public boolean isSeparateOK(){
        for (Camper camper : allSeparate) {
            for (Camper camper1 : camper.getCanNotBeWith()) {
                System.out.println(camper.toString() + " compare to " + camper1.toString());
                if(camper.getSectionID() == camper1.getSectionID()) return false;
            }
        }
        return true;
    }

    


}
