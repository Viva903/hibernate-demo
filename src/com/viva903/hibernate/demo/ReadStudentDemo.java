package com.viva903.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.viva903.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {

//		create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
//		create session
		Session session = factory.getCurrentSession();

		try {
//			create a student object
			System.out.println("Creating new student object");
			String theDateOfBirthStr = "23/05/1999";
			Student tempStudent = new Student("Damien", "Duffy", "damien@viva903.com", DateUtils.parseDate(theDateOfBirthStr));
			
//			start a transaction
			session.beginTransaction();
			
//			save the student object
			System.out.println("Saving the student ... ");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
//			commit transaction
			session.getTransaction().commit();
			
//			Read from Database
//			find out student id : primary key
			System.out.println("Saved student. Generated id : " + tempStudent.getId());
			
//			now get a new session and start transaction
			session =  factory.getCurrentSession();
			session.beginTransaction();
			
//			retrieve student based on the id : primary key
			System.out.println("\nGetting student with id : " + tempStudent.getId());
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get complete : " + myStudent);
			
//			commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

	}

}
