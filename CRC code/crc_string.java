import java.lang.*;
import java.util.*;

public class crc_string{

    public static Scanner sc = new Scanner(System.in);

    public static void main(String args[]){

        int temp,dataLength,divisorLenght;
        String str="",str2="",s="";
        
        System.out.println("AT SENDERS SIDE :\nEnter data to be send : ");
        str = sc.next();
        dataLength = str.length();
        
        
        System.out.println("Enter divisor : ");
        str2 = sc.next();
        divisorLenght = str2.length();
        
        for(int i = 0; i < (divisorLenght - 1);i++){
            str += "0";
        }
        dataLength = str.length();
        System.out.println("\nData entered  : " + str);
        System.out.println( "Divisor Enteres : " + str2 +"\n");
    
        // main logic 

        String res="", tmpDiv = str2 , tmpData = str.substring(0,divisorLenght);
        int x = divisorLenght;

        while( x < dataLength ){
            System.out.print(x +"  "+tmpData+ "  ");
            tmpData = xorCompare(tmpData,tmpDiv);
            tmpData += str.charAt(x);
            x++; 
            //System.out.print(x +"  "+tmpData+ "  ");
        }
        System.out.print("	");
        res = str.substring(0,4) + xorCompare(tmpData , tmpDiv);
        System.out.println(res); 

        //receiverSide();
        String recvedData = res;
        System.out.println("Data recived : " + res + "\n");
        //recvedData = sc.next();

        String ress=""; tmpDiv = str2 ; tmpData = res.substring(0,divisorLenght);
        x = divisorLenght;

        while( x < dataLength ){
            System.out.print(x +"  "+tmpData+ "  ");
            tmpData = xorCompare(tmpData,tmpDiv);
            tmpData += recvedData.charAt(x);
            x++; 
            //System.out.print(x +"  "+tmpData+ "  ");
        }
        ress = str.substring(0,divisorLenght) + xorCompare(tmpData , tmpDiv);
        System.out.println(ress);
        recvedData = xorCompare(tmpData , tmpDiv);//res.substring(divisorLenght);

        int flag=0;
        for(int i=0;i<divisorLenght-1;i++){
            if(recvedData.charAt(i) == '1'){
                flag = 1;
            }
        }
        if(flag == 0){
            System.out.println("\n NO error found !");
        }else{
            System.out.println("\n Error found !");
        }


    }
    static String xorCompare(String one , String two){
        String res = "";

        if(one.charAt(0) == '0'){ // first bit is zero so xor with 4 zeros 
            res = one.substring(1); // last tree bit are returned
            System.out.println("0    "+res);
            return res;
        }
        
        for(int i=0;i<two.length();i++){
            if( one.charAt(i) == two.charAt(i) ){// xor logic 
                res += "0";
            }
            else{
                res += "1";
            }
        }
        System.out.println("1    "+res.substring(1));
        return res.substring(1);


    }
    


} 
