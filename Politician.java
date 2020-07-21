package com.example.guessmaster;

public class Politician extends Person {
    private String party;

    public Politician(Person person, String party) {
        super(person);
        this.party = party;
    }

    public Politician(String name, Date birthDate, String gender, String party, double difficulty) {
        super(name, birthDate, gender, difficulty); //uses constructor in Person class
        this.party = party;
    }

    public Politician(Politician s) { //COPY CONSTRCTOR
        super(s.getName(), s.getBorn(), s.getGender(), s.getDifficulty());
        this.party = s.party;
    }

    public String getParty() {
        return party;
    }

    @Override
    public String entityType() {
        return "This entity is a Politician!";
    }

    @Override
    public Politician clone() {
        return new Politician(this); //utilizes the copy constructor
    }

    public String toString() {
        return super.toString()+"\nPolitical Party: "+party;
    }
}

