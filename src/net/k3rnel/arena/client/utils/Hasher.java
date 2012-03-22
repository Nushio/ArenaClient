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
package net.k3rnel.arena.client.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Provides encryption
 * @author Nushio
 *
 */
public class Hasher {
    
    private static String convertToHex(byte[] data) {
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int abyte = (data[i] >>> 4) & 0x0F;
            int bytes = 0;
            do {
                if ((0 <= abyte) && (abyte <= 9))
                    buff.append((char) ('0' + abyte));
                else
                    buff.append((char) ('a' + (abyte - 10)));
                abyte = data[i] & 0x0F;
            } while(bytes++ < 1);
        }
        return buff.toString();
    }
    
    public String SHA1(String text) {
    		try {
    		    MessageDigest md = MessageDigest.getInstance("SHA");
    			md.update(text.getBytes(), 0, text.length());
    			return convertToHex(md.digest());
    		} catch (NoSuchAlgorithmException e) {
    			e.printStackTrace();
    			return null;
    		}
    	}
}

