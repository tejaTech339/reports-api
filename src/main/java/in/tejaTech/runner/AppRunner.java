package in.tejaTech.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.tejaTech.entity.EligibilityDtls;
import in.tejaTech.repo.EligibilityDtlsRepo;

@Component
public class AppRunner implements ApplicationRunner {

	@Autowired
	private EligibilityDtlsRepo repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		EligibilityDtls entity1=new EligibilityDtls();
		entity1.setEligibilityId(1);
		entity1.setName("John");
		entity1.setMobile(12456789L);
		entity1.setGender('M');
		entity1.setSsn(987766L);
		entity1.setPlanName("SNAP");
		entity1.setPlanStatus("Success");
		
		repo.save(entity1);
		
		EligibilityDtls entity2=new EligibilityDtls();
		entity2.setEligibilityId(2);
		entity2.setName("Alice");
		entity2.setMobile(456789L);
		entity2.setGender('F');
		entity2.setSsn(779966L);
		entity2.setPlanName("MEDICO");
		entity2.setPlanStatus("Denied");
		
		repo.save(entity2);
		
		EligibilityDtls entity3=new EligibilityDtls();
		entity3.setEligibilityId(3);
		entity3.setName("Mia");
		entity3.setMobile(893456L);
		entity3.setGender('M');
		entity3.setSsn(345966L);
		entity3.setPlanName("CCAP");
		entity3.setPlanStatus("Closed");
		
		repo.save(entity3);
		
	}

}
