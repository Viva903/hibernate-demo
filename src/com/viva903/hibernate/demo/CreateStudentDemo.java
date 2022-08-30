package com.viva903.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.viva903.hibernate.demo.entity.Student;

public class CreateStudentDemo {

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
			Student tempStudent = new Student("Paul", "Walker", "paul@viva903.com", DateUtils.parseDate(theDateOfBirthStr));

//			start a transaction
			session.beginTransaction();

//			save the student object
			System.out.println("Saving the student ... ");
			session.save(tempStudent);

//			commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

	}

}
