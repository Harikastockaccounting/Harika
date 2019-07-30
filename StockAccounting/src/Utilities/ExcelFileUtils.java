package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtils
{
	
	Workbook wb;
	//It load all excel  sheets
	public ExcelFileUtils() throws Throwable
	{
	FileInputStream fis=new FileInputStream("D:\\Harika_Stock\\StockAccounting\\TestInputs\\InputSheet.xlsx");
	wb=WorkbookFactory.create(fis);
	
	}
	//row count
public int rowCount(String sheetname)
{
return wb.getSheet(sheetname).getLastRowNum();
	}

//coloumn count
public int columnCount(String sheetname, int row)
{
return wb.getSheet(sheetname).getRow(row).getLastCellNum();	
}

//Reading the data
public String getData(String sheetname,int row,int coloumn)
{
	
String data="";
if (wb.getSheet(sheetname).getRow(row).getCell(coloumn).getCellType()==Cell.CELL_TYPE_NUMERIC)
{
	int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(coloumn).getNumericCellValue();
	data=String.valueOf(celldata);
	
}else
	
	{
		data=wb.getSheet(sheetname).getRow(row).getCell(coloumn).getStringCellValue();
	}
return data;
	}

	

//Store the data
public void setData(String sheetname,int row,int coloumn,String status) throws Throwable
{
	Sheet sh=wb.getSheet(sheetname);
	Row rownum=sh.getRow(row);
	Cell cell=rownum.createCell(coloumn);
	cell.setCellValue(status);
	if (status.equalsIgnoreCase("PASS")) 
	{
		//create cell style
		CellStyle style=wb.createCellStyle();
		//create font
		Font font=wb.createFont();
		font.setColor(IndexedColors.GREEN.getIndex());
		//set bold
		font.setBold(true);
		//set font
		style.setFont(font);
		//set cell style
		rownum.getCell(coloumn).setCellStyle(style);
		
	}else
		if(status.equalsIgnoreCase("FAIL"))
		{
		//create cell style
			CellStyle style=wb.createCellStyle();
		//create font
			Font font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			//set bold
			font.setBold(true);
			//set font
			style.setFont(font);
			//set cell style
			rownum.getCell(coloumn).setCellStyle(style);
			
		}else
			if(status.equalsIgnoreCase("Not Executed"))
			{
				//create cell style
				CellStyle style=wb.createCellStyle();
			//create font
				Font font=wb.createFont();
				font.setColor(IndexedColors.BLUE.getIndex());
				//set bold
				font.setBold(true);
				//set font
				style.setFont(font);
				//set cell style
				rownum.getCell(coloumn).setCellStyle(style);
				
			}
	FileOutputStream fos=new FileOutputStream("D:\\Harika_Stock\\StockAccounting\\TestOutput\\outputsheet.xlsx");
	wb.write(fos);
	fos.close();
}
}





