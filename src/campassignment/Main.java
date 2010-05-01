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
        
        Camp camp = new Camp(2);
        camp.importData();
        camp.assignmentCampers();
        System.out.println(camp.toString());
       
    }

}
