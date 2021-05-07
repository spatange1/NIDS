package nids;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import java.io.File;

public class FileHandler {

    String trainingDataPath;
    String testDataPath;


    public Instances readTrainingData() throws Exception{
        
        return this.readData(this.trainingDataPath);
    }
    
    public Instances readTestData() throws Exception{
        
        return this.readData(this.testDataPath);
    }
    
    public Instances readData(String filename) throws Exception {
        ArffLoader loader = new ArffLoader();
        loader.setSource(new File(filename));
        Instances data = loader.getDataSet();
        return data;
    }

}
