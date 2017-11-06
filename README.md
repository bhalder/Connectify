# Connectify v0.01

This is a project to help you find all your public data on the web.

With so much of our data on the web, it is interesting to see, how much of 
our data is actually available to the public.

The project right now will crawl for text based content and save the results
in a cassandra database. The data once collected will be used to cluster
information together to find out 

For the first version -
- Java8
- jsoup 1.8.3
- Cassandra 3.11

As a personal preference, I code using text based editors (vim/vi/nano) only.
So, most of the tasks I do will be based on that. 

mvn archetype:generate -DgroupId=com.halder.barun -DartifactId=connectify -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
