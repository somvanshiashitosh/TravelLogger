package mypack;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class databaseMechanism {
	
	Session session;
	Transaction transaction;
	databaseMechanism()
	{
		session= new Configuration().configure().buildSessionFactory().openSession();
		transaction=session.beginTransaction();
	}
	
	public Integer add(student s)
	{
		Integer rn=(Integer) session.save(s);
		transaction.commit();
		session.close();
		return rn;
	}
	
	public student get(int rollNo)
	{
		student student=null;
		try
		{
		 student= (student)session.get(student.class,rollNo);
		}
		catch(Throwable e)
		{
			System.out.println("exception occurred"+e);
			return student;
		}
		return student;
	}
	
	public boolean updateData(student student)
	{
		student persistedStudent= (student)session.get(student.class,student.rollno);
		persistedStudent.setName(student.name);
		persistedStudent.setEmailid(student.emailid);
		Integer rollNo=(Integer)session.save(persistedStudent);
		transaction.commit();
		session.close();
		if(rollNo==student.rollno)
			return true;
	 return false;
	}

}
