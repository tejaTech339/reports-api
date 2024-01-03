package in.tejaTech.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.tejaTech.request.SearchRequest;
import in.tejaTech.response.SearchRepsonse;
import in.tejaTech.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportRestController {

	@Autowired
	private ReportService service;
	
	@GetMapping("/plans")
	public ResponseEntity<List<String>> getPlanNames(){
		
		List<String> planNames = service.getUniquePlanNames();
		
		return new ResponseEntity<>(planNames,HttpStatus.OK);
	}
	
	@GetMapping("/statuses")
	public ResponseEntity<List<String>> getPlanStatuses(){
		
		List<String> planStatuses = service.getUniquePlanStatuses();
		
		return new ResponseEntity<>(planStatuses,HttpStatus.OK);
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<SearchRepsonse>> search(@RequestBody SearchRequest request){
		
		List<SearchRepsonse> response = service.search(request);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/excel")
	public void excelReport(HttpServletResponse response) throws IOException {
		
		// HttpServletResponse response why ? bcoz Server sending directly file to client
		
		response.setContentType("application/octec-stream");
		
		String headerKey="Contenet-Disposition";
		String headerValue="attachment;filename=data.xls";
		
		response.setHeader(headerKey, headerValue);
		
		service.generateExcel(response);
	}
	
	@GetMapping("/pdf")
	public void pdfReport(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/pdf");
		
		String headerKey="Contenet-Disposition";
		String headerValue="attachment;filename=data.pdf";
		
		response.setHeader(headerKey, headerValue);
		
		service.generatePdf(response);
	}
}
