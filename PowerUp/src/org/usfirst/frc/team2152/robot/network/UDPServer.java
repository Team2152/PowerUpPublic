package org.usfirst.frc.team2152.robot.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * UDP Server, to be used as a listener/receiver with response capabilities
 * @author David
 */
public class UDPServer extends Thread {

	private final int bufferSize = 64;

	private DatagramSocket sock = null;
	private UDPListener listener = null;

	private Object lock;

	private InetAddress returnAddr;
	private int returnPort;

	/**
	 * Initialize the UDP Server with no listener -- meant to be 
	 * used with receive method in a procedural style
	 * @param addr
	 * @param port
	 */
	public UDPServer(String addr, int port){
		this(port, null);
	}

	/**
	 * Initialize the UDP Server with a receive callback
	 * @param port The port to bind to
	 * @param listener The receive callback -- when a packet "bytes" is received, 
	 * the server will call listener.packetReceived(bytes)
	 */
	public UDPServer(int port, UDPListener listener){
		try {
			sock = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.listener = listener;
	}

	/**
	 * Waits for a message to be sent to the server.
	 * @param timeout The receive timeout
	 * @return The packet received
	 * @throws SocketTimeoutException
	 */
	public byte[] receive(int timeout) throws SocketTimeoutException {
		if(listener != null) 
			return new byte[]{}; //no
		byte[] buffer = new byte[bufferSize];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

		try {
			sock.setSoTimeout(timeout);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}

		try {
			sock.receive(packet);
		} catch (SocketTimeoutException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
		returnAddr = packet.getAddress();
		returnPort = packet.getPort();
		return packet.getData();
	}

	public void sendPacket(byte[] bytes, String addr, int port) {
		try {
			sendMsg(bytes, InetAddress.getByName(addr), port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sends a message to the  most recent client to send a message to the server
	 * @param bytes The packet to send
	 */
	public void sendBack(byte[] bytes) {
		sendMsg(bytes, returnAddr, returnPort);
	}

	public void run() {
		byte[] buffer = new byte[bufferSize];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		
		// Don't start listening for data until a listener is added
		synchronized(lock) {
			if(listener == null) {
				System.err.println("UDPReceiver has no listener; waiting until one is added");
				try {
					lock.wait(); // Blocks until lock.notify() is called on another thread
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		while(!Thread.interrupted()) {
			clear(buffer);
			try {
				sock.receive(packet);
			} catch (SocketTimeoutException e) {
				// Continue receive loop to ignore buffer data
				continue;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Notify listener of new data
			listener.packetReceived(buffer);
		}
		cleanUp();
	}

	protected void sendMsg(byte[] sendData, InetAddress addr, int port) {
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, addr, port);
		try {
			sock.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setListener(UDPListener listener) {
		if(listener != null) {
			this.listener = listener;
			synchronized(lock) {
				lock.notify();
			}
		} else {
			System.err.println("Attempted to add null listener to UDP Receiver");
		}
	}


	private void cleanUp() {
		if(sock != null) {
			sock.close();
			sock = null;
		}
	}

	private void clear(byte[] bs) {
		for(int i = 0; i < bs.length; i++) {
			bs[i] = 0;
		}
	}
}