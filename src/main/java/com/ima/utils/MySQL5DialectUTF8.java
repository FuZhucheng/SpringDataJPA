package com.ima.utils;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * Created by ${符柱成} on 2017/3/21.
 */
public class MySQL5DialectUTF8 extends MySQL5InnoDBDialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}