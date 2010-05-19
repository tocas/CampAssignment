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

        Long start, stop;
        //count campers
        for(int i = 50; i < 500; i = i + 50){
            //count Sections
            for(int j = 0; j < i/10; j = j + i/50){
                System.out.println("Camperts: " + i);
                System.out.println("TogetherSeparated: " + j);
                Camp camp = new Camp(i/10);
                camp.autoImport(i);
                camp.autoTogetherSeparate(j, i);
                start = System.currentTimeMillis();
                camp.assignmentCampersWithOptimum();
                stop = System.currentTimeMillis();
                System.out.println(camp.toString());
                System.out.println("\n Finish at: " + String.valueOf(stop - start));

            }
        }

/*
        Camp camp = new Camp(Integer.valueOf(args[1]));
        camp.importData(args[0]);
        //camp.autoImport(50);
        camp.setupLeaders();
        camp.setupTogether(args[2]);
        camp.setupSeparate(args[3]);
        camp.assignmentCampersWithOptimum();
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

 */
    }
}

