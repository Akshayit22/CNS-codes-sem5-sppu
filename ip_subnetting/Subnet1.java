import java.io.*;
import java.net.InetAddress;
public class Subnet1 {

    public static void main(String[] args) throws IOException {
   
        System.out.println("ENTER IP:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ip = br.readLine();
        String checkclass = ip.substring(0, 3);



        int cc = Integer.parseInt(checkclass);
        String mask = null;
        if(cc>0)
        {
            if(cc<=127)
            {
                mask = "255.0.0.0";
        System.out.println("Class A IP Address");
        System.out.println("SUBNET MASK:\n"+mask);
            }
            if(cc>=128 && cc<=191)
            {
                mask = "255.255.0.0";
        System.out.println("Class B IP Address");
        System.out.println("SUBNET MASK:\n"+mask);
            }
            if(cc>=192 && cc<=223)
            {
                mask = "255.255.255.0";
        System.out.println("Class C IP Address");
        System.out.println("SUBNET MASK:\n"+mask);
            }
        if(cc>=224 && cc<=239)
            {
        mask = "255.0.0.0";
                System.out.println("Class D IP Address Used for multicasting");
            }
            if(cc>=240 && cc<=254)
            {
        mask = "255.0.0.0";
                System.out.println("Class E IP Address Experimental Use");
            }
        }
      

        String networkAddr="";
    String lastAddr="";
        String[] ipAddrParts=ip.split("\\.");
        String[] maskParts=mask.split("\\.");

        for(int i=0;i<4;i++){
            int x=Integer.parseInt(ipAddrParts[i]);
            int y=Integer.parseInt(maskParts[i]);
            int z=x&y;
            networkAddr+=z+".";
            int w=z|(y^255);
            lastAddr+=w+".";
        }
   
    System.out.println("First IP of block: "+networkAddr);
    System.out.println("Last IP of block: "+lastAddr);
   }

}

/*OUTPUT
iotlab@iotlab-Veriton-M200-B360:~$ javac Subnet1.java
iotlab@iotlab-Veriton-M200-B360:~$ java Subnet1
ENTER IP:
226.35.65.23
Class D IP Address Used for multicasting
First IP of block: 226.0.0.0.
Last IP of block: 226.255.255.255.
iotlab@iotlab-Veriton-M200-B360:~$ java Subnet1
ENTER IP:
192.168.100.5
Class C IP Address
SUBNET MASK:
255.255.255.0
First IP of block: 192.168.100.0.
Last IP of block: 192.168.100.255.
iotlab@iotlab-Veriton-M200-B360:~$
*/

/*
import java.lang.*;
import java.util.Scanner;

public class mysubnet {

    public static String[] ip_parts;
    public static int[] ip_ipart = new int[4];
    public static int ones = 0;
    public static int claas = -1;
    public static String mask;

    public static void main(String args[]) {

        String ip;

        mysubnet obj = new mysubnet();
        Scanner sc = new Scanner(System.in);
        System.out.print("ENTER IP ADDRESS : ");
        ip = sc.nextLine();

        ip_parts = ip.split("\\.");

        obj.is_valid();
        obj.check_class();
        obj.cal_addresses();

        // for (int i : ip_ipart) {
        // System.out.println(i);
        // }System.out.println(ones);
        sc.close();
    }

    void is_valid() {

        boolean valid = true;

        if ((ip_parts.length) != 4) {
            valid = false;
        } else {
            for (int i = 0; i < 4; i++) {

                if (i == 3) {
                    if (ip_parts[i].contains("/")) {
                        String[] separate = ip_parts[i].split("/");
                        ip_parts[i] = separate[0];
                        ip_ipart[i] = Integer.parseInt(separate[0]);
                        ones = Integer.parseInt(separate[1]);

                        if (ones > 32 || ones < 16) {
                            valid = false;
                            break;
                        }
                    }
                } else {
                    int value = Integer.parseInt(ip_parts[i]);
                    ip_ipart[i] = value;
                    if (value < 0 || value > 256) {
                        valid = false;
                        break;
                    }
                }
            }
        }
        if (!valid) {
            System.out.println("ENTER A VALID IP ADDRESS.");
            System.exit(1);
        }
        System.out.println("IP ADDRESS IS VALID.");
    }

    void check_class(){

        int cc = Integer.parseInt(ip_parts[0]);
        
        if(cc <= 127) {
            mask = "255.0.0.0";
            System.out.println("\nClass A IP Address");
            System.out.println("SUBNET MASK : " + mask);
            claas = 0;
        }
        else if(cc >= 128 && cc <= 191) {
            mask = "255.255.0.0";
            System.out.println("\nClass B IP Address");
            System.out.println("SUBNET MASK : " + mask);
            claas = 1;
        }
        else if(cc >= 192 && cc <= 223) {
            mask = "255.255.255.0";
            System.out.println("\nClass C IP Address");
            System.out.println("SUBNET MASK : " + mask);
            claas = 2;
        }
        else if(cc >= 224 && cc <= 239) {
            mask = "255.0.0.0";
            System.out.println("\nClass D IP Address Used for multicasting");
            System.exit(1);
        }
        else if(cc >= 240 && cc <= 254) {
            mask = "255.0.0.0";
            System.out.println("\nClass E IP Address Experimental Use");
            System.exit(1);
        }else{

        }
    }

    void cal_addresses(){
        // int ones_p = 8;
        // if(claas = 0){ ones_p = 8; }
        // else if(claas = 1){ ones_p = 16; }
        // else{ claas = 32; }

        String networkAddr ="",lastAddr ="";
        
        String[] maskParts =mask.split("\\.");

        for(int i=0;i<4;i++){

            int x = ip_ipart[i];
            int y = Integer.parseInt(maskParts[i]);

            int z = x&y;
            networkAddr += z + ".";
            int w = z|(y^255);
            lastAddr += w + ".";

        }
   
        System.out.println("\nFirst IP of block: " + networkAddr);
        System.out.println("Last IP of block: " + lastAddr);

    }

}
*/