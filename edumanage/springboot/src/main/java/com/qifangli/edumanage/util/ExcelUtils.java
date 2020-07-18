package com.qifangli.edumanage.util;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {


    static final short borderpx = 1;

	/**
	 * 导出excel表格
	 * @param head
	 * @param body
	 * @return
	 */
    public static HSSFWorkbook expExcel(List<String> head, List<List<String>> body) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell= null;
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        setBorderStyle(cellStyle, borderpx);
        cellStyle.setFont(setFontStyle(workbook, "黑体", (short) 14));
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        for (int i = 0; i<head.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(head.get(i));
            cell.setCellStyle(cellStyle);
        }
        
        HSSFCellStyle cellStyle2 = workbook.createCellStyle();
        setBorderStyle(cellStyle2, borderpx);
        cellStyle2.setFont(setFontStyle(workbook, "宋体", (short) 12));
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (int i = 0; i < body.size(); i++) {
            row = sheet.createRow(i + 1);
            List<String> paramList = body.get(i);
            for (int p = 0; p < paramList.size(); p++) {
                cell = row.createCell(p);
                cell.setCellValue(paramList.get(p));
                cell.setCellStyle(cellStyle2);
            }
        }
        for (int i = 0, isize = head.size(); i < isize; i++) {
            sheet.autoSizeColumn(i); 
        }
        return workbook;
    }

    /**
     * 文件输出
     * @param workbook 填充好的workbook
     * @param path 存放的位置
     */
    public static void outFile(HSSFWorkbook workbook,String path,HttpServletResponse response) {
        SimpleDateFormat fdate=new SimpleDateFormat("yyyyMMdd-HH点mm分");
        path = path.substring(0, path.lastIndexOf(".")) + fdate.format(new Date()) + path.substring(path.lastIndexOf("."));
        OutputStream os=null;
        File file = null;
        try {
        	file = new File(path);
            String filename = file.getName();
            os = new FileOutputStream(file);
            response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename, "UTF-8"));
             os= new BufferedOutputStream(response.getOutputStream());  
            response.setContentType("application/vnd.ms-excel;charset=utf-8");  
            workbook.write(os);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
        	os.flush();
            os.close();
            System.gc();
            System.out.println(file.delete());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置字体样式
     * @param workbook 工作簿
     * @param name 字体类型
     * @param height 字体大小
     * @return HSSFFont
     */
    private static HSSFFont setFontStyle(HSSFWorkbook workbook, String name, short height) {
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints(height);
        font.setFontName(name);
        return font;
    }

    /**
     * 设置单元格样式
     * @param cellStyle 工作簿
     * @param border border样式
     */
    private static void setBorderStyle(HSSFCellStyle cellStyle, short border) {
        cellStyle.setBorderBottom(border); // 下边框
        cellStyle.setBorderLeft(border);// 左边框
        cellStyle.setBorderTop(border);// 上边框
        cellStyle.setBorderRight(border);// 右边框
    }

    /*****************文件输入相关************************/

    /**
     * @Title: createWorkbook
     * @Description: 判断excel文件后缀名，生成不同的workbook
     * @param @param is
     * @param @param excelFileName
     * @param @return
     * @param @throws IOException
     * @return Workbook
     * @throws
     */
    public static Workbook createWorkbook(InputStream is, String excelFileName) throws IOException{
        if (excelFileName.endsWith(".xls")) {
            return new HSSFWorkbook(is);
        }else if (excelFileName.endsWith(".xlsx")) {
            return new XSSFWorkbook(is);
        }
        return null;
    }

    /**
     * @Title: importDataFromExcel
     * @Description: 将sheet中的数据保存到list中，
     * 1、调用此方法时，vo的属性个数必须和excel文件每行数据的列数相同且一一对应，vo的所有属性都为String
     * 2、在action调用此方法时，需声明
     *     private File excelFile;上传的文件
     *     private String excelFileName;原始文件的文件名
     * 3、页面的file控件name需对应File的文件名
     * @param @param vo javaBean
     * @param @param is 输入流
     * @param @param excelFileName
     * @param @return
     * @return List<Object>
     * @throws
     */
    public static List<Object> importDataFromExcel(Object vo,InputStream is,String excelFileName){
        List<Object> list = new ArrayList<Object>();
        try {
            //创建工作簿
            Workbook workbook = createWorkbook(is, excelFileName);
            //创建工作表sheet
            Sheet sheet = getSheet(workbook, 0);
            //获取sheet中数据的行数
            int rows = sheet.getPhysicalNumberOfRows();
            //获取表头单元格个数
            int cells = sheet.getRow(0).getPhysicalNumberOfCells();
            //利用反射，给JavaBean的属性进行赋值
            Field[] fields = vo.getClass().getDeclaredFields();
            for (int i = 1; i < rows; i++) {//第一行为标题栏，从第二行开始取数据
                vo = vo.getClass().getConstructor(new Class[]{}).newInstance(new Object[]{});
                Row row = sheet.getRow(i);
                int index = 0;
                while (index < cells) {
                    Cell cell = row.getCell(index);
                    if (null == cell) {
                        cell = row.createCell(index);
                    }
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String value = null == cell.getStringCellValue()?"":cell.getStringCellValue();

                    Field field = fields[index];
                    String fieldName = field.getName();
                    String methodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                    Method setMethod = vo.getClass().getMethod(methodName, new Class[]{String.class});
                    setMethod.invoke(vo, new Object[]{value});
                    index++;
                }
                if (isHasValues(vo)) {//判断对象属性是否有值
                    list.add(vo);
                    vo.getClass().getConstructor(new Class[]{}).newInstance(new Object[]{});//重新创建一个vo对象
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            //如果读取错误则返回空List
            list = new ArrayList<>();

            //logger.error(e);
        }finally{
            try {
                is.close();//关闭流
            } catch (Exception e2) {
                //logger.error(e2);
            }
        }
        return list;

    }

    /**
     * @Title: getSheet
     * @Description: 根据sheet索引号获取对应的sheet
     * @param @param workbook
     * @param @param sheetIndex
     * @param @return
     * @return Sheet
     * @throws
     */
    public static Sheet getSheet(Workbook workbook,int sheetIndex){
        return workbook.getSheetAt(0);
    }

    /**
     * @Title: isHasValues
     * @Description: 判断一个对象所有属性是否有值，如果一个属性有值(分空)，则返回true
     * @param @param object
     * @param @return
     * @return boolean
     * @throws
     */
    public static boolean isHasValues(Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        boolean flag = false;
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
            Method getMethod;
            try {
                getMethod = object.getClass().getMethod(methodName);
                Object obj = getMethod.invoke(object);
                if (null != obj && !"".equals(obj)) {
                    flag = true;
                    break;
                }
            } catch (Exception e) {
                //logger.error(e);
            }

        }
        return flag;

    }
}
