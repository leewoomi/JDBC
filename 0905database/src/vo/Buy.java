package vo;

public class Buy {
protected int num;
protected String userid;
protected String productname;
protected int price;
protected int amount;



public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getProductname() {
	return productname;
}
public void setProductname(String productname) {
	this.productname = productname;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
@Override
public String toString() {
	return "Buy [num=" + num + ", userid=" + userid + ", productname=" + productname + ", price=" + price + ", amount="
			+ amount + "]";
}

}
