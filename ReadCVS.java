
import java.io.*;
import java.util.*;


public class ReadCVS {

    //Instance variables
    private static HashMap<String, Integer> outSize = new HashMap<>();
    private static ArrayList<StringBuilder> names = new ArrayList<>();
    private static ArrayList<StringBuilder> probabilities = new ArrayList<>();

    //Main Program
    public static void main(String[] args) {
        ReadCVS obj = new ReadCVS();
        obj.run();
        obj.printProbabilities(names, outSize);
    }

    
    private void run() {

        String csvFile = "/Users/nielskjer/Downloads/trial.csv";
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        String line = "";
        String cvsSplitBy = ",";


        try {
            //FrequencyTable freq = new FrequencyTable(csvFile);

            //Create BufferedReader object
            br1 = new BufferedReader(new FileReader(csvFile));
            br2 = new BufferedReader(new FileReader(csvFile));

            //Create user list mapping with tools
            HashMap<String, ArrayList<String>> userMap = new HashMap<>();

            //Generate User ArrayList for all tools and store in map
            while ((line = br1.readLine()) != null) {
                String[] inLine = line.split(cvsSplitBy);
                String toolName;
                toolName = inLine[2];
                if (!userMap.containsKey(toolName)) {
                    userMap.put(toolName, new ArrayList<>());
                }
            }

            //Map tools with user lists
            while ((line = br2.readLine()) != null) {
                String[] array = line.split(cvsSplitBy);
                String companyName, toolName;
                companyName = array[1];
                toolName = array[2];
                
                //Checks if a tool is in the line and adds to list if true
                if (!userMap.get(toolName).contains(companyName)) {
                    userMap.get(toolName).add(companyName);
                    }
                
                //Calculate the probabilities for each tool permutation
                for (String tool : userMap.keySet()) {
                    calculateProbability(userMap.get(tool), userMap.get(toolName), tool, toolName);
                }
            }
            
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
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public void calculateProbability(ArrayList<String> a, ArrayList<String> b, String toolNameA,
                                     String toolNameB) {

        //Calculate combined count for each tool
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

        if (p1 != 0 && p2 != 0 && combinedCount != 0) {

            //Build strings to be added on the ArrayLists
            StringBuilder sb = new StringBuilder();
            sb.append(toolNameA);
            sb.append(": ");
            sb.append(a.size());
            sb.append(", ");
            sb.append(toolNameB);
            sb.append(": ");
            sb.append(b.size());

            //Add to ArrayLists of names and size
            names.add(sb);

            StringBuilder build = new StringBuilder();

            build.append("(");
            build.append(toolNameA);
            build.append(",");
            build.append(toolNameB);
            build.append(")");
            build.append(" is: ");
            build.append(combinedCount);
            build.append(" -- ");
            build.append("Probability for ");
            build.append(toolNameA);
            build.append(" is: ") ;
            build.append(p1);
            build.append(" and the probability for ");
            build.append(toolNameB);
            build.append(" is: ");
            build.append(p2);
            
            probabilities.add(build);
        }
    }

    public void printProbabilities(ArrayList<StringBuilder> names, HashMap<String, Integer> map) {

        try {
            //Create FileWriter object
            PrintStream out = new PrintStream(new FileOutputStream("example22.txt"));

            //Write to text file
            out.println("=========================");

            for (int i = 0; i < names.size(); i++) {
                out.println(names.get(i));
                out.println("=================================");
            }
            out.println("=================================");
            out.println("=================================");
            out.println("!!Probability Calculations!!");
            out.println("=================================");
            out.println("=================================");

            for (int i = 0; i < probabilities.size(); i++) {
                out.println(probabilities.get(i));
                out.println("=================================");
            }
            //Close writer
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
