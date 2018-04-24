package com.kvetter;

import com.kvetter.db.neo4j.DBConnection;
import com.kvetter.db.neo4j.Neo4jConnection;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Neo4jConnection ne = new Neo4jConnection();
        HashMap<Integer, ArrayList<Double>> neo4jMap = new HashMap<>();
        neo4jMap.put(1, new ArrayList<>());
        neo4jMap.put(2, new ArrayList<>());
        neo4jMap.put(3, new ArrayList<>());
        neo4jMap.put(4, new ArrayList<>());
        ne.get20RandomIndexes().forEach((name) -> {


            ne.getDepthOne((String) name, neo4jMap).list().size(); // Fast
            ne.getDepthTwo((String) name, neo4jMap).list().size(); // Fast
            ne.getDepthThree((String) name, neo4jMap).list().size(); // Around 10 sec
            ne.getDepthFour((String) name, neo4jMap).list().size(); // Should take around 30 sec - avg and median not implemented
            //ne.getDepthFive((String) name); //// DON'T RUN THIS, TAKES TOO LONG - avg and median not implemented


        });
        System.out.println("Depth 1");
        System.out.println("avg: " + ne.getAverage(neo4jMap.get(1)));
        System.out.println("median" + ne.getMedian(neo4jMap.get(1)));

        System.out.println("Depth 2");
        System.out.println("avg: " + ne.getAverage(neo4jMap.get(2)));
        System.out.println("median" + ne.getMedian(neo4jMap.get(2)));

        System.out.println("Depth 3");
        System.out.println("avg: " + ne.getAverage(neo4jMap.get(3)));
        System.out.println("median" + ne.getMedian(neo4jMap.get(3)));

        System.out.println("Depth 4");
        System.out.println("avg: " + ne.getAverage(neo4jMap.get(4)));
        System.out.println("median" + ne.getMedian(neo4jMap.get(4)));
        DBConnection.closeDriver();
    }
}
