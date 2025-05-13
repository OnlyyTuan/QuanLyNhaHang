/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author Admin
 */
public class Formatter {
    public static String FormatVND(long vnd) {
        return String.format(Locale.US, "%,d", (long)vnd) + "đ";
    }
    
    public static String FormatDateTime(Timestamp thoigian) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        return formatDate.format(thoigian);
    }
    
    public static String getYear(Timestamp thoigian) {
        SimpleDateFormat formatDate = new SimpleDateFormat("YYYY");
        return formatDate.format(thoigian);
    }
    
    public static String getMonth(Timestamp thoigian) {
        SimpleDateFormat formatDate = new SimpleDateFormat("MM/YYYY");
        return formatDate.format(thoigian);
    }
    
    public static String getDate(Timestamp thoigian) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY");
        return formatDate.format(thoigian);
    }
    
}
