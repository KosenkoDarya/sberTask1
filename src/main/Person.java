package main;

public class Person {
    private String name;
    private String kto;

    public Person(String name, String kto) {
        this.name = name;
        this.kto = kto;
    }

    public String getName() {
        return name;
    }

    public String getKto() {
        return kto;
    }
}