package com.example.util;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * @Description:excel导入工具类
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/2/15
 **/

public class ExcelUtil {


    /**
     * @Author liuf
     * @Description: 根据路径导入
     * @Date 10:13 2019/2/21
     * @Param [filePath, titleRows, headerRows, pojoClass]
     * @return java.util.List<T>
     **/
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        }catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }
    
    
    /**
     * @Author liuf
     * @Description: 上传文件导入
     * @Date 10:14 2019/2/21
     * @Param [file, titleRows, headerRows, pojoClass]
     * @return java.util.List<T>
     **/
    public static  <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (file == null){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
