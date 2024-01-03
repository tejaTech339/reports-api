package in.tejaTech.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.tejaTech.entity.EligibilityDtls;

@Repository
public interface EligibilityDtlsRepo extends JpaRepository<EligibilityDtls, Integer> {

	//below are static queries
	@Query("select distinct(planName) from EligibilityDtls")
	public List<String> findPlanNames();
	
	@Query("select distinct(planStatus) from EligibilityDtls")
	public List<String> findPlanStatuses();
}
