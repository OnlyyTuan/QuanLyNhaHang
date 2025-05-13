/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import config.Constants;

/**
 *
 * @author Duc3m
 */
public class Calculator {
    public static final long calculatePrice(long giaNhap) {
        return (long)((long) giaNhap * Constants.PROFIT);
    }
}
