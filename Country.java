package com.example.guessmaster;

public class Country extends Entity {
    private String capital;

    public Country(Entity entity, String capital) {
        super(entity);
        this.capital = capital;
    }

    public Country(String name, Date birthDate, String capital, double difficulty) {
        super(name, birthDate, difficulty);
        this.capital = capital;
    }

    public Country(Country c) { //COPY CONSTRCTOR
        super(c.getName(), c.getBorn(), c.getDifficulty());
        this.capital = c.capital;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public String entityType() {
        return "This entity is a Country!";
    }

    @Override
    public Country clone() {
        return new Country(this); //utilizes the copy constructor
    }

    public String toString() {
        return super.toString()+"Capital: "+capital;


    }
}
