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
	
	private int patientNumber;
	private String patientLastName;
	private String patientFirstName;
	private String phone;
	private String city;
	private String streetname;
	private String country;
}
