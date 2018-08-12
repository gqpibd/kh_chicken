package addDb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//CREATE TABLE LOADNAME_ADD(
//		LOAD VARCHAR2(80),	
//		SIDO VARCHAR2(20),
//		SIGUNGU VARCHAR2(20),
//		EUBMEONDONG VARCHAR2(20)
//	);
public class AddDB {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(
					new FileReader(new File("D:\\JavaWorkspace_google\\semiProject\\addall.txt")));
			String buf = br.readLine();
			System.out.println(buf);
			String load = buf.split("\\|")[1];
			String sido = buf.split("\\|")[4];
			String sigungu = buf.split("\\|")[6];
			String eubmeondong = buf.split("\\|")[8];
			System.out.println(load + sido + sigungu + eubmeondong);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
