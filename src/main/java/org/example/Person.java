package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Person extends Main {

    int id;
    String name;


    public Person() {
        List<Person> people = new ArrayList<>();

        people.add(new Person(1, "Kemal"));
        people.add(new Person(2, "Ali"));
        people.add(new Person(3, "Kiki"));
        people.add(new Person(4, "lili"));

        System.out.println();
        System.out.println("----------------Person---Class----------------");

        people.stream().skip(2).limit(3).forEach(person -> person.sayHello());
        System.out.println();

        List<Person> find = people.stream().filter(person -> person.getId() < 3).collect(Collectors.toList());
        Map<Integer, String> integerStringMap = find.stream().collect(Collectors.toMap(Person::getId, Person::getName));
        integerStringMap.forEach((integer, s) -> System.out.println(integer + "--> " + s));
        System.out.println();

        System.out.println(integerStringMap);
        System.out.println();



        List<Person> kh = people.stream().filter(person -> person.getName().startsWith("K")).collect(Collectors.toList());
        System.out.println("K harfi ile başlayanlar = " + kh);



    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void sayHello() {
        System.out.println("Hello, I am  " + this.name);
        System.out.println(LocalDate.now());

    }


}