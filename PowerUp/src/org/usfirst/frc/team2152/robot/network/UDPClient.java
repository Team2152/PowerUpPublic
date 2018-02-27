package org.usfirst.frc.team2152.robot.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * UDP Sender with response listening functionality
 * @author David
 *
 */
public class UDPClient {

	DatagramSocket sock;

	/**
	 * UDP Client constructor
	 * Use this if you only need to send
	 */
	public UDPClient() {
		try {
			sock = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	/**
	 * UDP Client constructor that binds to a port
	 * Use this if you need to receive responses from the server you're sending to
	 * @param port Local port to bind to
	 */
	public UDPClient(int port) {
		try {
			sock = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void send(byte[] sendData, String addr, int port) {
        DatagramPacket sendPacket;
		try {
			sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(addr), port);
            sock.send(sendPacket);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public void send(byte[] sendData, InetAddress addr, int port) {
        DatagramPacket sendPacket;
		try {
			sendPacket = new DatagramPacket(sendData, sendData.length, addr, port);
            sock.send(sendPacket);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public byte[] receiveResponse(int length, int timeout) throws SocketTimeoutException {
		byte[] buffer = new byte[length];
		DatagramPacket p = new DatagramPacket(buffer, length);
		try {
			sock.receive(p);
		} catch(SocketTimeoutException e) {
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buffer;
	}
}