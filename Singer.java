package com.example.guessmaster;

public class Singer extends Person {
    private String debutAlbum;
    private Date debutAlbumReleaseDate;

    public Singer(Person person, String debutAlbum, Date debutAlbumReleaseDate) {
        super(person);
        this.debutAlbum = debutAlbum;
        this.debutAlbumReleaseDate = new Date(debutAlbumReleaseDate); //avoid privacy leak
    }

    public Singer(String name, Date birthDate, String gender, String debutAlbum, Date debutAlbumReleaseDate, double difficulty) {
        super(name, birthDate, gender, difficulty); //uses constructor in Person class
        this.debutAlbum = debutAlbum;
        this.debutAlbumReleaseDate = new Date(debutAlbumReleaseDate); //avoid privacy leak
    }

    public Singer(Singer s) { //COPY CONSTRCTOR
        super(s.getName(), s.getBorn(), s.getGender(), s.getDifficulty());
        this.debutAlbum = s.debutAlbum;
        this.debutAlbumReleaseDate = new Date(s.debutAlbumReleaseDate);
    }

    public String getDebutAlbum() {
        return debutAlbum;
    }

    public Date getDebutAlbumReleaseDate() {
        return new Date(debutAlbumReleaseDate); //avoid privacy leak
    }

    @Override
    public String entityType() {
        return "This entity is a Singer!";
    }

    @Override
    public Singer clone() {
        return new Singer(this); //utilizes the copy constructor
    }

    public String toString() {
        return super.toString()+"\nDebut Album: "+debutAlbum+"\n"+"Debut Album Released: " + debutAlbumReleaseDate.toString();
    }

}

