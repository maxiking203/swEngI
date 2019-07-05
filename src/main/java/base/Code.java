package base;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.RandomStringUtils;

@Entity
public class Code {
	
	@Id
	private String mail;
	private String code;
	
	
	public Code() {
		
	}
	
	public Code(String mail) {
		code = generateCode();
		this.mail = mail;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	private String generateCode() {
		return RandomStringUtils.random(8, true, true);
	}

}
