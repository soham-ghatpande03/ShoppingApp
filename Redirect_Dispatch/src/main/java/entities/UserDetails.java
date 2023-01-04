package entities;

public class UserDetails {

	String uid;
	String pass;
	String fname;
	String mname;
	String lname;
	String email;
	String cntc;
	
	
	public UserDetails() {
		// TODO Auto-generated constructor stub
	}


	public UserDetails(String uid, String pass, String fname, String mname, String lname, String email, String cntc) {
		super();
		this.uid = uid;
		this.pass = pass;
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.email = email;
		this.cntc = cntc;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getMname() {
		return mname;
	}


	public void setMname(String mname) {
		this.mname = mname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCntc() {
		return cntc;
	}


	public void setCntc(String cntc) {
		this.cntc = cntc;
	}


	@Override
	public String toString() {
		return "UserDetails [uid=" + uid + ", pass=" + pass + ", fname=" + fname + ", mname=" + mname + ", lname="
				+ lname + ", email=" + email + ", cntc=" + cntc + "]";
	}
	

}
