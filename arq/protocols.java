
import java.lang.*;
import java.util.Scanner;

public class protocols {

    public static int totalframe=0, frsize=0, cnt = 0;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {

        //int totalframe, frsize, cnt = 0;
        

        System.out.print("\nEnter the Total number of frames : ");
        totalframe = sc.nextInt();
        //totalframe+=2;
        System.out.print("Enter the Window Size : ");
        frsize = sc.nextInt();
        //frsize+=2;
        int ch;
        do {
            System.out.print("\n\t\t1.Go back N\n\t\t2.Selective repeat\n\t\t3.Exit\nEnter your choice  :\t");
            ch = sc.nextInt();
            switch (ch) {
                case 1: {
                    Go_back_n();
                    break;
                }
                case 2: {
                    Selective_Repeat();
                    break;
                }
                case 3: {
                    System.out.println("Exitting........");
                    break;
                }

            }
        } while (ch != 3);
        sc.close();
    }

    static void Go_back_n(){

              int[] send = new int[totalframe*10];
            	int[] recieve = new int[totalframe*10];
            	int[] retransmitted = new int[totalframe*10];
            	int i=0;
 				 for(int m=1;m<=totalframe;m++)
 				 {
  					retransmitted[m]=-1;
				  }
                  while (i <= totalframe) {
                    int z = 0;
                    for (int k = i; k < i + frsize && k <= totalframe; k++) {
                        System.out.println("Sending Frame " + k + "...");     
                        send[k]=k;
                    }
                    for (int k = i;k <= totalframe; k++) {
                      
                      char ch;
                      System.out.print("\nDo you want to send the Acknowledgment for frame " + k + " (y/n): ");
                      ch = sc.next().charAt(0);
                     
                      if (ch=='y') {
                        System.out.println("Positive Acknowledgment for Frame " + k + "...");
                        recieve[k]=k;
                        if((k+frsize)<=totalframe)
                        {
                        	System.out.println("Sending Frame  " + (k+frsize) +"...");
                        	send[k+frsize]=k+frsize;
                        }
                        z++;
                      } else {
                        System.out.println("Negative Acknowledgement for Frame Number : " + k );
                        for(int m=k; m < k+frsize && m<=totalframe;m++)
                        {
                        	System.out.println("Retransmitting Frame " + m);
                        	retransmitted[m]=m;
                        }
                        
                        k=k-1;
                      }
                    }
                    System.out.println(); 
                    i = i + z;
                  }
                  System.out.println("\nThe frames that are sended :");
                  for(int m=1;m<=totalframe;m++)
                  {
                    System.out.println(send[m] + " ");
                  }
                  
                  System.out.println("\nThe frames that are recieved are :");
                  for(int m=1;m<=totalframe;m++)
                  {
                    System.out.println(recieve[m] + " ");
                  }
                  
                  System.out.println("\nThe frames that are Retransmitted are :");
                  for(int m=1;m<=totalframe;m++)
                  {
                    if(retransmitted[m] == -1){
             		
                    }else{
                      System.out.println(retransmitted[m] + " ");
                    }
                  }

    }

    static void Selective_Repeat(){

              int[] send = new int[totalframe*10];
              int[] recieve = new int[totalframe*10];
              int[] retransmitted = new int[totalframe*10];
            	int i=0;
              for(int m=1;m<=totalframe;m++)
              {
                  retransmitted[m]=-1;
                }
                 while (i <= totalframe) {
                      int z = 0;
                      for (int k = i; k < i + frsize && k <= totalframe; k++) {
                        System.out.println("Sending Frame " + k + "..." );     
                        send[k]=k;
                      }
                      for (int k = i;k <= totalframe; k++) {
            
                          char ch;
                          System.out.print("\nDo you want to send the Acknowledgment for Frame "+ k + " (y/n): ");
                          ch = sc.next().charAt(0);
              
                          if (ch=='y') {
                                System.out.println("Positive Acknowledgment for Frame "+ k + "...");
                                    recieve[k]=k;
                                if((k+frsize)<=totalframe)
                                {
                                    System.out.println("Sending Frame  " + (k+frsize) + "...");
                                  send[k+frsize]=k+frsize;
                                }
                                z++;
                          } else {
                                System.out.println("Negative Acknowledgement for Frame Number : "+ k );
                                System.out.println("Retransmitting Frame " + k );
                                retransmitted[k]=k;
                                k=k-1;
                          }
                      }
                      System.out.println();
                      i = i + z;
             }
             System.out.println("\nThe frames that are sended :");
             for(int m=1;m<=totalframe;m++)
             {
                System.out.println(send[m] + " ");
            }
  
                System.out.println("\nThe frames that are recieved are :");
            for(int m=1;m<=totalframe;m++)
             {
            	System.out.println(recieve[m] + " ");
             }
  
             System.out.println("\nThe frames that are Retransmitted are :");
             for(int m=1;m<=totalframe;m++)
             {
             	if(retransmitted[m] == -1){
             		
                }else{
                  System.out.println(retransmitted[m] + " ");
                }
            }

    }

}