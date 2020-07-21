package com.example.guessmaster;

public abstract class Entity {
    private String name;
    private Date born;
    private double difficulty; //new instance variable, to be between 0 and 1


    public Entity(String name, Date birthDate, double difficulty) {
        this.name = name;
        this.born = new Date(birthDate); //no privacy leak
        this.difficulty = difficulty;
    }

    public Entity(Entity entity) { //COPY CONSTRUCTOR?
        this.name = entity.name;
        this.born = new Date(entity.born); //no privacy leak
        this.difficulty = entity.difficulty;
    }

    public String getName() {
        return name;
    }

    public Date getBorn() {
        return new Date(born);
    }

    public double getDifficulty() {
        return difficulty;
    }

    public String toString() {
        return "Name: "+name+"\n"+"Born: "+born.toString()+"\n";
    }

    public int getAwardedTicketNumber() {
        return (int) (difficulty * 100); //difficulty is of type double, casted to type integer
    }

    abstract public String entityType(); //to be specified in child classes

    abstract public Entity clone(); //will be created in child classes

    public String welcomeMessage() {
        return entityType();
    }

    public String closingMessage() {
        return "Congratulations! The detailed information of the entity you guessed is:\n" + toString();

    }
}
