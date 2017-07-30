package service;


public enum Currency {

    PESO("peso"),
    HUF_BEFORE2008("huf_before2008"),
    HUF_AFTER2008("huf_after2008");

    private String name;

    Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
