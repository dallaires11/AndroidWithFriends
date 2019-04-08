package com.example.androsim;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID=1L;

    private int id;
    private String ndc;
    private  String password;
    private Integer pdv ;
    private Integer mana ;

    public User(String ndc, String password/*,int pdv, int mana*/) {
        this.ndc = ndc;
        this.password = password;
        pdv=800;
        mana = 200;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNDC() {
        return ndc;
    }

    public void setNDC(String ndc) {
        this.ndc = ndc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPDV() {return pdv;}

    public void setPDV(Integer pdv) {this.pdv=pdv;}

    public Integer getMana() {return mana;}

    public void setMana(Integer mana) {this.mana=mana;}
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", ndc='" + ndc + '\'' +
                ", password='" + password + '\'' +
                ", pdv='" + pdv + '\'' +
                ", mana='" + mana + '\'' +
                '}';
    }
}
