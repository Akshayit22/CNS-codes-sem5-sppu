import java.lang.*;
import java.util.*;


class hamming {
	final static int dataSize = 7;
	public static Scanner sc = new Scanner(System.in);
	public static int[] data = new int[dataSize+1];
	public static int[] dataR = new int[dataSize+1];
	public static int[][] parityArr = new int[][]{{1,3,5,7},{2,3,6,7},{4,5,6,7}};
	

	public static void main(String args[]){

		int dataLength,j=0,l=0;
		String SenderData="",ReceiverData="",res="";
		
		System.out.print("Enter data: ");
		SenderData = sc.next();
		dataLength = SenderData.length();
		l = (dataLength-1);

		for(int i = 1; i <= dataSize ; i++){
			if(i == Math.pow(2,j)){	
				j++;
				data[i] = 8;
			}
			else{
				if(SenderData.charAt(l)=='1'){
					data[i] = 1;
				}else{
					data[i] = 0;
				}l--;
			}
		}
		printData(data);
		
		j=0;
		int parity=0;
		int[] parites = new int[dataLength];

		for(int i = 1; i <= dataSize ; i++){
			if(i == Math.pow(2,j)){	j++;
				for(int k=(dataLength-1);k>0;k--){
					parity += data[parityArr[j-1][k]];
					//System.out.println(j+"	"+data[parityArr[j-1][k]]);
				}
				parites[j-1] = (parity%2);
				System.out.println("P-bit	P"+j+"  : "+parites[j-1]); 
				data[i] = parites[j-1];
			}
			parity=0;
		}
		printData(data);

		// receiver side 
		System.out.print("Enter data at receiver : ");
		ReceiverData = sc.next();l=0;

		for(int i = dataSize; i > 0 ; i--){
			if(ReceiverData.charAt((l))=='1'){
				dataR[i] = 1;
			}else{
				dataR[i] = 0;
			}l++;
			
		}
		printData(dataR);

		j=0;
		parity = 0;
		for(int i = 1; i <= dataSize ; i++){
			if(i == Math.pow(2,j)){	
				j++;
				for(int k=0;k<dataLength;k++){
					parity += dataR[parityArr[j-1][k]];
					//System.out.println(k+"	"+dataR[parityArr[j-1][k]]);
				}
				parites[j-1] = (parity%2);
				System.out.println("P-bit	P"+j+"  : "+parites[j-1]); 
				
			}
			parity=0;
		}
		for(int k=(dataLength-2);k>=0;k--){
			if(parites[k] == 1){
				res += '1';
			}else{
				res += '0';
			}
		}

		int errIdx = Integer.parseInt(res,2);
		if(errIdx == 0){
			System.out.println("Error NOT found in data");
		}
		else{
			System.out.print("\nError found at index : "); 
			System.out.println(errIdx);
			System.out.println("\nAfter correcting error : ");
			if(dataR[errIdx] == 1){dataR[errIdx] = 0; }
			else{ dataR[errIdx] = 1; }
		}

		printData(dataR);


	}//end of main

	static void printData(int[] arr){

		System.out.print("\nIndex : ");
		for(int i = dataSize; i>0; i--)
			System.out.print(i+" ");

		System.out.print("\nData  : ");
		for(int i = dataSize; i>0; i--)
			System.out.print(arr[i]+" ");
		System.out.println();
	}
}
