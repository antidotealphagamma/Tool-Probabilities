//Niels Kjer

import java.io.*;
import java.util.*;

public class ReadCVS {
  

  public static void main(String[] args) {
    ReadCVS obj = new ReadCVS();
    obj.run(); 
  }
  
  public void run() {
    
    String csvFile = "/Users/nielskjer/Downloads/trial.csv";
    BufferedReader br1 = null;
    BufferedReader br2 = null;
    BufferedReader br3 = null;
    BufferedReader br4 = null;
    String line = "";
    String cvsSplitBy = ",";
    
    
    try {
      FrequencyTable freq = new FrequencyTable(csvFile);
      //freq.print();
      
      //generate list of all the tools used
      ArrayList<String> allTools = freq.generate();
      
      for (int i = 0; i < allTools.size(); i++) {
        String name = allTools.get(i);
        
        ArrayList<String> tool = new ArrayList();
        //System.out.println(name);
        
      }
      //Create BufferedReader object
      br1 = new BufferedReader(new FileReader(csvFile));
      br2 = new BufferedReader(new FileReader(csvFile));
      br3 = new BufferedReader(new FileReader(csvFile));
      br4 = new BufferedReader(new FileReader(csvFile));
      
      //Create Hashmap
      HashMap<String, ArrayList<String>> userMap = new HashMap<String, ArrayList<String>>();
      
      //Generate User ArrayList for all tools and store in map
      while ((line = br1.readLine()) != null) {
        String[] inLine = line.split(cvsSplitBy);
        String companyName, toolName;
        companyName = inLine[1];
        toolName = inLine[2];
        if (!userMap.containsKey(toolName)) {
          userMap.put(toolName, new ArrayList<String>());
        }
        //Testing: Show key values
        //for (String key : userMap.keySet()) System.out.println(key);
      }
      
      while ((line = br2.readLine()) != null) {
        String[] inLine = line.split(cvsSplitBy);
        String companyName, toolName;
        companyName = inLine[1];
        toolName = inLine[2];
        //System.out.println("here");
        if (!userMap.containsValue(companyName)) {
          //if (!userMap.containsKey())
          userMap.get(toolName).add(companyName);
        }
        
        //Test
        //System.out.println(userMap);
        //for (String key: userMap.keySet()) System.out.println(key);
      }
      
      ArrayList<String> tool1Users = new ArrayList();
      ArrayList<String> tool2Users = new ArrayList();
      //int toolACount = 0, toolBCount = 0;
       
      while ((line = br3.readLine()) != null) {
        String[] array = line.split(cvsSplitBy);
        String companyName, toolName;
        companyName = array[1];
        toolName = array[2];
        
        
        //check if tool is in the line and add to list
        for (String tool : userMap.keySet()) {
          if (userMap.get(toolName).contains(toolName)) {
            userMap.get(toolName).add(companyName);
          }
        }
        //for (String key: userMap.keySet()) System.out.println(key);
        System.out.println(userMap);
        
//        if (toolName.contains("MailChimp")) {
//          //check for duplicate entries
//          if (!tool1Users.contains(companyName)) {
//            tool1Users.add(companyName);
//          }
//        }
//        
//        if (toolName.contains("TriNet")) {
//          //check for duplicate entries
//          if (!tool2Users.contains(companyName)) {
//            tool2Users.add(companyName);
//          }
//        }
//        
      }
     
      
      
      //testing purposes
      //calculateProbability(tool1Users, tool2Users);
      

        
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br1 != null) {
        try {
          br1.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    
    System.out.println("Done");
    
  }
  
  public void calculateProbability(ArrayList<String> a, ArrayList<String> b) {
    //calculating combined count
    int combinedCount = 0;
    if (a.size() > b.size()) {
      for (int i = 0; i < a.size(); i++) {
        if (b.contains(a.get(i))) {
          combinedCount++;
        }
      }
    }
    else {
      for (int i = 0; i < b.size(); i++) {
        if (a.contains(b.get(i))) {
          combinedCount++;
        }
      }
    }
    //testing purposes
    System.out.println("------------------");
    System.out.println("Count of users who use both: " + combinedCount);
    
    /* Probabilty calculation module
     * ----------------------------
     */
    float p1 = 0, p2 = 0;
    float divisor = combinedCount;
    p1 = (divisor/a.size());
    p2 = (divisor/b.size());
    
    System.out.printf("Probability for A is %.3f probabilty for B is %.3f \n", p1, p2);
  }
  
    
}