import java.util.*;
import java.lang.Math;

public class Subnetting
{
    // 8+8+8+x
    // powerNumber = 2^x
    int powerNumber;

    private int getPowerNumber()
    {
        return powerNumber;
    }

    private void setPowerNumberFromNoOfSubnets( int nSubnets )
    {
        while( 256%nSubnets != 0 )
        {
            nSubnets++;
        }
        // powerNumber = (int)Math.pow(2,nSubnets);
        powerNumber = 256/nSubnets;
    }

    private void setPowerNumberFromCIDR( int cidr )
    {
        // finding 8 + 8 + ? + ?
        int mod = cidr%8;
        powerNumber = (int)Math.pow(2,8-mod);
    }

    private int getNumberOfSubnets()
    {
        return (256/powerNumber);
    }


    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String ip,subnetMask = "255.255.";
        int choice,cidr,nSubnets;
        boolean isSupernetting = false;
        Subnetting subnetting = new Subnetting(); // created object because main() is static. Either do this or create another class especially for main()
        
        System.out.println("Enter Ip Address");
        ip = sc.next();
        String[] test = ip.split("\\.",5);
        for ( String str : test )
        {
            int x = Integer.valueOf(str);
            if( x < 0 || x > 255 )
            {
                System.out.println("Invalid IP");
                System.exit(1);
            }
        }

        System.out.println("1. Enter CIDR ( ex. 26 )");
        System.out.println("2. Enter number of subnets ( ex. 4 )");
        choice = sc.nextInt();

        if( choice!=1 && choice !=2)
        {
            System.out.println("Invalid Input");
            sc.close();
            System.exit(1);
        }
        
        
        if( choice == 1 )
        {
            cidr = sc.nextInt();
            if( cidr < 16 || cidr > 31)
            {
                System.out.println("CIDR Does not fit into subnetting or supernetting");
                System.exit(1);
            }
            // finding if supernetting or subnetting
            if( Integer.valueOf(cidr / 8) < 3 )
                isSupernetting = true;

            subnetting.setPowerNumberFromCIDR(cidr);
        }
        else if ( choice == 2 )
        {
            nSubnets = sc.nextInt();
            subnetting.setPowerNumberFromNoOfSubnets(nSubnets);
        }


        int host = 256 - subnetting.getPowerNumber();
        
        if( isSupernetting )
            subnetMask += host + ".0";
        else
            subnetMask += "255." + host;
        System.out.println(subnetMask);

        if(!isSupernetting)
            System.out.println("Number of subnets formed: " + subnetting.getNumberOfSubnets());
        else
            System.out.println("Number of supernets formed: " + subnetting.getNumberOfSubnets());

        // removing last element from 
        ArrayList<String> test2 = new ArrayList<>(Arrays.asList(test));
        int lastIpBits;
        if( isSupernetting )
        {    
            test2.remove(2);
            test2.remove(2);
            lastIpBits = Integer.valueOf(test[2]);
        }
        else
        {
            test2.remove(3);
            lastIpBits = Integer.valueOf(test[3]);
        }
        
        // converting array back to string
        // half ip will be first 3 ip bits e.g. 192.168.13. ( for printing range )
        String halfIp = "";
        for( String str : test2 )
        {
            halfIp = halfIp + str + ".";
        }

        // finding range
        int pow = subnetting.getPowerNumber();
        int maxLimit = pow;
        int minLimit = 0;
        while( 256 >= maxLimit )
        {
            if( !isSupernetting )
                System.out.print( halfIp + minLimit + " to " + halfIp + (maxLimit-1) );
            else
                System.out.print( halfIp + minLimit + ".0" + " to " + halfIp + (maxLimit-1) + ".0");

            if( minLimit < lastIpBits && maxLimit > lastIpBits )
                System.out.print(" <- ip belongs to this range\n");
            else
                System.out.println();
            minLimit = maxLimit;
            maxLimit += pow;
        }

        sc.close();
    }
}
/*

import java.lang.*;
import java.util.Scanner;

public class mysubnet{

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
                            System.out.println("Number of one's execeds");
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
        int ones_p = 8;
        int rem;
        String binary_NID="";
        if(claas == 0){
            ones_p = 8; 
            rem = ones - ones_p;
            if(rem > 23){
                System.out.println("Number of one's execeds");
                System.exit(1);
            
            }
            for(int i=0;i<rem;i++){
                binary_NID += "1";
                if(i==7){
                    //binary_NID.insert(7,'.');
                    StringBuffer stringBuffer = new StringBuffer(binary_NID);
                    stringBuffer.insert(7, '.');
                }
            }
            System.out.println(rem + "  " + binary_NID);
        }
        else if(claas == 1){
            ones_p = 16; 
            rem = ones - ones_p;
            if(rem > 15){
                System.out.println("Number of one's execeds");
                System.exit(1);
            }
            
            for(int i=0;i<rem;i++){
                binary_NID += "1";
                if(i==7 || i==15){
                    //binary_NID.insert(7,'.');
                    StringBuffer stringBuffer = new StringBuffer(binary_NID);
                    stringBuffer.insert(7, '.');
                }
            }
            System.out.println(rem + "  " + binary_NID);
        }
        else{
            claas = 32; 
            rem = ones - ones_p;
            if(rem > 7){
                System.out.println("Number of one's execeds");
                System.exit(1);
            }
            for(int i=0;i<rem;i++){
                binary_NID += "1";
            }
            System.out.println(rem + "  " + binary_NID);
        }

        // String networkAddr ="",lastAddr ="";
        
        // String[] maskParts =mask.split("\\.");

        // for(int i=0;i<4;i++){

        //     int x = ip_ipart[i];
        //     int y = Integer.parseInt(maskParts[i]);

        //     int z = x&y;
        //     networkAddr += z + ".";
        //     int w = z|(y^255);
        //     lastAddr += w + ".";

        // }
   
        // System.out.println("\nFirst IP of block: " + networkAddr);
        // System.out.println("Last IP of block: " + lastAddr);

    }

}

*/