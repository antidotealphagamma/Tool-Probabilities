

import java.io.*;
import java.util.*;


public class ReadCVS {


    public static void main(String[] args) {
        ReadCVS obj = new ReadCVS();
        obj.run();
    }

    private void run() {

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

            //ArrayList<String> tool1Users = new ArrayList();
            //ArrayList<String> tool2Users = new ArrayList();
            //int toolACount = 0, toolBCount = 0;

            while ((line = br3.readLine()) != null) {
                String[] array = line.split(cvsSplitBy);
                String companyName, toolName;
                companyName = array[1];
                toolName = array[2];


                //check if tool is in the line and add to list
                for (String tool : userMap.keySet()) {
                    if (!userMap.get(toolName).contains(companyName)) {
                        userMap.get(toolName).add(companyName);
                    }
                }
//
//                int count = 0;
//                for (String tool : userMap.keySet()) {
//                    count++;
//                }

                //System.out.printf("%d\n", count);

                for (String tool : userMap.keySet()) {
                    calculateProbability(userMap.get(tool), userMap.get(toolName), tool, toolName);
                }
            }

            //for (String key: userMap.keySet()) System.out.println(key);
            //System.out.println(userMap);


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

    /* @Params:
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    First two arguments take an ArrayList of users who use a particular tool.
    Last two arguments take the name of the tools, ergo the key of the Hashmap reference.
    Final argument takes the filename for output.
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public void calculateProbability(ArrayList<String> a, ArrayList<String> b, String toolNameA,
                                     String toolNameB) {

        //calculating combined count
        int combinedCount = 0;
        if (a.size() > b.size()) {
            for (int i = 0; i < a.size(); i++) {
                if (b.contains(a.get(i))) {
                    combinedCount++;
                }
            }
        } else {
            for (int i = 0; i < b.size(); i++) {
                if (a.contains(b.get(i))) {
                    combinedCount++;
                }
            }
        }

    /* Probability calculation module
     * ----------------------------
     */
        float p1 = 0, p2 = 0;
        float divisor = combinedCount;
        p1 = (divisor / a.size());
        p2 = (divisor / b.size());
        int factoredA = (int)p1 * 100;
        int factoredB = (int)p2 * 100;

        //See size
//        if (a.size() > 10 && b.size() > 10) {
//            System.out.println("-------------------");
//            System.out.println(a + " size is " + a.size());
//            System.out.println(b + " size is " + b.size());
//        }

        PrintStream out = null;

        if (p1 != 0 && p2 != 0 && combinedCount != 0) {
            try {
                //Create FileWriter object

                out = new PrintStream(new FileOutputStream("example22.txt"));


                  //Debugging
                
//                System.out.println("------------------");
//                System.out.println("Count of " + toolNameA +  " users is: " +  a.size());
//                System.out.println("Count of " + toolNameB + " users is: " + b.size());
//                System.out.println("Count of users who use both " + toolNameA + " and " +
//                        toolNameB + " is: " + combinedCount);
//                System.out.printf("Probability for " +  toolNameA + " is %.3f, probabilty for " +
//                        toolNameB +" is %.3f \n", p1, p2);

                
                //Write to text file
                out.println("------------------");
                out.println("Count of " + toolNameA +  " users is: " +  a.size());
                out.println("Count of " + toolNameB + " users is: " + b.size());
                out.println("Count of users who use both " + toolNameA + " and " +
                 toolNameB + " is: " + combinedCount);
                out.printf("Probability for " +  toolNameA + " is %.3f, probabilty for " +
                        toolNameB +" is %.3f \n", p1, p2);

                //Close writer
                out.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            }
        }
    }
