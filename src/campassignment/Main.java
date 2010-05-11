/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package campassignment;

/**
 *
 * @author tocas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Camp camp = new Camp(Integer.valueOf(args[1]));
        camp.importData(args[0]);
        camp.setupLeaders();
        camp.setupTogether(args[2]);
        camp.setupSeparate(args[3]);
        camp.assignmentCampers();
        if (camp.isTogetherOK()) {
            System.out.println("Together OK");
        } else {
            System.out.println("Together FAIL");
        }
        if (camp.isSeparateOK()) {
            System.out.println("Separated OK");
        } else {
            System.out.println("Separated FAIL");
        }
        System.out.println(camp.toString());

        //System.out.println(Camp.optimization(camp).toString());

    }
}

