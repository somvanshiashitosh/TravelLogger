package mypack;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ManagedBean(name="student")
@SessionScoped
@Entity
public class student {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int rollno;
	String name;
	String emailid;
	
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	@Override
	public String toString() {
		return "student [rollno=" + rollno + ", name=" + name + ", emailid=" + emailid + "]";
	}
	
	public String savingData()
	{
		databaseMechanism databasemechanism= new databaseMechanism();
		int rollNo=(int)databasemechanism.add(this);
		if(rollNo!=0)
		{
			this.setRollno(rollNo);
			System.out.println(this);
			return "RegistrationDone";
		}
		else
		{
			return "fail";
		}
	}
	
	public String getData()
	{
		databaseMechanism databasemechanism= new databaseMechanism();
		student student=(student)databasemechanism.get(this.rollno);
		if(student==null)
		{
			return "fail";
		}
		else
		{
			this.setRollno(student.getRollno());
			this.setName(student.getName());
			this.setEmailid(student.getEmailid());
			return "makeChanges";
		}
			
	}
	public String update()
	{
		databaseMechanism databasemechanism= new databaseMechanism();
		student student=new student();
		student.setRollno(this.rollno);
		student.setName(this.name);
		student.setEmailid(this.emailid);
		if(databasemechanism.updateData(student))
		return "edited";
		
		return "fail";
	}
}