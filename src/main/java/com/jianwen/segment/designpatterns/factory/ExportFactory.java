package com.jianwen.segment.designpatterns.factory;

/**
 * @Author: jianwen
 * @since: 2018/12/16 下午2:23
 */
public interface ExportFactory {
    public ExportFile factory(String type);
}
