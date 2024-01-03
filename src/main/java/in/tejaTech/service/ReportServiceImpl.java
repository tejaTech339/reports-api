package in.tejaTech.service;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import in.tejaTech.entity.EligibilityDtls;
import in.tejaTech.repo.EligibilityDtlsRepo;
import in.tejaTech.request.SearchRequest;
import in.tejaTech.response.SearchRepsonse;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private EligibilityDtlsRepo dtlsRepo;

	@Override
	public List<String> getUniquePlanNames() {
      /*
		Set<String> planNames=new HashSet<>();
		List<EligibilityDtls> findAll = dtlsRepo.findAll();
		for(EligibilityDtls e:findAll) {
			planNames.add(e.getPlanName());
		}
		return planNames;
		    Time waste and memory waste. 
		    Thats why we are developed a custom method in Repo class with static Query.
		*/	
		return dtlsRepo.findPlanNames();
	}

	@Override
	public List<String> getUniquePlanStatuses() {

		return dtlsRepo.findPlanStatuses();
	}

	@Override
	public List<SearchRepsonse> search(SearchRequest request) {

		List<SearchRepsonse> response=new ArrayList<>();
		
		/* Search will be depends on User selection based on search, 
		   we need t retrieve the data.
           We are implementing Query-By-Search or 
           we can also use #CriteriaBuilder# of Data-JPA
		*/
		EligibilityDtls queryBuilder=new EligibilityDtls();
		
		String planName = request.getPlanName();
		if(planName!=null && !planName.equals("")) {
			queryBuilder.setPlanName(planName);
		}
		
		String planStatus = request.getPlanStatus();
		if(planStatus!=null && !planStatus.equals("")) {
			queryBuilder.setPlanStatus(planStatus);
		}
		
		LocalDate planStartDate = request.getPlanStartDate();
		if(planStartDate!=null) {
			queryBuilder.setPlanStartDate(planStartDate);
		}
		
		LocalDate planEndDate = request.getPlanEndDate();
		if(planEndDate!=null) {
			queryBuilder.setPlanEndDate(planEndDate);
		}
		//interface of Data-JPA 
		Example<EligibilityDtls> example=Example.of(queryBuilder);
		
		//===========Query-By-Search========================
		
		List<EligibilityDtls> entities = dtlsRepo.findAll(example);
		
		for(EligibilityDtls entity: entities) {
			
			SearchRepsonse sr=new SearchRepsonse();
			
			BeanUtils.copyProperties(entity, sr);
			
			response.add(sr);
		}
		return response;
	}

	@Override
	public void generateExcel(HttpServletResponse response) throws IOException {

		List<EligibilityDtls> entities = dtlsRepo.findAll();
		/*
		 *  Excel-Steps:
		    ===========
		    1.creating an instance of HSSFWorkbook class  
	          HSSFWorkbook workbook = new HSSFWorkbook();
	          
	        2.invoking creatSheet() method and passing the name of the sheet to be created   
              HSSFSheet sheet = workbook.createSheet();
            
            3.creating the 0th row using the createRow() method  
	          HSSFRow rowhead = sheet.createRow((short)0);  
	          
	        4.creating cell by using the createCell() method and 
	          setting the values to the cell by using the setCellValue() method 
	          ex:
	             rowhead.createCell(0).setCellValue("S.No."); 
	             rowhead.createCell(1).setCellValue("Name");

		 */
		HSSFWorkbook workBook=new HSSFWorkbook();
		HSSFSheet sheet = workBook.createSheet();
		HSSFRow headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Mobile");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("SSN");
		
		
		for(EligibilityDtls entity:entities) {
			
			int i=1;
			/*
			 *  > i=1 bcoz header row having the zero index.
			 *  > So the data rows starts from the first-index only.
			 */
			
			// Inserting the row data.
			HSSFRow rowData = sheet.createRow(i);
			
			rowData.createCell(0).setCellValue(entity.getName());
			rowData.createCell(1).setCellValue(entity.getMobile());
			rowData.createCell(2).setCellValue(entity.getGender());
			rowData.createCell(3).setCellValue(entity.getSsn());
			
			i++;
		}
		
		ServletOutputStream outputStream=response.getOutputStream();
		workBook.write(outputStream);
		workBook.close();
		outputStream.close();
		
	}

	@Override
	public void generatePdf(HttpServletResponse response) throws Exception {

		    List<EligibilityDtls> entities = dtlsRepo.findAll();
		
		    Document document=new Document(PageSize.A4);
		
			PdfWriter.getInstance(document,response.getOutputStream());
			
			document.open();
			
			Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			font.setSize(18);
			font.setColor(Color.BLUE);
			
			Paragraph paragraph=new Paragraph("Search Report",font);
			paragraph.setAlignment(Paragraph.ALIGN_CENTER);
			
			document.add(paragraph);
			
			//Pending
			
			
		
	}
}

