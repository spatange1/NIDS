package nids;

import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.Remove;

public class NaiveBayes {

    FileHandler f;
    NaiveBayesUpdateable nbu;
    FilteredClassifier fc;
    //NaiveBayesUpdateable fc;

    public NaiveBayes(FileHandler file) {
        this.f = file;
        this.nbu = new NaiveBayesUpdateable();

    }

    public Remove preprocessData() {
        Remove rm = new Remove();
        rm.setAttributeIndices("1");
        return rm;
    }

    public void train() throws Exception {

        Instances train = f.readTrainingData();
        train.setClassIndex(train.numAttributes() - 1);
        
        System.out.println("Training Data Total Instances : "+train.numInstances());

        this.fc = new FilteredClassifier();
        this.fc.setFilter(this.preprocessData());
        this.fc.setClassifier(this.nbu);

        System.out.println("Training Classifier....");
        this.fc.buildClassifier(train);
        System.out.println("Training Completed, Ready to test...");
    }

    public void test() throws Exception {
        
        Instances test = f.readTestData();
        test.setClassIndex(test.numAttributes() - 1);

        System.out.println("Test Data Total Instances : "+test.numInstances());

        int anomaly_detected = 0;
        int instances = 0;
        int a_not_predicted = 0;
        int not_a_predicted = 0;
        int a_pred = 0;
        int p = 0;
        int np = 0;

        for (int i = 0; i < test.numInstances(); i++) {
            double pred = this.fc.classifyInstance(test.instance(i));
            String a = "anomaly";
            String actual_value;
            String predicted_value;
            
            actual_value = test.classAttribute().value((int) test.instance(i).classValue());
            predicted_value = test.classAttribute().value((int) pred);

            System.out.print("ID: " + i);
            System.out.print(", actual: "+ actual_value);
            System.out.print(", predicted: "+ predicted_value);
            System.out.print("-----------------------------------------------------------\n");
            
            if (actual_value.equalsIgnoreCase(a)) {
                anomaly_detected++;
            }
            if (actual_value.equalsIgnoreCase(predicted_value)) {
                p++;
            }
            if (!actual_value.equalsIgnoreCase(predicted_value)) {
                np++;
            }
            if (actual_value.equalsIgnoreCase(a) && predicted_value.equalsIgnoreCase(a)) {
                a_pred++;
            }
            if ((!actual_value.equalsIgnoreCase(a)) && predicted_value.equalsIgnoreCase(a)) {
                not_a_predicted++;
            }
            if (actual_value.equalsIgnoreCase(a) && (!predicted_value.equalsIgnoreCase(a))) {
                a_not_predicted++;
            }
            instances++;
        }

        double precision = a_pred * 100 / (a_pred + not_a_predicted);
        double recall = a_pred * 100 / (anomaly_detected);
        double accuracy = (p * 100) / (p + np);

        System.out.println("Total_no._of_instances : " + instances + "\nTotal anomaly detected : " + anomaly_detected);
        System.out.println("Correctly classified : " + p + "\nIncorrectly classified : " + np);
        System.out.println("Precision : " + precision + "\nRecall : " + recall);
        System.out.println("Accuracy : " + accuracy);

    }

}
