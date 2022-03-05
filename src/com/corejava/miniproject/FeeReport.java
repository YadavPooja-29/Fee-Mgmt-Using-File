package com.corejava.miniproject;
import java.io.*;
import java.util.*;

class AccountantLogin implements Serializable
{
	String nm, pass;
	AccountantLogin(String nm, String pass)
	{
		this.nm = nm;
		this.pass = pass;
	}
	public String toString()
	{
		return nm+" "+pass;
	}
}
public class FeeReport
{
	public static void main(String[] args)throws IOException, ClassNotFoundException
	{
		int choice = -1;
		Scanner s = new Scanner(System.in);
		Scanner s1 = new Scanner(System.in);
		File file = new File("AccountantLogin.txt");
		ArrayList<AccountantLogin> al = new ArrayList<AccountantLogin>();
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		ListIterator li = null;

		
		if(file.isFile())
		{
			ois = new ObjectInputStream(new FileInputStream(file));
			al = (ArrayList<AccountantLogin>)ois.readObject();
			ois.close();
		}
		
		do{
			System.out.println("*************** Fee Report ***************");
			System.out.println("1.Admin Login ");
			System.out.println("2.Accountant Login ");
			System.out.println("3.View Accountant Login Details ");
			System.out.println("4.Logout ");
			System.out.println("\n Enter your choice: ");
			choice = s.nextInt();
			
			switch(choice)
			{
				case 1:
					System.out.println("************* Admin Login *************");
					System.out.print("Name : ");
					String name = s1.nextLine();
					System.out.print("Password : ");
					String password = s1.nextLine();
					if(name.equals("admin")&& password.equals("admin123"))
					{
						System.out.println("Admin Login Successfull");
					}
					else
					{
						System.out.println("Please provide correct credential");
					}						
					break;
					
				case 2:
					System.out.println("************* Accountant Login *************");
					System.out.print("Name : ");
					String nm = s1.nextLine();
					System.out.print("Password : ");
					String pass = s1.nextLine();
					
					System.out.println("Accountant Login Successfull");
					
					al.add(new AccountantLogin(nm,pass));
					oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(al);
					oos.close();
					
					break;
					
				case 3:
					if(file.isFile())
					{
						ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<AccountantLogin>)ois.readObject();
						ois.close();
						
						System.out.println("************* View Accountant Login Details *************");
						li = al.listIterator();
						while(li.hasNext())
							System.out.println(li.next());
						System.out.println("--------------------------------------------");
					}
					else
					{
						System.out.println("File not Exist.....!");
					}
					break;  
			}
		}while(choice!=4);
	}

}
