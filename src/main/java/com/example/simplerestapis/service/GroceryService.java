package com.example.simplerestapis.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.ooxml.util.SAXHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.example.simplerestapis.models.Item;

@Service
public class GroceryService {

	public List<Item> generateResults(String sometext) throws OpenXML4JException, Exception {

		return response(sometext);
	}
	public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException, ParserConfigurationException {
		XMLReader parser = SAXHelper.newXMLReader();
		ContentHandler handler = new SheetHandler(sst);
		parser.setContentHandler(handler);
		return parser;
	}
	public List<Item> response(String text) throws IOException, SAXException, OpenXML4JException, Exception {
		List<Item> itemDetails = new ArrayList<>();
		Item item = new Item();
		File filename = ResourceUtils.getFile("classpath:vegetables.xlsx");
		OPCPackage pkg = OPCPackage.open(filename);
		XSSFReader r = new XSSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();
		XMLReader parser = fetchSheetParser(sst);
		Iterator<InputStream> sheets = r.getSheetsData();
		while (sheets.hasNext()) {
			System.out.println("Processing new sheet:\n");
			InputStream sheet = sheets.next();
			InputSource sheetSource = new InputSource(sheet);
			parser.parse(sheetSource);
			sheet.close();
		}
		
//	FileInputStream file = new FileInputStream(new File("C:\\Users\\mdali\\Desktop\\CGIAssignment\\groceryassignment\\data\\vegetables.xlsx"));
//		try {
//			File file = ResourceUtils.getFile("classpath:vegetables.xlsx");
//			FileInputStream fis = new FileInputStream(file);
//			XSSFWorkbook wb = new XSSFWorkbook(fis);
//			
//			XSSFSheet sheet = wb.getSheetAt(0);
//			{
//
//				Iterator<Row> rowIterator = sheet.iterator();
//				while (rowIterator.hasNext()) {
//					Row row = rowIterator.next();
//					// For each row, iterate through all the columns
//					Iterator<Cell> cellIterator = row.cellIterator();
//
//					while (cellIterator.hasNext()) {
//						Cell cell = cellIterator.next();
//						// checking the cell type
//
//						switch (cell.getCellType()) {
//						case Cell.CELL_TYPE_NUMERIC:
//							System.out.print(cell.getNumericCellValue());
//							if (cell.getNumericCellValue() != 0)
//								item.setItemMaxPrice(cell.getNumericCellValue());
//
//							break;
//						case Cell.CELL_TYPE_STRING:
//							System.out.print(cell.getStringCellValue());
//							item.setItemName(cell.getStringCellValue());
//							break;
//						}
//					}
//					System.out.println("");
//				}
//			}
//		}
//
//		catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	 file.close();

		itemDetails.add(item);
		return itemDetails;
	}

//	itemDetails.sort(Comparator.comparing(s -> s.getItemName()));

}
