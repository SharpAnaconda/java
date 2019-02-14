import org.apache.poi.*;
import org.apache.poi.ss.usermodel.*;

import java.io.*;

public class SecondCost {
    private static Workbook createWorkbook(String superPath) {
        File fileIn = new File(superPath);
        Workbook wbIn = null;
        try {
            wbIn = WorkbookFactory.create(fileIn);
        } catch (EncryptedDocumentException | IOException e) {
            e.printStackTrace();
        }
        return wbIn;
    }

    private static String getIDStr(String superMasterWbPath) {
        Workbook masterWbIn = createWorkbook(superMasterWbPath);
        boolean flag = false;
        for (Sheet sheet : masterWbIn) {
            for (Row row : sheet) {
                if (row == null) {
                    continue;
                }
                for (Cell cell : row) {
                    if (cell == null) {
                        continue;
                    } else if (cell.getCellType().equals(CellType.STRING) && cell.getStringCellValue().equals("受付No.")) {
                        flag = true;
                    } else if (cell.getCellType().equals(CellType.STRING) && cell.getStringCellValue().equals("") && flag) {
                        String idStr = sheet.getRow(cell.getRowIndex() - 1).getCell(0).getStringCellValue();
                        System.out.println(idStr);
                        return idStr;
                    }
                    break;
                }
            }
            String idStr = sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue();
            System.out.println(idStr);
            return idStr;
        }
        return null;
    }

    private static Workbook createRepairCostList(String superPath, String superIDStr) {
        Workbook wbIn = createWorkbook(superPath);
        String str = superIDStr.split("-")[1];
        int i = Integer.parseInt(str);
        for (Sheet sheet : wbIn) {
            for (Row row : sheet) {
                if (row == null) {
                } else if (row.getRowNum() == 0 || !sheet.getRow(row.getRowNum() - 1).getCell(1).getStringCellValue()
                        .equals(row.getCell(1).getStringCellValue())) {
                    i += 1;
                    row.getCell(0).setCellValue("E18下-" + String.format("%03d", (i)));
                } else {
                    row.getCell(0).setCellValue("E18下-" + String.format("%03d", (i)));
                }
            }
            break;
        }
        return wbIn;
    }

    private static OutputStream workbookOutputStream(String superPath) {
        try {
            return new FileOutputStream(superPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] arsg) {
        String thisIdStr = getIDStr("\\\\Fs.win.sharp.co.jp\\〔デバイス事業〕実装技術開発部\\~特殊共有\\78_オプトGr旧データ\\生産技術部\\seigi_com\\設備\\メンテナンス連絡\\2018年度\\下期\\メンテナンス連絡2018下期_縁成.xlsx");
        assert thisIdStr != null;
        Workbook wbSave = createRepairCostList("C:\\Users\\s138093\\Desktop\\sample.xlsx", thisIdStr);
        OutputStream wbOut = workbookOutputStream("C:\\Users\\s138093\\Desktop\\sample1.xlsx");
        try {
            wbSave.write(wbOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}