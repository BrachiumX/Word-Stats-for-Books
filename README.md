# Word-Stats-for-Books

This mini-project creates stats like:<br/>

For specific words:<br/>

Frequency of the word<br/>
How many usages of this word starts with an uppercase character<br/>
Where in sentence is this word located on average<br/>
What is the average page location of this word<br/>
At which page does this word occur first<br/>
At which page does this word occur last<br/>

General stats for the book:<br/>

Number of words<br/>
Number of pages<br/>
Number of sentences<br/>
Number of distinct words<br/>

and writes the results into a txt file or into a database in a mysql based sql server.<br/>

Since txt files do not have pages I have decided that 250 words equal one page but this amount can be changed inside Parser.java file.<br/>

This project uses Java 22 and Maven so you need both to run it.<br/>

To print the results into a txt file:<br/>

Go into pom.xml make the first argument "textfile" (without quotes), the second argument the full path to the txt file you want to analyze, the third argument the full path of the txt file you want the results written into, the other arguments are not important.<br/>

To send the results into an sql server:<br/>

First create a table named total in your database. This table will save the general stats for each book. Then go into pom.xml make the first argument "sql" (without quotes), the second argument the full path to the txt file you want to analyze, the third argument the url to your server, the fourth argument the name of the table you want to create for this book, the fifth argument your username for the server and the sixth argument your password for the server.<br/>

After doing either of these two options open a terminal which points to the outermost folder and run<br/>

mvn clean compile exec:java<br/>

It is important that it is compiled each time since the arguments are passed through pom.xml.<br/>

Some things may not be fully accurate due to formatting issues.<br/>

The columns TotalPosition and TotalPages in SQL Results mean sum of each position of the word in sentence for each of its occurrences and the same thing but for pages. I included this because you can derive the average values by dividing these values by the total frequncy of the word.<br/>

Also first and last occurrences are word based in SQL version and not page based like in txt version.<br/>
