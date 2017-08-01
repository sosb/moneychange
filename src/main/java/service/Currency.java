package service;



public enum Currency {

    PESO("PESO"),
    HUF_BEFORE2008("HUF_BEFORE2008"),
    HUF_AFTER2008("HUF_AFTER2008");

    private String name;

    Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
