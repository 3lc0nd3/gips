package co.util;

import co.com.lh.smsfin.util.CommandSystem;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import java.security.*;

import com.sun.rowset.JoinRowSetImpl;


/**
 * Created by Edward L. Ramirez A.
 * cel 300 554 3367
 * email elramireza@gmail.com
 * User: usuariox
 * Date: Aug 5, 2011
 * Time: 12:43:11 AM
 */
public class Uno {
    public static void main(String[] args) {

/*
        String f = "pidof apache2";
        f = CommandSystem.exec(f).trim();
        String[] ff = f.split(" ");
        for (String s : ff) {
            System.out.println("s =" + s+"=");
        }*/
/*

        String f = "smshilo";
        f = CommandSystem.exec(f).trim();
        String[] ff = f.split(" ");
        for (String s : ff) {
            System.out.println("s =" + s+"=");
        }
*/

        /*
        ArrayList<String> imeis = new ArrayList<String>();

        String ls = "lsmodem";
        String pS = CommandSystem.exec(ls);
//        System.out.println("pS = " + pS);
        String[] arrayPortS = pS.split("\n");
//        System.out.println("arrayPortS.length = " + arrayPortS.length);
        for (int i = 1; i < arrayPortS.length; i++) {
            String s = arrayPortS[i];
//            System.out.println("s = " + s);
        }

//        System.out.println("arrayPortS.length = " + arrayPortS.length);

        String aux1;
        for (int i=1; i<= arrayPortS.length-1; i++) {

            String portDev = arrayPortS[i];
            int index = portDev.indexOf("USB") + 3;
            String phone = portDev.substring(index).trim();
//            System.out.println("phone = " + phone);
//          ANTERIOR LINEA DE COMANDO
//            aux1 = CommandSystem.exec(" --phone "+phone+" --identify");
            aux1 = CommandSystem.exec("modembind "+phone);
//            System.out.println("aux1 = " + aux1);

            index = aux1.indexOf("IMEI         :");

//            System.out.println("index = " + index);
            if (index != -1) {
                int intInicio = aux1.indexOf(":", index)+2;
//                System.out.println("intInicio = " + intInicio);
                int intFinal = aux1.indexOf("\n", index);
//                System.out.println("intFinal = " + intFinal);
                aux1 = aux1.substring(intInicio, intFinal).trim();

//                System.out.println("aux"+(phone)+" =" + aux1);
            } else {
            }
        }
*/
        /*
        int phone = 2000;
        String command;
        command = "modembind " + phone;
        command = "gnokii --phone "+phone+" --identify";
        command = "ls";
        command = "gnokii --identify";
        System.out.println("command = " + command);
        String aux1 = CommandSystem.exec(command);
        System.out.println("aux1 = " + aux1);
*/
        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);
        System.out.println("Long.MIN_VALUE = " + Long.MIN_VALUE);

        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);


        String yourString = "123456789";
        byte[] bytesOfMessage = yourString.getBytes();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);

            StringBuffer hexString = new StringBuffer();
            for (byte aThedigest : thedigest) {
                String hex = Integer.toHexString(0xff & aThedigest);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
    	System.out.println("Digest(in hex format):: " + hexString.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);

        

    }
}
