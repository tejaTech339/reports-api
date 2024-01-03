package in.tejaTech.response;

import lombok.Data;

@Data
public class SearchRepsonse {

	private String name;
	private Long mobile;
	private String email;
	private Character gender;
	private Long ssn;
	private String planName;
	private String planStatus;
}
