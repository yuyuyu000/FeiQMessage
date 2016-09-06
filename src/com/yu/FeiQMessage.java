package com.yu;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class FeiQMessage {
	private static String packet = "1_lbt4_10#32899#002481627512#0#0#0:1289671407:��������:666666-666666:288:";

	public static void main(String[] args) throws Exception {

		// sendMessage(getMyIpAddress() , "hello,world!" ); //���Լ�������Ϣ����
		sendMessage("192.168.54.2", "hello,world!");
		
//		killFeiQ("192.168.54.2", "hello,world!");
	}

	/**
	 * 
	 * @param ipAddress
	 *            Ŀ��ip��ַ
	 * @param message
	 *            ��Ŀ��ip���͵���Ϣ
	 */
	public static void sendMessage(String ipAddress, String message) {
		// �ɸ뱨��

		String msg = packet + message;

		try {
			byte[] buf = msg.getBytes("GBK");

			InetAddress addr = InetAddress.getByName(ipAddress);
			int port = 2425;
			DatagramPacket send = new DatagramPacket(buf, buf.length, addr, port);

			DatagramSocket client = new DatagramSocket();
			client.send(send);
			// client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * ��ȡ����ip��ַ
	 * 
	 * @return ���ػ�ȡ��ip
	 */
	public static String getMyIpAddress() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString(); // ��ȡ����ip
			// System.out.println("����IP��" + ip);
			return ip;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �ظ����ʹ�����Ϣ,��ɷ��￨��
	 * 
	 * @param ipAddress
	 * @param message
	 */
	public static void killFeiQ(String ipAddress, String message) {
		for (int i = 0; i < 100; i++) {
			sendMessage(ipAddress, message);
		}
	}

}
