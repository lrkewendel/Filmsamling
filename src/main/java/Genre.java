public enum Genre {ACTION("AKTION"), COMEDY("KOMEDIE"), DRAMA("DRAMA"), THRILLER("THRILLER"), HORROR("GYSER"), ROMANCE("ROMANSE"), CRIME("KRIMI"), SCI_FI("SCI_FI"), FANTASY("FANTASI"), ANIMATION("ANIMATION"), DOCUMENTARY("DOKUMENTAR"), FAMILY("FAMILIEFILM"), OTHER("ANDET");

    private String danishName;

    Genre(String danishName){
        this.danishName = danishName;
    }

    public String getDanishName() {
        return danishName;
    }
}
