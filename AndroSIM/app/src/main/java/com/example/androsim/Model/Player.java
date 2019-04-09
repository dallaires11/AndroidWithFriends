package com.example.androsim.Model;

public class Player {
    private int vie,vieMax,mana,manaMax,degat;

    public Player(){
        vieMax = 800;
        vie = vieMax;
        manaMax = 200;
        mana = manaMax;
        degat = 20;
    }

    public Player(int vie,int mana){
        vieMax = 800;
        this.vie = vie;
        manaMax = 200;
        this.mana = mana;
        degat = 20;
    }

    public int attaque(){
        return degat;
    }

    public void repos(){
        vie+=30;
        if(vie>vieMax)
            vie = vieMax;
    }

    public boolean mangerDegat(int degatSubit){
        vie -= degatSubit;

        if(vie<=0){
            vie = 0;
            return true; //Le joueur est mort
        }

        return false; //Le joueur est toujours vivant
    }

    public boolean utiliserSort(int cout){
        int tmp = mana - cout;
        if(tmp <0)
            return false; //Peut pas caster le spell
        else{
            mana = tmp;
            return true; //Le spell peut etre caster
        }
    }

    public int getVieMax(){
        return vieMax;
    }

    public int getVie(){
        return vie;
    }

    public void setVie(int vie){this.vie=vie;}

    public int getManaMax(){
        return manaMax;
    }

    public void setMAna (int mana){this.mana = mana;}

    public int getMana(){
        return mana;
    }
}