package cloudoer.su.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtil {

    /**
     * 将单元格的值都以字符串类型返回
     * @param cell
     * @return
     */
    public static String getCallValueToString(Cell cell){
        String value = "";
        if (cell == null){
            return "";
        }else {
            switch (cell.getCellTypeEnum()){
                case NUMERIC :
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        //如果是date类型则 ，获取该cell的date值
                        Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        value = format.format(date);;
                    }else {// 纯数字
                        BigDecimal big=new BigDecimal(cell.getNumericCellValue());
                        value = big.toString();
                        //解决1234.0  去掉后面的.0
                        if(null!=value&&!"".equals(value.trim())){
                            String[] item = value.split("[.]");
                            if(1<item.length&&"0".equals(item[1])){
                                value=item[0];
                            }
                        }
                    }
                    break;
                case STRING:
                    value = cell.getStringCellValue().trim();
                    break;
                // 布尔类型
                case BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                // 空值
                case BLANK:
                    value = "";
                    break;
                // 故障
                case ERROR:
                    value = "";
                    break;
                default:
                    value = cell.getStringCellValue().toString().trim();
            }
        }
        return value;
    }
}
