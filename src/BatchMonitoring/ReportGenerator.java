package BatchMonitoring;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ReportGenerator
{
	private static XSSFColor red = new XSSFColor(Color.red);
	private static XSSFColor green = new XSSFColor(Color.green);

	public static void generateExcelReport(ArrayList<Batch> batch, String fileName)
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Sheet - " + fileName);

		int rowIndex = 2;
		CellStyle redCell = workbook.createCellStyle();
//		redCell.setFillPattern(CellStyle.SOLID_FOREGROUND);
		redCell.setFillForegroundColor(IndexedColors.RED.getIndex());
		redCell.setFillBackgroundColor(IndexedColors.RED.getIndex());

		CellStyle greenCell = workbook.createCellStyle();
//		greenCell.setFillPattern(CellStyle.SOLID_FOREGROUND);
		greenCell.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		greenCell.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());

		CellStyle yellowCell = workbook.createCellStyle();
//		yellowCell.setFillPattern(CellStyle.SOLID_FOREGROUND);
		yellowCell.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		yellowCell.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());

		CellStyle blueCell = workbook.createCellStyle();
//		blueCell.setFillPattern(CellStyle.SOLID_FOREGROUND);
		blueCell.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
		blueCell.setFillBackgroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		Row StartRow = sheet.createRow(0);
		Cell StartCell = StartRow.createCell(0);
		StartCell.setCellValue(fileName);

		Row headerRow = sheet.createRow(1);
		String headerData[] = { "Batch name", "Status", "Start time", "End time", "Time taken", "Exception" };
		for (int i = 0; i < headerData.length; i++)
		{
			Cell headerCell = headerRow.createCell(i);
			headerCell.setCellValue(headerData[i]);
			headerCell.setCellStyle(blueCell);
		}
		for (Batch b : batch)
		{
			Row row = sheet.createRow(rowIndex++);
			String status = null;
			String data[] = null;
			if (b.getBatchState() == BatchState.NEW)
			{
				status = "NOT STARTED/NEW";
				data = new String[] { b.getName(), status };
			}
			else if (b.getBatchState() == BatchState.RUNNING)
			{
				status = "RUNNING";
				data = new String[] { b.getName(), status };
			}
			else
			{
				status = "COMPLETED";
				if (b.getName().equalsIgnoreCase("STANDINGORDER"))
				{
					data = new String[] { b.getName(), status };
				}
				else
				{
					if (Property.EXCEPTIONS_ENABLED)
					{
						data = new String[] { b.getName(), status, b.getStartTime().toString(), b.getEndTime().toString(), b.timeInterval(), b.getExceptionMessage() };
					}
					else
					{
						data = new String[] { b.getName(), status, b.getStartTime().toString(), b.getEndTime().toString(), b.timeInterval() };
					}

				}
			}
			for (int cellIndex = 0; cellIndex < data.length; cellIndex++)
			{
				Cell cell = row.createCell(cellIndex);
				if (cellIndex == 0)
				{
					cell.setCellValue(data[cellIndex]);
				}
				if (cellIndex == 1)
				{
					if (data[cellIndex].equalsIgnoreCase("NOT STARTED/NEW"))
					{
						cell.setCellStyle(redCell);
					}
					else if (data[cellIndex].equalsIgnoreCase("RUNNING"))
					{
						cell.setCellStyle(yellowCell);
					}
					else
					{
						cell.setCellStyle(greenCell);
					}
					cell.setCellValue(data[cellIndex]);
				}
				if (cellIndex == 2)
				{
					cell.setCellValue(data[cellIndex]);
				}
				if (cellIndex == 3)
				{
					cell.setCellValue(data[cellIndex]);
				}
				if (cellIndex == 4)
				{
					cell.setCellValue(data[cellIndex]);
				}
				if (Property.EXCEPTIONS_ENABLED)
				{
					if (cellIndex == 5)
					{
						if (data[cellIndex] != null && data[cellIndex].indexOf("Exception") != -1)
						{
							cell.setCellStyle(redCell);
							cell.setCellValue(data[cellIndex]);
						}

					}
				}
			}
		}
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.autoSizeColumn(4);
		sheet.autoSizeColumn(5);
		FileOutputStream fos = null;
		try
		{
			File file = new File("Workbook-" + fileName + ".xlsx");
			if (file.exists())
			{
				if (file.delete())
				{
					System.out.println("File deleted!");
				}
				else
				{
					System.out.println("File not deleted!");
				}
			}
			fos = new FileOutputStream(file);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				workbook.write(fos);
				fos.flush();
				fos.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Created Excel file successfully!");

	}
}
