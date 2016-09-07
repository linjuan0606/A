package com.phone.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.phone.dao.DianHuaBenDao;
import com.phone.pojo.Phone;

public class Main {
	/*
	 * 打印提示信息
	 * */
	private static void outInfo() {
		System.out.println("************************");
		System.out.println("******  1.添加电话          ******");
		System.out.println("******  2.删除电话          ******");
		System.out.println("******  3.修改电话          ******");
		System.out.println("******  4.查询电话          ******");
		System.out.println("******  输入exit退出    ******");
		System.out.println("************************");
	}
	/*
	 * 打印查询电话提示信息
	 * */
	private static void outQuerywhereInfo() {
		System.out.println("************************");
		System.out.println("******  1.姓名查询           ******");
		System.out.println("******  2.电话查询           ******");
		System.out.println("******  3.ID查询             ******");
		System.out.println("******  4.全表查询            ******");
		System.out.println("****** exit返回上一级     ******");
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
						System.out.println("插入成功!");
					}else {
						System.out.println("插入失败！");
					}
				}else if ("2".equals(temp)) {
					
				}else if ("3".equals(temp)) {
					
				}else if ("4".equals(temp)) {
					query();
				}else {
					System.out.println("无法识别，请输入如下信息：");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 查询
	 * */
	public static void query() throws SQLException {
		while (true) {
			outQuerywhereInfo();
			temp = scanner.next();
			if ("exit".equalsIgnoreCase(temp)) {
				break;
			}
			if ("1".equals(temp)) {
				System.out.println("输入需要查询的名字：");
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
				System.out.println("无法识别,请输入如下信息：");
			}
			
		}
	}
	/*
	 * 条件查询
	 * */
	private static void whereQuery(String tempPhone) throws SQLException {
		// TODO Auto-generated method stub
		List<Phone> phoneList = dianHuaBenDao.query(tempPhone);
		System.out.println("ID\t姓名\t电话");
		for (Phone phone : phoneList) {
			System.out.println(phone.getId()+"\t"+phone.getName()+"\t"+phone.getPhone());
		}
	}
	/*
	 * 全表查询
	 * */
	private static void allQuery() throws SQLException {
		// TODO Auto-generated method stub
		List<Phone> phoneList = dianHuaBenDao.query("select * from dianhuaben");
		System.out.println("ID\t姓名\t电话");
		for (Phone phone : phoneList) {
			System.out.println(phone.getId()+"\t"+phone.getName()+"\t"+phone.getPhone());
		}
	}
	/*
	 * 添加电话
	 * */
	public static boolean addPhone() throws SQLException {
		System.out.println("请输入电话信息-->姓名   电话（ps：请以\",\"分隔）");
		String[] phones = null;
		while (true) {
			temp = scanner.next();
			phones = temp.split(",");
			if (phones.length ==2) {
				break;
			}
			System.out.println("输入信息有误，请重新输入！");
		}
		Phone phone = new Phone();
		phone.setName(phones[0]);
		phone.setPhone(phones[1]);
		return dianHuaBenDao.add(phone);
	}
	
	
}
