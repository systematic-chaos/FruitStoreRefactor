package com.cybercom.fruitstore.entity;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;

// fruit
@Entity
public class FruitObject {
@Id
private int id;

    // Type
    private String t;
    // Name
    private String n;

    private int p;

    public FruitObject(){

    }

    public FruitObject(String t, String n, int p, int id){
        if (id == 0){
            Random rand = new Random();
            this.id =rand.nextInt();
        }
        else{
            this.id = id;
        }
        //set type
        setT(t);
        //set prices
        setP(p);
        //set name
        setN(n);



    }

	/**
	 * @return the t
	 */
	public String getT() {
		return t;
	}

	/**
	 * @param t the t to set
	 */
	public void setT(String t) {
		this.t = t;
	}



	/**
	 * @return the p
	 */
	public int getP() {
		return p;
	}

	/**
	 * @param p the p to set
	 */
	public void setP(int p) {
		this.p = p;
	}

	/**
	 * @return the n
	 */
	public String getN() {
		return n;
	}

	/**
	 * @param n the n to set
	 */
	public void setN(String n) {
		this.n = n;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
}