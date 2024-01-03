package in.tejaTech.service;

import java.io.IOException;
import java.util.List;

import in.tejaTech.request.SearchRequest;
import in.tejaTech.response.SearchRepsonse;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {

	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatuses();
	
	public List<SearchRepsonse> search(SearchRequest request);
	
	public void generateExcel(HttpServletResponse response) throws IOException;
	//public HttpServletResponse generateExcel();
	
	public void generatePdf(HttpServletResponse response) throws Exception;
}
