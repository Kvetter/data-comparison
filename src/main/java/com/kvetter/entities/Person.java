package com.kvetter.entities;

import java.util.Set;

public class Person {

    String userId;
    String name;
    Set<Person> friends;
    Set<Viewing> views;
    Person referredBy;

    public Person(String userId, String name, Set<Person> friends, Set<Viewing> views, Person referredBy) {
        this.userId = userId;
        this.name = name;
        this.friends = friends;
        this.views = views;
        this.referredBy = referredBy;
    }
}
