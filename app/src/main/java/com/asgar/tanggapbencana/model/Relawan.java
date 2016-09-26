package com.asgar.tanggapbencana.model;

/**
 * Created by skday on 9/26/16.
 */

public class Relawan {
    private String nama, kontak;

    public Relawan(String nama, String kontak) {
        this.kontak = kontak;
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }
}
