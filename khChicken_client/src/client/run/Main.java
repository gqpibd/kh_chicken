package client.run;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Single;

import client.communicator.Communicator;
import client.singleton.Singleton;
import client.view.mainView;

public class Main {

	public static void main(String[] args) {
		Singleton s = Singleton.getInstance();
		
		s.comm.makeConnection();
		new mainView();
		
	}

}
