=========================================================================================
The purpose of this project is to be able to calculate that given n people use a particular
tool X, the probability p that n people would use another tool Y. We can do so 
for each tool by calculating each associated binary-relative permutation and outputting the results to
a CSV file. 


Currently the path is set for the "trial.csv" file in my downloads folder. Where csvFile, a variable of type string is the path of the dataset to be processed. Change this to the path that leads to the specified dataset. 

Known Issues:
There are multiple iterations of the same datapoint. For example, each permutation stores a combined count value which iterativey updates in the userMap structure. This is probably due to the fact that each key value mapping -- where the key is the company name and the value being stored is an array list of companies using the given tool -- is being updated at every line being read. We add the companies to the key mapping, however it still stores the iterative numbers. More concretely, lets take a look at the (LinkedIn,MailChimp) output in the output txt file. We first have the datapoint combined count = 1, along with 1 and 0.333 probabilty values for LinkedIn and MailChimp respectively. Then we have the datapoint combined count = 1, along with 1 and 0.25 respective probability values. The probability values change because the size of the ArrayLists containing both the LinkedIn and MailChimp users have updated sizes. Most likely the size of this associated ArrayList for MailChimp has increased in the consectuive iteration, which would consequently mutate our values. However all hope is not lost! The final, alpha version, of this datapoint exists in the txt file. This datapoint has a combined count of 42, with associated probabilities of 0.5316 and 0.2308 for LinkedIn and MailChimp respectively. 

This is a huge problem -- as the dataset now not only contains each binary-relative permutation for each tool, but also the iteratively incrementing data. 
