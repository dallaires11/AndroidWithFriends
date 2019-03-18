package com.example.androsim.Model;

public class Monstre {
    private int vie,vieMax,degat;

    public Monstre(){
        vieMax = 800;
        vie = vieMax;
        degat = 20;
    }

    public Monstre(int vie){
        vieMax = 800;
        this.vie = vie;
        degat = 20;
    }

    public int attaque(){
        return degat;
    }

    public boolean mangerDegat(int degatSubit){
        vie -= degatSubit;

        if(vie<=0){
            vie = 0;
            return true; //Le monstre est mort
        }

        return false; //Le monstre est toujours vivant
    }


    public int getVieMax(){
        return vieMax;
    }

    public int getVie(){
        return vie;
    }
}