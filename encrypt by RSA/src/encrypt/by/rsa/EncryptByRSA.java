/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package encrypt.by.rsa;

import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Abdullah Shublaq
 */

public class EncryptByRSA {

    public static String encrypt(String m, BigInteger n/* p*q */, int e, boolean oddLength) {
        char Char;
        BigInteger HexBInt;
        String HexStr = "";
        Integer Hex;
        String EncMessage = "";
        BigInteger c;
        BigInteger power;
        for (int i = 0; i < m.length(); i++) {
            Char = m.charAt(i);
            if (Char == ' ') {
                // power = HexBInt.pow(e);
                // c = power.mod(n);
                HexStr = "00" + HexStr;//+ String.format("%04d", c);               
            } else {
                Hex = (int) Char;
                Hex -= 65;
                HexStr = HexStr + String.format("%02d", Hex);
            }
            if (i % 2 == 0) {
                continue;
            }
            HexBInt = BigInteger.valueOf(Integer.parseInt(HexStr));
            power = HexBInt.pow(e);
            c = power.mod(n);
            EncMessage = EncMessage + String.format("%04d", c);
            HexStr = "";
        }
        if (oddLength) {
            EncMessage = EncMessage + "*";
        }
        return EncMessage;
    }

    /*with n = 53 · 61 and e = 17 */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the message");
        String message = in.nextLine().toUpperCase().trim();
        boolean oddLength = false;
        if (message.length() % 2 != 0) {
            message = message + " ";
            oddLength = true;
        }

        System.out.println("Enter n : (n=p*q) *p,q odd primes* ");
        System.out.print("p = ");
        BigInteger p = in.nextBigInteger();

        System.out.print("q = ");
        BigInteger q = in.nextBigInteger();
        BigInteger n = p.multiply(q);
        System.out.println("n = " + p + " * " + q + " = " + n);

        System.out.print("Enter e : ");
        int e = in.nextInt();

        System.out.println("The encrypted mesaage for \" " + message+ " \" is : " + encrypt(message, n, e, oddLength));

    }
}
