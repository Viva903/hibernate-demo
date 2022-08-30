package com.viva903.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.viva903.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

//		create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
//		create session
		Session session = factory.getCurrentSession();

		try {

//			start a transaction
			session.beginTransaction();

//			Query the students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			displayStudents(theStudents);

//			query Students : lastname = 'Walker'
			theStudents = session.createQuery("from Student s where s.lastName = 'Walker'").getResultList();
//			Display the students
			System.out.println("\n\nStudents who have the last name of Walker");
			displayStudents(theStudents);

//			query Students : lastname = 'Duffy' OR firstName = 'Antony'
			theStudents = session.createQuery("from Student s where s.lastName = 'Duffy' OR s.firstName = 'Antony'")
					.getResultList();
//			Display the students
			System.out.println("\n\nStudents who have the last name of Duffy or first name of Antony");
			displayStudents(theStudents);

//			query Students where email like %viva903.com
			theStudents = session.createQuery("from Student s where s.email LIKE '%viva903.com'").getResultList();
//			Display the students
			System.out.println("\n\nStudents who emails end with viva903.com");
			displayStudents(theStudents);
			
			
			
//			commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");

		} finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
