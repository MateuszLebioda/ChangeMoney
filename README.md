# Change Money
Recursive algorithm for change money.

# Java

Application is written in Java  using OpenJDK 12.0.1 - redchat 2019-04-16

# Run Application 

To run application you will need Maven and Java (recommended: OpenJDK 12.0.1 redchat)
\
\
Build application using command: `mvn clean package`. After that, you should find .jar file in {project_directory}\target\target
\
\
To lunch application use command: `java -jar {jar_name}.jar`


##### Application need parameters! 

For example, to change 500 into 1, 2 and 5 coin you will use command: `java -jar {jar_name.jar} -m 500 -d [1,2,5]`.

##### Flags 

 -m -> Money to change. For example: -m 500 
 \
 \
 -d -> Denomination list. For example: -d [1,2,5] 
 \
 \
 -g -> On graphic mode 
 \
 \
 -t -> On text mode. Application will save result in txt file
 \
 \
 -rt -> Algorithm using recursion table. The fasts algorithm, is not allowed using graphic and text mode.
 
###### WARRING! To both of above mode generation time will be longer and to big data you can get StackOverFlow Exception

To lunch this information in application  use command  `java -jar {jar_name.jar} -help` or `java -jar {jar_name.jar} -h` 
