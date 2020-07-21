package com.example.guessmaster;

public class Person extends Entity {
    private String gender;

    public Person(Entity entity, String gender) {
        super(entity);
        this.gender = gender;
    }

    public Person(String name, Date birthDate, String gender, double difficulty) {
        super(name, birthDate, difficulty);
        this.gender = gender;
    }

    public Person(Person p) { //COPY CONSTRCTOR
        super(p.getName(), p.getBorn(), p.getDifficulty());
        this.gender = p.gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String entityType() {
        return "This entity is a Person!";
    }

    @Override
    public Person clone() {
        return new Person(this); //utilizes the copy constructor
    }

    public String toString() {
        return super.toString()+"Gender: "+gender;
    }

}

