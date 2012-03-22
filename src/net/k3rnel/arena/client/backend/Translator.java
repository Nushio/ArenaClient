/**
 * This file is part of Distro Wars (Client).
 * 
 *  Distro Wars (Client) is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  Distro Wars (Client) is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with Distro Wars (Client).  If not, see <http://www.gnu.org/licenses/>.
*/
package net.k3rnel.arena.client.backend;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.k3rnel.arena.client.GameClient;

public class Translator {
	private static Translator m_instance;
	
	/**
	 * Returns a list of translated text
	 * @param filename
	 * @return
	 */
	public List<String> translateText(String filename) {
		List<String> translated = new ArrayList<String>();
		String respath = System.getProperty("res.path");
		if(respath==null)
			respath="";
		try {
			String path = respath+"res/language/" + GameClient.getLanguage() + "/UI/" + filename + ".txt";
			InputStream in = new FileInputStream(path);
			if(in != null) {
				BufferedReader f = new BufferedReader(new InputStreamReader(in));
				Scanner reader = new Scanner(f);
				while(reader.hasNextLine()) {
					translated.add(reader.nextLine().replaceAll("/n", "\n"));
				}
				/*if(translated.size()==0){
					FileInputStream fis = new FileInputStream(f);
					BufferedInputStream bis = new BufferedInputStream(fis);
					DataInputStream dis = new DataInputStream(bis);
					 while (dis.available() != 0) {
						 // this statement reads the line from the file
						 translated.add(dis.readLine());
					 }
					 fis.close();
					 bis.close();
					 dis.close();
				}*/
			}else{ //In case of emergencies, load english!
				try{
					in = new FileInputStream(respath+"res/language/english/UI/" + filename + ".txt");
					BufferedReader f = new BufferedReader(new InputStreamReader(in));
					Scanner reader = new Scanner(f);
					while(reader.hasNextLine()) {
						translated.add(reader.nextLine().replaceAll("/n", "\n"));
					}
				}catch(Exception e){
					translated.add("/n"); //If there's no english, display default line. 
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return translated;
	}
	
	/**
	 * Returns the instance of translator
	 * @return
	 */
	public static Translator getInstance() {
		if(m_instance == null)
			m_instance = new Translator();
		return m_instance;
	}
	
	public static List<String> translate(String filename){
		return Translator.getInstance().translateText(filename);
	}
}
