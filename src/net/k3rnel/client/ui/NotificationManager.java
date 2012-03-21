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
package net.k3rnel.client.ui;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import mdes.slick.sui.Display;
import net.k3rnel.client.ui.base.Notification;


/**
 * Manages notifications
 * @author shadowkanji
 *
 */
public class NotificationManager implements Runnable {
	@SuppressWarnings("unused")
	private Display m_display;
	private boolean m_isRunning;
	private Thread m_thread;
	private static Queue<Notification> m_notifications;
	
	/**
	 * Default constructor
	 * @param d
	 */
	public NotificationManager(Display d) {
		m_display = d;
	}
	
	/**
	 * Called when running
	 */
	public void run() {
		while(m_isRunning) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {}
		}
	}
	
	/**
	 * Adds a new notification
	 * @param n
	 */
	public static void addNotification(String n) {
		m_notifications.add(new Notification(n));
	}
	
	/**
	 * Starts the notification manager
	 */
	public void start() {
		m_notifications = new ConcurrentLinkedQueue<Notification>();
		m_isRunning = true;
		m_thread = new Thread(this);
		m_thread.start();
	}

	/**
	 * Stops the notification manager
	 */
	public void stop() {
		m_isRunning = false;
	}
}
