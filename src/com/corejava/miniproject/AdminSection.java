//package com.corejava.miniproject.AccountantSection;
package com.corejava.miniproject;
import java.io.*;
import java.util.*;
class Admin implements Serializable
{
	int id;
	String name, password, email, contactno;
	Admin(int id, String name, String password, String email, String contactno)
	{
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.contactno = contactno;
	}
	public String toString()
	{
		return id+" "+name+" "+password+" "+email+" "+contactno;
	}
}
public class AdminSection
{
	public static void main(String[] args)throws IOException, ClassNotFoundException
	{
		int choice = -1;
		Scanner s = new Scanner(System.in);
		Scanner s1 = new Scanner(System.in);
		File file = new File("AdminSection.txt");
		ArrayList<Admin> al = new ArrayList<Admin>();
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		ListIterator li = null;
		
		if(file.isFile())
		{
			ois = new ObjectInputStream(new FileInputStream(file));
			al = (ArrayList<Admin>)ois.readObject();
			ois.close();
		}	
		
		do{
			System.out.println("*************** Admin Section ***************");
			System.out.println("1.Add Accountant ");
			System.out.println("2.View Accountant ");
			System.out.println("3.Logout ");
			System.out.println("\n Enter your choice: ");
			choice = s.nextInt();
			
			switch(choice)
			{
				case 1:
					System.out.println("************* Add Accountant *************");
					System.out.println("Enter how many Accountant you want to add : ");
					int n = s.nextInt();
					for(int i=0;i<n;i++)
					{
						System.out.print("Id :");
						int id = s.nextInt();
						System.out.print("Name : ");
						String name = s1.nextLine();
						System.out.print("Password : ");
						String password = s1.nextLine();
						System.out.print("Email : ");
						String email = s1.nextLine();
						System.out.print("Contact No : ");
						String contactno = s1.nextLine();
					
						al.add(new Admin(id,name,password,email,contactno));
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(al);
						oos.close();
					 }
					break;
					
				case 2:
					if(file.isFile())
					{
						ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<Admin>)ois.readObject();
						ois.close();
						
						System.out.println("************* View Accountant *************");
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
		}while(choice!=3);
	}

}
