package nids;

import java.util.Scanner;

public class Controller {
    
    //1. Decision Tree
    //2. Naive Bayes
    //3. NB Tree
    int algorthimIdentifier = -1;
    
    int actor;
    
    public int action(int actor) throws Exception{
        
        switch (actor) {
            case 1:
                System.out.println("Decision Tree Selected");
                FileHandler f1 = new FileHandler();
                System.out.print("Training Data Path:");
                Scanner s1 = new Scanner(System.in);
                f1.trainingDataPath = s1.nextLine();

                DecisionTree d = new DecisionTree(f1);
                d.train();

                System.out.print("Test Data Path:");
                Scanner s11 = new Scanner(System.in);
                f1.testDataPath = s11.nextLine();

                d.test();
                break;
                
            case 2:
                System.out.println("Naive Bayes Selected");
                FileHandler f2 = new FileHandler();
                System.out.print("Training Data Path:");
                Scanner s2 = new Scanner(System.in);
                f2.trainingDataPath = s2.nextLine();

                NaiveBayes n = new NaiveBayes(f2);
                n.train();

                System.out.print("Test Data Path:");
                Scanner s22 = new Scanner(System.in);
                f2.testDataPath = s22.nextLine();

                n.test();
                break;
                
            case 3:
                System.out.println("NB Tree - Combined");
                FileHandler f3 = new FileHandler();
                System.out.print("Training Data Path:");
                Scanner s3 = new Scanner(System.in);
                f3.trainingDataPath = s3.nextLine();
                
                System.out.print("Test Data Path:");
                Scanner s33 = new Scanner(System.in);
                f3.testDataPath = s33.nextLine();
                
                NaiveBayesTree nbt = new NaiveBayesTree(f3);
                nbt.train();
                nbt.test();

                break;
            default:
                System.out.println("Invalid option");
                break;
        }
        
        return 1;
    }
   
    
}
