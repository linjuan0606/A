package com.phone.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.phone.dao.DianHuaBenDao;
import com.phone.pojo.Phone;

public class Main {
	/*
	 * ��ӡ��ʾ��Ϣ
	 * */
	private static void outInfo() {
		System.out.println("************************");
		System.out.println("******  1.��ӵ绰          ******");
		System.out.println("******  2.ɾ���绰          ******");
		System.out.println("******  3.�޸ĵ绰          ******");
		System.out.println("******  4.��ѯ�绰          ******");
		System.out.println("******  ����exit�˳�    ******");
		System.out.println("************************");
	}
	/*
	 * ��ӡ��ѯ�绰��ʾ��Ϣ
	 * */
	private static void outQuerywhereInfo() {
		System.out.println("************************");
		System.out.println("******  1.������ѯ           ******");
		System.out.println("******  2.�绰��ѯ           ******");
		System.out.println("******  3.ID��ѯ             ******");
		System.out.println("******  4.ȫ���ѯ            ******");
		System.out.println("****** exit������һ��     ******");
		System.out.println("*************************");
	}
	static Scanner scanner = new Scanner(System.in);
	static String temp = "";
	static DianHuaBenDao dianHuaBenDao = new DianHuaBenDao();
	public static void main(String[] args) {
		try {
			while (true) {
				outInfo();
				temp = scanner.next();
				if ("exit".equalsIgnoreCase(temp)) {
					break;
				}
				if ("1".equals(temp)) {
					if (addPhone()) {
						System.out.println("����ɹ�!");
					}else {
						System.out.println("����ʧ�ܣ�");
					}
				}else if ("2".equals(temp)) {
					
				}else if ("3".equals(temp)) {
					
				}else if ("4".equals(temp)) {
					query();
				}else {
					System.out.println("�޷�ʶ��������������Ϣ��");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * ��ѯ
	 * */
	public static void query() throws SQLException {
		while (true) {
			outQuerywhereInfo();
			temp = scanner.next();
			if ("exit".equalsIgnoreCase(temp)) {
				break;
			}
			if ("1".equals(temp)) {
				System.out.println("������Ҫ��ѯ�����֣�");
				temp = scanner.next();
				whereQuery("select * from dianhuaben where name ='"+temp+"'");
			}else if ("2".equals(temp)) {
				temp = scanner.next();
				whereQuery("select * from dianhuaben where phone ='"+temp+"'");
			}else if ("3".equals(temp)) {
				temp = scanner.next();
				whereQuery("select * from dianhuaben where id ="+temp);
			}else if ("4".equals(temp)) {
				allQuery();
			}else {
				System.out.println("�޷�ʶ��,������������Ϣ��");
			}
			
		}
	}
	/*
	 * ������ѯ
	 * */
	private static void whereQuery(String tempPhone) throws SQLException {
		// TODO Auto-generated method stub
		List<Phone> phoneList = dianHuaBenDao.query(tempPhone);
		System.out.println("ID\t����\t�绰");
		for (Phone phone : phoneList) {
			System.out.println(phone.getId()+"\t"+phone.getName()+"\t"+phone.getPhone());
		}
	}
	/*
	 * ȫ���ѯ
	 * */
	private static void allQuery() throws SQLException {
		// TODO Auto-generated method stub
		List<Phone> phoneList = dianHuaBenDao.query("select * from dianhuaben");
		System.out.println("ID\t����\t�绰");
		for (Phone phone : phoneList) {
			System.out.println(phone.getId()+"\t"+phone.getName()+"\t"+phone.getPhone());
		}
	}
	/*
	 * ��ӵ绰
	 * */
	public static boolean addPhone() throws SQLException {
		System.out.println("������绰��Ϣ-->����   �绰��ps������\",\"�ָ���");
		String[] phones = null;
		while (true) {
			temp = scanner.next();
			phones = temp.split(",");
			if (phones.length ==2) {
				break;
			}
			System.out.println("������Ϣ�������������룡");
		}
		Phone phone = new Phone();
		phone.setName(phones[0]);
		phone.setPhone(phones[1]);
		return dianHuaBenDao.add(phone);
	}
	
	
}
