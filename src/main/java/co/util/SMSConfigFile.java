package co.util;

import java.io.*;

/**
 * Created by Edward L. Ramirez A.
 * cel 300 554 3367
 * email elramireza@gmail.com
 * User: usuariox
 * Date: Aug 19, 2011
 * Time: 9:03:02 AM
 */
public class SMSConfigFile {

    public static void main(String[] args)
            throws IOException {

        FileWriter outFile = new FileWriter("test01.txt");
			PrintWriter out = new PrintWriter(outFile);
			
        // Also could be written as follows on one line
        // Printwriter out = new PrintWriter(new FileWriter(args[0]));

        // Write text to file

        out.println("");
        out.println("# **************  Global \t ********************");
        out.println("[global]\n" +
                "port = /dev/ttyUSB0\n" +
                "model = AT\n" +
                "initlength = default\n" +
                "connection = serial\n" +
                "use_locking = yes\n" +
                "serial_baudrate = 19200\n" +
                "smsc_timeout = 10");
        out.println("");
        for (int maquina=1; maquina<=2; maquina++) {
            out.println("");
            out.println("");
            out.println("# **************  Maquina \t" + maquina + " ********************");
            out.println("");
            for (int puertoTTY=0; puertoTTY<30; puertoTTY++) {
                out.println("");
                out.println("[phone_"+ (puertoTTY+(maquina*1000)) +"]\n" +
                        "port = /dev/ttyUSB"+puertoTTY+"\n" +
                        "model = AT\n" +
                        "initlength = default\n" +
                        "connection = serial\n" +
                        "use_locking = yes\n" +
                        "serial_baudrate = 19200\n" +
                        "smsc_timeout = 10");
            }
        }
        out.println();
        out.println("\n" +
                "[xgnokii]\n" +
                "allow_breakage = 0\n" +
                "\n" +
                "[gnokiid]\n" +
                "bindir = /usr/sbin/\n" +
                "\n" +
                "[connect_script]\n" +
                "TELEPHONE = 12345678\n" +
                "[disconnect_script]");
        out.close();

    }
}
