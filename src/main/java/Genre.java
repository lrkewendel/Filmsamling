public enum Genre {ACTION("Aktion"), COMEDY("Komedie"), DRAMA("Drama"), THRILLER("Thriller"), HORROR("Gyser"), ROMANCE("Romantik"), CRIME("Krimi"), SCI_FI("Science Fiction"), FANTASY("Fantasi"), ANIMATION("Animation"), DOCUMENTARY("Dokumentar"), FAMILY("Familiefilm"), OTHER("Andet");

    private String danishName;

    Genre(String danishName){
        this.danishName = danishName;
    }

    public String getDanishName() {
        return danishName;
    }
}
