package in.tejaTech.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="ELIGIBILITY_DETILS")
@Data
public class EligibilityDtls {
	
	@Id  //we are just retrieving data so no need for GeneratedValue.
	private Integer eligibilityId;
	private String name;
	private Long mobile;
	private String email;
	private Character gender;
	private Long ssn;
	private String planName;
	private String planStatus;
	
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	
	private LocalDate createDate;
	private LocalDate updateDate;
	private String createdBy;
	private String updatedBy;

}
