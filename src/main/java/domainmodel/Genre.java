package domainmodel;

public enum Genre {
    ACTION("Action"),
    ANIMATION("Animation"),
    DOCUMENTARY("Dokumentar"),
    DRAMA("Drama"),
    FAMILY("Familiefilm"),
    FANTASY("Fantasi"),
    HORROR("Gyser"),
    COMEDY("Komedie"),
    CRIME("Krimi"),
    ROMANCE("Romantik"),
    SCI_FI("Science Fiction"),
    THRILLER("Thriller"),
    OTHER("Andet");


    private String danishName;

    Genre(String danishName){
        this.danishName = danishName;
    }

    public String getDanishName() {
        return danishName;
    }
}