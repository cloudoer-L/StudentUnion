package cloudoer.su.service.impl;

import cloudoer.su.base.impl.BaseServiceImpl;
import cloudoer.su.entity.Position;
import cloudoer.su.exception.ServiceException;
import cloudoer.su.service.PositionService;
import cloudoer.su.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service("positionService")
@Transactional
public class PositionServiceImpl extends BaseServiceImpl implements PositionService{

    public List<Position> getAll() {
        return positionDao.getAll();
    }

    public List<Position> getByPage(int pageNo, int pageSize) {
        return positionDao.getByPage(pageNo, pageSize);
    }

    public Position getById(String id) {
        return (Position) positionDao.getById(id);
    }

    public Position getByNumber(String number) {
        return (Position) positionDao.getByNumber(number);
    }

    public String add(Position position) {
        return positionDao.add(position);
    }

    public void update(Position position) {
        Position p = (Position) positionDao.getById(position.getId());
        if (p == null){
            throw new ServiceException("修改失败，请检查参数");
        }
        p.setName(position.getName());
        p.setNumber(position.getNumber());
        p.setIntroduce(position.getIntroduce());
        p.setState(position.getState());
    }

    public void delete(String id) {
        Position p = (Position) positionDao.getById(id);
        if (p == null){
            throw new ServiceException("删除失败，请检查参数");
        }
        positionDao.delete(id);
    }

    public String importFile(File file) throws ServiceException {
        HSSFWorkbook workbook = null;
        StringBuffer msg = new StringBuffer();
        try{
            workbook = new HSSFWorkbook(new FileInputStream(file));
            HSSFSheet sheet = workbook.getSheetAt(0);
            int cont = sheet.getLastRowNum()+1;
            Position p = null;
            int successCount = 0;
            int errorCount = 0;
            for (int i = 1; i < cont; i++){
                try{
                    p = new Position();
                    p.setName(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(0)));
                    p.setNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(1)));
                    p.setIntroduce(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(2)));
                    p.setState(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(3)));
                    positionDao.add(p);
                    successCount++;
                    if (i+1%20==0){
                        positionDao.getSession().flush();
                        positionDao.getSession().clear();
                    }
                }catch (ServiceException e){
                    errorCount++;
                    msg.append("[第"+(i+1)+"行]原因:" + e.getErrorMsg());
                    msg.append("<br/>");
                }catch (Exception e){
                    errorCount++;
                    msg.append("[第"+(i+1)+"行]原因:不明...<br/>");
                }
                msg.insert(0,"文件上传成功<br/>导入成功:"+ successCount +"条" + "<br/>导入失败:"+errorCount+ "条 <br/>记录<br/>");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("导入失败，解析文件错误，请检查文件格式");
        }
        return msg.toString();
    }

    public void exportFile(OutputStream os) throws Exception {
        List<Position> positions = positionDao.getAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFRow rows = sheet.createRow(0);
        rows.createCell(0).setCellValue("职务名称");
        rows.createCell(1).setCellValue("职务编号");
        rows.createCell(2).setCellValue("职务说明");
        rows.createCell(3).setCellValue("状态");
        for (int i = 0; i < positions.size(); i++){
            rows = sheet.createRow(i+1);
            rows.createCell(0).setCellValue(positions.get(i).getName());
            rows.createCell(1).setCellValue(positions.get(i).getNumber());
            rows.createCell(2).setCellValue(positions.get(i).getIntroduce());
            rows.createCell(3).setCellValue(positions.get(i).getState());
        }
        workbook.write(os);
    }
}
