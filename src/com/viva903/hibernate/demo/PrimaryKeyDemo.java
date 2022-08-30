package com.viva903.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.viva903.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

//		create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
//		create session
		Session session = factory.getCurrentSession();

		try {
//			create 3 student objects
			System.out.println("Creating 3 student objects");
			String theDateOfBirthStr = "23/05/1999";
			Student tempStudent1 = new Student("Antony", "Walker", "Antony@viva903.com",
					DateUtils.parseDate(theDateOfBirthStr));
			Student tempStudent2 = new Student("Dean", "Walker", "dean@viva903.com",
					DateUtils.parseDate(theDateOfBirthStr));
			Student tempStudent3 = new Student("David", "Walker", "david@viva903.com",
					DateUtils.parseDate(theDateOfBirthStr));

//			start a transaction
			session.beginTransaction();

//			save the student object
			System.out.println("Saving the students ... ");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);

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
