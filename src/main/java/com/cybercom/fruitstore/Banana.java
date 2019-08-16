package com.cybercom.fruitstore;


//banana
public class Banana {

private FruitObject f;

    public Banana(FruitObject f){
        if(f.getT().equals("Banana")){
            setF(f);
        }
    else {
        f = null;
    }
    }

	/**
	 * @return the f
	 */
	public FruitObject getF() {
		return f;
	}

	/**
	 * @param f the f to set
	 */
	public void setF(FruitObject f) {
		this.f = f;
	}

}