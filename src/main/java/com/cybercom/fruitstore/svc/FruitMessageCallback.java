package com.cybercom.fruitstore.svc;

import java.io.IOException;

public interface FruitMessageCallback<M> {
	
	void messageArrived(M message) throws IOException;
}
