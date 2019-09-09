package br.com.senaigo.wso2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
	
	private int number;
	private String lastName;
	private String firstName;
	private String phone;
	private String city;
	private String street;
	private String country;

//	static String NUMBER = "number";
//	static String LAST_NAME = "last_name";
//	static String FIRST_NAME = "first_name";
//	static String PHONE = "phone";
//	static String CITY = "city";
//	static String STREET = "street";
//	static String COUNTRY = "country";
}
