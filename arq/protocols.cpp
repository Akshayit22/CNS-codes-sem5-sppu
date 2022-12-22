#include<iostream>
using namespace std;

class protocols{
	
 public :
            void Selective_Repeat(int i, int frsize, int totalframe, int cnt) {

            	int send[totalframe];
            	int recieve[totalframe];
            	int retransmitted[totalframe];
            	
              for(int m=1;m<=totalframe;m++)
              {
                  retransmitted[m]=-1;
                }
                 while (i <= totalframe) {
                      int z = 0;
                      for (int k = i; k < i + frsize && k <= totalframe; k++) {
                          cout << "Sending Frame " << k << "..." << endl;     send[k]=k;
                      }
                      for (int k = i;k <= totalframe; k++) {
            
                          char ch;
                          cout<<"\nDo you want to send the Acknowledgment for Frame "<<k<<" (y/n): ";
                          cin>>ch;
              
                          if (ch=='y') {
                                    cout << "Positive Acknowledgment for Frame " << k << "..." << endl;
                                    recieve[k]=k;
                                if((k+frsize)<=totalframe)
                                {
                                  cout<<"Sending Frame  "<<k+frsize<<"..."<<endl;
                                  send[k+frsize]=k+frsize;
                                }
                                z++;
                          } else {
                              cout << "Negative Acknowledgement for Frame Number : " << k <<endl;
                              cout << "Retransmitting Frame "<<k<< endl;
                              retransmitted[k]=k;
                              k=k-1;
                          }
                      }
                      cout << "\n";
                      i = i + z;
             }
            cout<<"\nThe frames that are sended :";
             for(int m=1;m<=totalframe;m++)
             {
  	            cout<<send[m]<<" ";
            }
  
                cout<<"\nThe frames that are recieved are :";
            for(int m=1;m<=totalframe;m++)
             {
            	cout<<recieve[m]<<" ";
             }
  
             cout<<"\nThe frames that are Retransmitted are :";
             for(int m=1;m<=totalframe;m++)
             {
             	if(retransmitted[m] == -1){
             		
				}else{
					cout<<retransmitted[m]<<" ";
				}
             }
  
        }
        
        void Go_back_n(int i, int frsize, int totalframe, int cnt) {
            	int send[totalframe];
            	int recieve[totalframe];
            	int retransmitted[totalframe];
            	
 				 for(int m=1;m<=totalframe;m++)
 				 {
  					retransmitted[m]=-1;
				  }
                  while (i <= totalframe) {
                    int z = 0;
                    for (int k = i; k < i + frsize && k <= totalframe; k++) {
                      cout << "Sending Frame " << k << "..." << endl;     send[k]=k;
                    }
                    for (int k = i;k <= totalframe; k++) {
                      
                      char ch;
                      cout<<"\nDo you want to send the Acknowledgment for frame "<<k<<" (y/n): ";
                      cin>>ch;
                     
                      if (ch=='y') {
                        cout << "Positive Acknowledgment for Frame " << k << "..." << endl;
                        recieve[k]=k;
                        if((k+frsize)<=totalframe)
                        {
                        	cout<<"Sending Frame  "<<k+frsize<<"..."<<endl;
                        	send[k+frsize]=k+frsize;
                        }
                        z++;
                      } else {
                        cout << "Negative Acknowledgement for Frame Number : " << k <<endl;
                        for(int m=k; m < k+frsize && m<=totalframe;m++)
                        {
                        	cout << "Retransmitting Frame "<<m<< endl;
                        	retransmitted[m]=m;
                        }
                        
                        k=k-1;
                      }
                    }
                    cout << "\n";
                    i = i + z;
                  }
                  cout<<"\nThe frames that are sended :";
                  for(int m=1;m<=totalframe;m++)
                  {
                  	cout<<send[m]<<" ";
                  }
                  
                  cout<<"\nThe frames that are recieved are :";
                  for(int m=1;m<=totalframe;m++)
                  {
                  	cout<<recieve[m]<<" ";
                  }
                  
                  cout<<"\nThe frames that are Retransmitted are :";
                  for(int m=1;m<=totalframe;m++)
                  {
                  	cout<<retransmitted[m]<<" ";
                  }
  
            }


};

int main() {
  protocols gbn;
  int totalframe, frsize, cnt = 0;
 
  cout << "Enter the Total number of frames : ";
  cin >> totalframe;
  cout << "Enter the Window Size : ";
  cin >> frsize;
  int i = 1,ch;
  do{
      cout<<"\n1.Go back N\n2.SElective repeat\n3.Exit\nEnter your choice :"<<endl;
      cin>>ch;
      switch(ch)
      {
          case 1:{
               gbn.Go_back_n(i, frsize, totalframe, cnt);
               break;
          }
          
          case 2:
              {
                gbn.Selective_Repeat(i, frsize, totalframe, cnt);  
                 break;
              }
          case 3:
              {
                  cout<<"Exitting....";
                  break;
              }
      
        
        
      }
      }while (ch!=3);
  return 0;
}
