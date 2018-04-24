# data-comparison

## Requirements
* Maven
* Java
* Docker

## Setup
The following Docker commands are used to fill your database with data

First you need to get the files which can be done with following commands

The file can be found here
[https://github.com/datsoftlyngby/soft2018spring-databases-teaching-material/blob/master/data/archive_graph.tar.gz](https://github.com/datsoftlyngby/soft2018spring-databases-teaching-material/blob/master/data/archive_graph.tar.gz)

After that you need to unpack it and change some headers. That can be done with the follwing commands
```
sed -i -E '1s/.*/:ID,name,job,birthday/' social_network_nodes.csv
sed -i -E '1s/.*/:START_ID,:END_ID/' social_network_edges.csv
```
After that you can create your neo4j container in docker and import the data with the following command

```
docker run \
    -d --name neo4j \
    --rm \
    --publish=7474:7474 \
    --publish=7687:7687 \
    --volume=$(pwd)/import:/import \
    --volume=$(pwd)/plugins:/plugins \
    --env NEO4J_AUTH=neo4j/class \
    --env=NEO4J_dbms_security_procedures_unrestricted=apoc.\\\*,algo.\\\* \
    --env=NEO4J_dbms_memory_pagecache_size=6G \
    --env=NEO4J_dbms_memory_heap_max__size=10G \
    neo4j

docker exec neo4j sh -c 'neo4j stop'
docker exec neo4j sh -c 'rm -rf data/databases/graph.db'

docker exec neo4j sh -c 'neo4j-admin import \
    --nodes:Person /import/social_network_nodes.csv \
    --relationships:ENDORSES /import/social_network_edges.csv \
    --ignore-missing-nodes=true \
    --ignore-duplicate-nodes=true \
    --id-type=INTEGER'
```

## Running the Example

To run the code, open the project in an IDEA and navagiate to the Main class and execute the code

## Extra notes
This exercise is not finished :(

I had trouble getting the mySQL/postgresSQL docker container to run, so the code only have a connection to the neo4j database

The quries that can be seen in the file Neo4jConnection should be correct, but the execute time is for some reason the same 
for each depth. I am calculation the average running time and median for the queries, but when im trying to convert the
execution time to seconds i get some other numbers that are definitely not seconds
