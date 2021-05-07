package nids;

import java.util.Scanner;

public class NIDS {

    String Training_data_Path = "";

    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        System.out.println("--------------------------------------------------------");
        System.out.println("Network Intrusion Detection System");
        System.out.println("--------------------------------------------------------");

        System.out.println("1. Decision Tree");
        System.out.println("2. Naive Bayes");
        System.out.println("3. NB Tree - Combined");

        System.out.print("Please select an algorithm :");
        Scanner sc = new Scanner(System.in);
        int algo_option = sc.nextInt();

        Controller c = new Controller();
        c.action(algo_option);

    }

}
