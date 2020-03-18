package spring.data.mongodb.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//mongodb Collection에서 하나의 document와 매핑될 클래스

@Document(collection="score")
public class ScoreDTO {
	@Id
	String _id;
	String id;
	String name;
	String dept;
	String addr;
	int java;
	int servlet;
	
	public ScoreDTO() {
		super();
	}


	

	public ScoreDTO(String _id, String id, String name, String dept, String addr, int java, int servlet) {
		super();
		this._id = _id;
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.addr = addr;
		this.java = java;
		this.servlet = servlet;
	}




	public String get_id() {
		return _id;
	}




	public void set_id(String _id) {
		this._id = _id;
	}




	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getJava() {
		return java;
	}

	public void setJava(int java) {
		this.java = java;
	}

	public int getServlet() {
		return servlet;
	}

	public void setServlet(int servlet) {
		this.servlet = servlet;
	}
	
}
