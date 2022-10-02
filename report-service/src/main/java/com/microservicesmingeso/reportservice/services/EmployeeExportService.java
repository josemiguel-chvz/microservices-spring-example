package com.microservicesmingeso.reportservice.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.microservicesmingeso.reportservice.models.EmployeeModel;

public class EmployeeExportService {
    private List<EmployeeModel> employees;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public EmployeeExportService(List<EmployeeModel> employees) {
        this.employees = employees;
        workbook = new XSSFWorkbook();
    }

    public void generateFile(HttpServletResponse response) throws IOException {
        writeHeader(); // Escribir primera file
        writeObjects(); // Escribir objeto
        ServletOutputStream outputStream = response.getOutputStream(); // Buffer en bits para guardar excel file
        workbook.write(outputStream); //Escribe datos archivo excel en buffer y descarga de archivo
        workbook.close(); //Cierra archivo
        outputStream.close(); //Cierra buffer una vez descargado
    }

    private void writeHeader() {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(18);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);

        sheet = workbook.createSheet("Employees");
        Row row = sheet.createRow(0);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "Identification", style);
        createCell(row, 2, "First Name", style);
        createCell(row, 3, "Last Name", style);
        createCell(row, 4, "Entrance", style);
        createCell(row, 5, "Departure", style);
        createCell(row, 6, "Base Salary", style);
    }

    private void writeObjects() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(16);
        style.setFont(font);
        for (EmployeeModel record: employees) {
            int columnCount = 0;
            Row row = sheet.createRow(rowCount);
            createCell(row, columnCount++, record.getId(), style);
            createCell(row, columnCount++, record.getIdentification(), style);
            createCell(row, columnCount++, record.getFirst_name(), style);
            createCell(row, columnCount++, record.getLast_name(), style);
            createCell(row, columnCount++, record.getEntrance_date(), style);
            createCell(row, columnCount++, record.getDeparture_date(), style);
            createCell(row, columnCount++, record.getBase_salary(), style);
            rowCount++;
        }
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else if (valueOfCell instanceof Boolean) {
            cell.setCellValue((Boolean) valueOfCell);
        } else {
            cell.setCellValue("NULL");
        }
        cell.setCellStyle(style);
    }
}
