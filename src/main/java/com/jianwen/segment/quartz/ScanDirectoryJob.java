package com.jianwen.segment.quartz;

import org.quartz.*;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

/**
 * @Author: jianwen
 * @Date: 2018/7/17 下午2:30
 */
public class ScanDirectoryJob implements Job{
//    static Logger logger = LoggerFactory.getLogger(ScanDirectoryJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
//        String jobName = jobDetail.getName();
//        logger.info(jobName + "fired at" + new Date());

        JobDataMap dataMap = jobDetail.getJobDataMap();
        String dirName = dataMap.getString("SCAN_DIR");

        if (dirName == null){
            throw new JobExecutionException("Directory not configured");
        }

        File dir = new File(dirName);
        if (!dir.exists()){
            throw new JobExecutionException("Invalid Dir"+ dirName);
        }

        FileFilter fileFilter = new FileExtensionFileFilter(".xml");
        File[] files = dir.listFiles(fileFilter);

        if (files == null || files.length <= 0){
//            logger.info("No XML files found in" + dir);
            return;
        }

        int size = files.length;
        for (int i = 0; i <size; i++){
            File file = files[i];
            File aFile = file.getAbsoluteFile();
            long fileSize = file.length();
            String msg = aFile + "- Size: " + fileSize;
//            logger.info(msg);
        }

    }
}
