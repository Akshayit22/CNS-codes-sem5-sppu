
import java.lang.*;
import java.util.Scanner;

public class mysubnet{

    public static String[] ip_parts;
    public static int[] ip_ipart = new int[4];
    public static int ones = 0;
    public static int claas = -1;
    public static String mask,ip;

    public static void main(String args[]) {


        mysubnet obj = new mysubnet();
        Scanner sc = new Scanner(System.in);
        System.out.print("ENTER IP ADDRESS : ");
        ip = sc.nextLine();

        ip_parts = ip.split("\\.");

        obj.is_valid();
        obj.check_class();
        obj.show_addresses();
        obj.cal_addresses();

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

                        if (ones > 32 ) {
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
        System.out.println("\nIP ADDRESS  " + ip + "  IS VALID.");
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

    void show_addresses(){
        String networkAddr ="",lastAddr ="";
        
        String[] maskParts =mask.split("\\.");

        for(int i=0;i<4;i++){

            int x = ip_ipart[i];
            int y = Integer.parseInt(maskParts[i]);

            int z = x & y;
            networkAddr += z + ".";
            int w = z|(y^255);
            lastAddr += w + ".";

        }
        
        System.out.println("\nFirst IP of block: " + networkAddr);
        System.out.println("Last IP of block: " + lastAddr + "\n");
    }
    
    void cal_addresses(){

        String[] mask_str;
        String mask = ""; int temp;
        String first_addrs="",last_addrs="" , mask_deci="";

        int posible_adrss = (int)Math.pow(2,(32-ones));

        while(true){
            if(mask.length() > 31){
                break;
            } 
            if(ones > 23){
                mask += one(8);mask += one(8);mask += one(8);
                ones -= 24;
            }else{
                if(ones > 0 && ones < 9){
                    mask += one(ones);
                }
                if(mask.length() < 32){
                    mask += zero(32-mask.length());
                }
            }
        } 
        mask = mask.replace("11111111","11111111.");
        System.out.println("Subnet mask in binary : " + mask);  
    
        mask_str = mask.split("\\.");

        for(int i=0;i<4;i++){
            mask_deci += Integer.parseInt(mask_str[i], 2);  mask_deci +=".";

            temp = Integer.parseInt(mask_str[i], 2) & ip_ipart[i];
            first_addrs += temp;    first_addrs += ".";

            temp = Integer.parseInt(mask_str[i], 2) | ip_ipart[i];
            last_addrs += temp;    last_addrs += ".";

        }
        System.out.println("Subnet mask in Decimal : " + mask_deci); 

        System.out.println("\nTOTAL ADDREASSES POSIBLE ARE :" + posible_adrss);

        System.out.println("\nFirst address for networks is ---> " + first_addrs); 
        System.out.println("\nLast address for networks is ---> "+ last_addrs );
    }

    static String one(int x){
        String xx = "11111111";
        return xx.substring(0, x);
    }
    static String zero(int x){
        String xx = "00000000";
        return xx.substring(0, x);
    }
    
    
}
