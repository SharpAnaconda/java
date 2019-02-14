import org.apache.poi.*;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.text.*;
import java.util.*;

public class ThirdCost {
    public static void main(String[] args) {
        try {
            Workbook wb1 = WorkbookFactory.create(new File("\\\\Fs.win.sharp.co.jp\\〔デバイス事業〕実装技術開発部\\~特殊共有\\78_オプトGr旧データ\\生産技術部\\seigi_com\\設備\\メンテナンス連絡\\2018年度\\下期\\メンテナンス連絡2018下期_縁成.xlsx"));
            File dir = new File("C:\\Users\\s138093\\Desktop\\修繕費");
            File[] files = dir.listFiles((directory, fileName) -> fileName.endsWith(".xls") || fileName.endsWith(".xlsx"));
            assert files != null;
            for (File file : files) {
                Workbook wb2 = WorkbookFactory.create(file);
                String value = null;
                for (Sheet sheet : wb2) {
                    for (Row row : sheet) {
                        if (row == null) {
                            continue;
                        }
                        for (Cell cell : row) {
                            if (cell == null) {
                                continue;
                            }
                            if (cell.getCellType() == CellType.STRING) {
                                if (cell.getStringCellValue().startsWith("発行№")) {
                                    value = sheet.getRow(cell.getRowIndex() + 2).getCell(cell.getColumnIndex()).getStringCellValue();
                                }
                            }
                        }
                    }
                    break;
                }
                for (Sheet sheet : wb1) {
                    for (Row row : sheet) {
                        if (row == null) {
                            continue;
                        }
                        for (Cell cell : row) {
                            if (cell == null) {
                                continue;
                            }
                            if (cell.getCellType() == CellType.STRING) {
                                if (cell.getStringCellValue().equals(value)) {
                                    System.out.println(cell.getColumnIndex() + cell.getStringCellValue());
                                    value = row.getCell(0).getStringCellValue();
                                }
                            }
                        }
                    }
                    break;
                }
                for (Sheet sheet : wb2) {
                    for (Row row : sheet) {
                        if (row == null) {
                            continue;
                        }
                        for (Cell cell : row) {
                            if (cell == null) {
                                continue;
                            }
                            if (cell.getCellType() == CellType.STRING) {
                                if (cell.getStringCellValue().startsWith("管理番号")) {
                                    cell.setCellValue("管理番号: " + value);
                                } else if (cell.getStringCellValue().startsWith("日付")) {
                                    cell.setCellValue("日付: " + new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
                                }
                            }
                        }
                    }
                    break;
                }
                wb2.write(new FileOutputStream(dir + "\\new\\" + file.getName()));
            }
        } catch (EncryptedDocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}