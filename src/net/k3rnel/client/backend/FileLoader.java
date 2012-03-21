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
package net.k3rnel.client.backend;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A simple file loader to make our lives easier
 * @author ZombieBear
 *
 */
public class FileLoader {
	
	/**
	 *  Loads a file as an InputStream
	 * @param path
	 * @return an InputStream of a file
	 * @throws FileNotFoundException 
	 */
	public static InputStream loadFile(String path) throws FileNotFoundException {
		return new FileInputStream(path);
	}
	
	/**
	 * Loads a text file and gets it ready for parsing
	 * @param path
	 * @return a BufferedReader for a text file
	 * @throws FileNotFoundException 
	 */
	public static BufferedReader loadTextFile(String path) throws FileNotFoundException {
		return new BufferedReader(new InputStreamReader(loadFile(path)));
	}
}
