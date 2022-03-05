//package com.corejava.miniproject.AccountantSection;
package com.corejava.miniproject;
import java.io.*;
import java.util.*;
class Student implements Serializable
{
	int rno, fee, paid, due;
	String name, email, course, address, city, state, country, contactno;
	Student(int rno, String name, String email,String course, int fee, int paid, int due, String address,String city, String state, String country, String contactno)
	{
		this.rno = rno;
		this.name = name;
		this.email = email;
		this.course = course;
		this.fee = fee;
		this.paid = paid;
		this.due = due;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.contactno = contactno;
	}
	public String toString()
	{
		return rno+" "+name+" "+email+" "+course+" "+fee+" "+paid+" "+due+" "+address+" "+city+" "+state+" "+country+" "+contactno;
	}
}
public class AccountantSection
{
	public static void main(String[] args)throws IOException, ClassNotFoundException
	{
		int choice = -1;
		Scanner s = new Scanner(System.in);
		Scanner s1 = new Scanner(System.in);
		File file = new File("Student.txt");
		ArrayList<Student> al = new ArrayList<Student>();
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		ListIterator li = null;
		
		if(file.isFile())
		{
			ois = new ObjectInputStream(new FileInputStream(file));
			al = (ArrayList<Student>)ois.readObject();
			ois.close();
		}	
		
		do{
			System.out.println("*************** Accountant Section ***************");
			System.out.println("1.Add Student ");
			System.out.println("2.View Student ");
			System.out.println("3.Edit Student ");
			System.out.println("4.Due Fee ");
			System.out.println("5.Logout ");
			System.out.println("\n Enter your choice: ");
			choice = s.nextInt();
			
			switch(choice)
			{
				case 1:
					System.out.println("************* Add Student *************");
					System.out.println("Enter how many Student you want to add : ");
					int n = s.nextInt();
					for(int i=0;i<n;i++)
					{
						System.out.print("Roll No :");
						int rno = s.nextInt();
						System.out.print("Name : ");
						String name = s1.nextLine();
						System.out.print("Email : ");
						String email = s1.nextLine();
						System.out.print("Course : ");
						String course = s1.nextLine();
						System.out.print("Fee : ");
						int fee = s.nextInt();
						System.out.print("Paid : ");
						int paid = s.nextInt();
						int due = fee - paid;
						System.out.print("Due : " + due);
						//s.nextInt();
						System.out.print("\nAddress : ");
						String address = s1.nextLine();
						System.out.print("City : ");
						String city = s1.nextLine();
						System.out.print("State : ");
						String state = s1.nextLine();
						System.out.print("Country : ");
						String country = s1.nextLine();
						System.out.print("Contact No : ");
						String contactno = s1.nextLine();
					
						al.add(new Student(rno,name,email,course,fee,paid,due,address,city,state,country,contactno));
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(al);
						oos.close();
					 }
					break;
					
				case 2:
					if(file.isFile())
					{
						ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<Student>)ois.readObject();
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
					
				case 3:
					if(file.isFile())
					{
						ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<Student>)ois.readObject();
						ois.close();
												
						boolean found = false;
						System.out.println("************* (Update) Edit Student *************");
						System.out.print("Enter RollNo to Update : ");
						int rno = s.nextInt();
						System.out.println("--------------------------------------------");
						li = al.listIterator();
						while(li.hasNext())
						{
							Student stud =(Student)li.next();
							if(stud.rno == rno)
							{
								System.out.print("Enter New Name :");
								String studname = s1.nextLine();
								System.out.print("Enter New Email : ");
								String studemail = s1.nextLine();
								System.out.print("Enter New Course : ");
								String studcourse = s1.nextLine();
								System.out.print("Enter New Fee : ");
								int studfee = s.nextInt();
								System.out.print("Enter New Paid : ");
								int studpaid = s.nextInt();
								int studdue = studfee - studpaid;
								System.out.print("Enter New Due : " +studdue);
								//s.nextInt();
								System.out.println("\nEnter New Address : ");
								String studaddress = s1.nextLine();
								System.out.print("Enter New City : ");
								String studcity = s1.nextLine();
								System.out.print("Enter New State : ");
								String studstate = s1.nextLine();
								System.out.print("Enter New Country : ");
								String studcountry = s1.nextLine();
								System.out.print("Enter New Contact No : ");
								String studcontactno = s1.nextLine();
								li.set(new Student(rno, studname, studemail, studcourse, studfee, studpaid, studdue,studaddress, studcity, studstate, studcountry, studcontactno));
								found = true;
							}
						}
						if(found)
						{
							oos = new ObjectOutputStream(new FileOutputStream(file));
							oos.writeObject(al);
							oos.close();
							System.out.println("Record Updated Successfully....!");
						}
						else
						{
							System.out.println("Record Not Found.....!");
						}
						System.out.println("------------------------------------------------------------");
					}
					else
					{
						System.out.println("File Not Exits.....!");
					}
					break;
					
				case 4: 
					boolean found = false;
					System.out.println("************* View Due Fee *************");
					//int due = s.nextInt();
					System.out.println("------------------------------------------------------------");
					li = al.listIterator();
					while(li.hasNext())
					{
						Student stud = (Student)li.next();
						if(stud.due > 0)
						{
							System.out.println(stud);
							found = true;
						}
					}
					if(!found)
					{
						System.out.println("Record Not Found.....!");
						System.out.println("------------------------------------------------------------");
					}
					break;			
			}
		}while(choice!=5);
	}

}
