package com.example.DataCSV;

import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@CrossOrigin
public class DataController {

	@Autowired
	private DataRepository dataRepository;
	
	@Autowired
	DataServiceImpl dataServiceImpl;

	@PostMapping(value = "/upload")
	public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
		try {
			dataServiceImpl.parseCsv(file);
			return ResponseEntity.ok("CSV file uploaded and data saved to the database.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
		}

	}
	
	@GetMapping("/datacsv/all")
	public List<Data> getAll(){
		return dataServiceImpl.getAll();
	}

}
