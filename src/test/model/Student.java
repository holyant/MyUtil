package test.model;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student
{
	private int id;
	private String name;
	private int age;
	private Date birth;
	private String address;
	private String email;

	
	public Student()
	{
	}

	public Student(int id, String name, int age, Date birth)
	{
		this.id = id;
		this.name = name;
		this.age = age;
		this.birth = birth;
	}
	

	@Override
	public String toString() {
		return this.name+"#"+this.age+"#"+this.id+"#"+this.birth;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getBirth()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.birth);
	}

	public void setBirth(Date birth)
	{
		this.birth = birth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
