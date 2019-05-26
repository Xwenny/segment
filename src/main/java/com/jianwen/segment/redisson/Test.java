package com.jianwen.segment.redisson;

import java.util.Date;

import org.joda.time.LocalDate;

/**
 * @author jianwen
 * @since 2019/04/02
 */
public class Test {
        public static void main(String[] args) {
            System.out.println(new LocalDate(System.currentTimeMillis()).toString("yyyyMMdd"));
            System.out.println(new Date());
            System.out.println(new LocalDate().toString("yyyyMMdd"));
            System.out.println(LocalDate.now().toString("yyyyMMdd"));

        }


}
