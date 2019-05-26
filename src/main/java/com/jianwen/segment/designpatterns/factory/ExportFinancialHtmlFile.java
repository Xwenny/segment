package com.jianwen.segment.designpatterns.factory;

/**
 * @Author: jianwen
 * @since: 2018/12/16 下午2:25
 */
public class ExportFinancialHtmlFile implements ExportFile{
    @Override
    public boolean export(String data) {
        // TODO Auto-generated method stub
        /**
         * 业务逻辑
         */
        System.out.println("导出财务版HTML文件");
        return true;
    }
}
