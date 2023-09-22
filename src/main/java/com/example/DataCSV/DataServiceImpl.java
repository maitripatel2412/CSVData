package com.example.DataCSV;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.opencsv.CSVReader;

@Service
//@Transactional
public class DataServiceImpl {

	@Autowired
	private DataRepository dataRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	private static final CsvMapper mapper = new CsvMapper();

	public void parseCsv(MultipartFile file) throws Exception {

		try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
			String[] nextLine;
			int i = 0;
			while ((nextLine = reader.readNext()) != null) {

				if (i != 0) {
					crateDataDto(nextLine);
				}
				i++;
			}
		}
	}

	public void crateDataDto(String[] nextLine) {

		List<Data> dtoData = new ArrayList<>();

		Data data = new Data();

		data.setEndYear(Integer.parseInt(nextLine[0]));

		if (!checkStringIsEmpty(nextLine[1])) {
			data.setCitylng(Float.parseFloat(nextLine[1]));
		}

		if (!checkStringIsEmpty(nextLine[2])) {
			data.setCitylat(Double.parseDouble(nextLine[2]));
		}

		if (!checkStringIsEmpty(nextLine[3])) {
			data.setIntensity(Integer.parseInt(nextLine[3]));
		}

		data.setSector(nextLine[4]);

		data.setTopic(nextLine[5]);

		data.setInsight(nextLine[6]);

		data.setSwot(nextLine[7]);

		data.setUrl(nextLine[8]);

		data.setRegion(nextLine[9]);

		if (!checkStringIsEmpty(nextLine[10])) {
			data.setStartYear(Integer.parseInt(nextLine[10]));
		}

		if (!checkStringIsEmpty(nextLine[11])) {
			data.setImpact(Integer.parseInt(nextLine[11]));
		}

		data.setAdded(nextLine[12]);

		data.setPublished((nextLine[13]));

		data.setCity(nextLine[14]);

		data.setCountry(nextLine[15]);

		if (!checkStringIsEmpty(nextLine[16])) {
			data.setRelevance(Integer.parseInt(nextLine[16]));
		}

		data.setPestle(nextLine[17]);

		data.setSource(nextLine[18]);

		data.setTitle(nextLine[19]);

		if (!checkStringIsEmpty(nextLine[20])) {
			data.setLikelihood(Integer.parseInt(nextLine[20]));
		}

		System.err.println(" hellooooo   END ");

		dataRepository.save(data);

	}

	public Boolean checkStringIsEmpty(String data) {
		if (!data.equalsIgnoreCase("")) {
			return false;
		}
		return true;
	}

	public List<Data> getAll() {
		
		List<Data> dataList = dataRepository.findAll();
		List<Data> List = new ArrayList<>();
		
		for(Data datas : dataList) {
			Data data1 = modelMapper.map(datas, Data.class);
			List.add(data1);
		}
		return List;
	}

}
