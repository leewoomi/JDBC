package vo;

public class Member {
protected String email;
protected String pw;
protected String name;
protected String phone;
protected String addr;
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPw() {
	return pw;
}
public void setPw(String pw) {
	this.pw = pw;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getAddr() {
	return addr;
}
public void setAddr(String addr) {
	this.addr = addr;
}
@Override
public String toString() {
	return "Member [email=" + email + ", pw=" + pw + ", name=" + name + ", phone=" + phone + ", addr=" + addr + "]";
}

}
