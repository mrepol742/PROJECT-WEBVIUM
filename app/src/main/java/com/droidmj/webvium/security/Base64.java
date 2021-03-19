/*
 *
 *
 *
 * DROID MJ Property || Confidential
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.droidmj.webvium.security;

import java.util.Scanner;

public class Base64 {

    public static final int ENCODE = 1;
    public static final int DECODE = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1: Encode || 2: Decode");
        int stat = scanner.nextInt();
        if (stat == ENCODE) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Input the value to be encrypted:");
            String data = scanner1.nextLine();
            // System.out.println(encode(encode(encode(data, ENCODE), ENCODE), ENCODE));
            String sg = encode(data, ENCODE);
            for (int i = 0; i < 13; i++) {
                sg = encode(sg, ENCODE);
            }
            System.out.println(sg);
        } else if (stat == DECODE) {
            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Input the value to be decrypted:");
            String data1 = scanner2.nextLine();
            // System.out.println(encode(data1, DECODE));
            String temp = encode(data1, DECODE);
            for (int i = 0; i < 13; i++) {
                temp = encode(temp, DECODE);
            }
            System.out.println(temp);
        } else {
            System.out.println("Invalid!");
        }
    }

    public static String encode(String encode, int var1) {
        String data = encode.replaceAll(" ", "#25");
        if (var1 == ENCODE) {
            return java.util.Base64.getEncoder().encodeToString(data.getBytes());
        } else if (var1 == DECODE) {
            byte[] bytes = java.util.Base64.getDecoder().decode(data);
            StringBuilder stringBuilder = new StringBuilder();
            for (byte be : bytes) {
                stringBuilder.append((char) be);
            }
            return stringBuilder.toString().replaceAll("#25", " ");
        }
        throw new RuntimeException();
    }
}
