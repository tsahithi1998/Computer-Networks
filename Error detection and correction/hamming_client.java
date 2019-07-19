//package comp_networks;


	//package comp_networks;

	import java.io.*;
	import java.net.*;
	import java.util.*;

	public class hamming_client {

		private Socket socket =null;
		private BufferedReader inn=null;
		private DataOutputStream out =null;
		private DataInputStream inp=null;
		public hamming_client(String address,int port)
		{
			String input,frame;
			int i,r,j;
			int[] r1= {2,4,6,8,10};
			int[] r2= {2,5,6,9,10};
			int[] r3= {4,5,6};
			int[] r4= {8,9,10};
			char a,b,c,d;
			char[] test=new char[11];
			try
			{
				socket = new Socket(address,port);
				System.out.println("connected");
				inn =new BufferedReader(new InputStreamReader(System.in));
				inp=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				out=new DataOutputStream(socket.getOutputStream());
				
			}
			catch(UnknownHostException e)
			{
				System.out.println(e);
			}
			catch(IOException e)
			{
				System.out.println(e);
			}
			
			try
			{
				//while(true)
				//{
				
					Scanner s=new Scanner(System.in);
					System.out.println("enter the message");
					input=s.next();
					out.writeUTF(input);
					char[][] binary=new char[input.length()][7];
					char[][] result=new char[input.length()][11];
					char[][] result1=new char[input.length()][11];
					
					char[] array=input.toCharArray();
					for(i=0;i<array.length;i++)
					{
						frame=Integer.toBinaryString(array[i]);
						binary[i]=frame.toCharArray();
					}
					for(i=0;i<input.length();i++)
					{
						r=0;
						j=0;
						while(j!=7)
					 {
						 if((r==0)||(r==1)||(r==3)||(r==7))
						 {
							 r++;
							 
						 }
						 else
						 {
							 //System.out.println(""+r+i+j);
							 test[r]=binary[i][j];
							 //System.out.println(""+test[r]+"value of r"+r);
							 r++;
							 j++;
							 
						 }
					 }
						a=find_r(test,r1);
						b=find_r(test,r2);
						c=find_r(test,r3);
						d=find_r(test,r4);
						//System.out.println(a+" a"+b+"b"+c+"c"+d+"r values");
						test[0]=a;
						test[1]=b;
						test[3]=c;
						test[7]=d;
						for(int k=0;k<test.length;k++)
							result[i][k]=test[k];
						
					}
					for(i=0;i<input.length();i++)
					{
						for(j=0;j<11;j++)
						{
							result1[i][j]=result[i][j];
						}
						
					}
					
					Random rand=new Random();
					int q=rand.nextInt(40);
					int change=rand.nextInt(8);
					int change1=rand.nextInt(input.length());
					if(result1[change1][change]=='1')
						result1[change1][change]='0';
					else
						result1[change1][change]='1';
					System.out.println(""+change1+"  "+change);
					if(q%2==0)
					{
						System.out.println(q+"");
					for(i=0;i<input.length();i++)
					{
						String str=String.copyValueOf(result[i]);
						out.writeUTF(str);
					}
					}
					else if(q%2==1)
					{
						System.out.println(q+"");
						for(i=0;i<input.length();i++)
						{
							String str=String.copyValueOf(result1[i]);
							out.writeUTF(str);
						}

					}

			//}
				
			}
				catch(Exception e)
				{
					System.out.println(e);
				}
			
			//}
			try
			{
				inn.close();
				out.close();
				socket.close();
			}
			catch(IOException e)
			{
				System.out.println(e);
			}
			
		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			hamming_client c=new hamming_client("127.0.0.1",5000);

		}

		public static char find_r(char[] sample,int[] r)
		{
			int i=0,e=0;
			char parity;
			for(i=0;i<r.length;i++)
			{
				if(sample[r[i]]=='1')
					e++;
			}
			if(e%2==0)
				parity='0';
			else
				parity='1';
			return parity;
		}

	}



