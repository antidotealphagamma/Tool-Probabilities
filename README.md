=========================================================================================
Purpose: 

The purpose of this project is to be able to calculate that given n people use a particular
tool X, the probability p that n people would use another tool Y. We can do so 
for each tool by calculating relative permutations on tool associations and output the results to
a txt file. These figures are important because we can analyze the likelihood someone will use another tool Y, given they already use tool X. We can answer interesting questions, such as: "if a user is using Google docs, whats the likelihood that they are also using Slack?".

Path: 
Currently the path is set for the "trial.csv" file in my personal downloads folder. Where csvFile, a variable of type string is the path of the dataset to be processed. Change this to the path that leads to the specified dataset. 

=========================================================================================
Known Issues:
There are multiple iterations of the same datapoint. For example, each permutation stores a combined count value which iterativey updates in the userMap structure. This is probably due to the fact that each key value mapping -- where the key is the company name and the value being stored is an array list of companies using the given tool -- is being updated at every line being read. We add the companies to the key mapping, however it still stores the iterative numbers. More concretely, lets take a look at the (LinkedIn,MailChimp) output in the output txt file. We first have the datapoint combined count = 1, along with 1 and 0.333 probabilty values for LinkedIn and MailChimp respectively. Then we have the datapoint combined count = 1, along with 1 and 0.25 respective probability values. The probability values change because the size of the ArrayLists containing both the LinkedIn and MailChimp users have updated sizes. Most likely the size of this associated ArrayList for MailChimp has increased in the consectuive iteration, which would consequently mutate our values. However all hope is not lost! The final, alpha version, of this datapoint exists in the txt file. This datapoint has a combined count of 42, with associated probabilities of 0.5316 and 0.2308 for LinkedIn and MailChimp respectively. 

This is a huge problem -- as the dataset now not only contains each binary-relative permutation for each tool, but also the iteratively incrementing data. The final text file generated is 314.6MB, which is excessive to say the least. 

Another problem we found was the inconsistency of some results in the dataset. Cross-checking the probability figures with results found from Excel, the count of some tools were off for some firms. This could possibly be due to the parsing of the original csv file, where some fields would not be picked up due to formatting errors. 


====================================================================================================
Sample output:

These figures were taken directly from the produced "example22.txt" file. 

(LinkedIn,MailChimp) is: 42 -- Probability for LinkedIn is: 0.5316456 and the probability for MailChimp is: 0.23076923

(Slack,Google Analytics) is: 158 -- Probability for Slack is: 0.7314815 and the probability for Google Analytics is: 0.69298244

(Google Drive,Gusto (formerly ZenPayroll)) is: 46 -- Probability for Google Drive is: 0.21198156 and the probability for Gusto (formerly ZenPayroll) is: 0.6764706

========================================================================================================
Retrieving the correct data:

Since there are redundant iterations of the data as it builds up, we need to retrieve the finalmost datapoint. We can do so in a text editor by crudely searching for the entry fields "(Company#1,Company#2)" and locating the final version through the use of the back button.  
