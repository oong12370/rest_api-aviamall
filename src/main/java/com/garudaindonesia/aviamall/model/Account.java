package com.garudaindonesia.aviamall.model;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    private long id;
    private String nama;
    private String alamat;

    public Account() {
    }

    public Account(long id, String nama, String alamat) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "nama",nullable = false)
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    @Column(name = "alamat",nullable = false)
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public String toString() {
        return "Member [id=" + id + ", nama=" + nama + ", alamat=" + alamat + "]";
    }

}