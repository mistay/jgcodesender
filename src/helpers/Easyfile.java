package helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Easyfile {
	public static String getFileContents(String filename) {
		String ret = null;
		try {
			InputStream ips = new FileInputStream(filename);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			while ((line = br.readLine()) != null) {
				ret += line + "\n";
			}
			br.close();
		} catch (FileNotFoundException fnfe) {
			return null;
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return ret;
	}

	public static void write(String filename, String filecontents) {
		try {
			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter fileOut = new PrintWriter(bw);
			fileOut.println(filecontents);
			fileOut.close();
			bw.close();
			fw.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
