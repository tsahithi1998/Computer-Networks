//package comp_networks;


	//package comp_networks;

	import java.io.*;
	import java.net.*;
	import java.util.*;

	public class CRC_client
 {

		private Socket socket =null;
		private BufferedReader input=null;
		private DataOutputStream out =null;
		private DataInputStream inp=null;
		public CRC_client(String address,int port)
		{
			try
			{
				socket = new Socket(address,port);
				System.out.println("connected");
				input =new BufferedReader(new InputStreamReader(System.in));
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
				while(true)
				{
				
				char[] check= {'1','1','0','1'};
				int i,j,k,z,m;
				String str1,str2;
				Scanner s=new Scanner(System.in);
				String str,temp;
				System.out.println("enter the message");
				str=s.next();
				out.writeUTF(str);
				char[][] binary=new char[str.length()][10];
				char[] res=new char[4];
				char par='1';
				char[] c=str.toCharArray();
				for(i=0;i<c.length;i++)
				{
					temp=Integer.toBinaryString(c[i]);
					StringBuilder sb=new StringBuilder(temp);
					sb.append("000");
					temp=sb.toString();
					binary[i]=temp.toCharArray();
				}
				for(i=0;i<str.length();i++)
				{
					for(j=0;j<4;j++)
					{
						res[j]=binary[i][j];
						//System.out.println(res[j]+"asas");
					}
					
					z=4;
					m=6;
					while(m!=0)
					{
						if(par=='1')
						{
						for(k=0;k<4;k++)
						{
							if(res[k]==check[k])
								res[k]='0';
							else
								res[k]='1';
						}
						}
						
						str1=String.valueOf(res);
						//System.out.println(str1);
						StringBuilder sb1=new StringBuilder(str1);
						str1=sb1.append(binary[i][z]).toString();
						z++;
						m--;
						str2=str1.substring(1);
						//System.out.println(str2);
						res=str2.toCharArray();
						par=res[0];
					}
					if(par=='1')
						for(k=0;k<4;k++)
						{
							if(res[k]==check[k])
								res[k]='0';
							else
								res[k]='1';
						}
					String str4=String.copyValueOf(res);
					String str5=str4.substring(1);
					//System.out.println(str5);//required remainder
					char[] fin=str5.toCharArray();
					binary[i][7]=fin[0];
					binary[i][8]=fin[1];
					binary[i][9]=fin[2];
					
				}
				char[][] binary2=new char[str.length()][10];
				for(i=0;i<str.length();i++)
				{
					for(j=0;j<10;j++)
					{
						binary2[i][j]=binary[i][j];
					}
				}
				if(binary2[1][3]=='1')
					binary2[1][3]='0';
				else
					binary2[1][3]='1';
				int r;
				Random rand=new Random();
				r=rand.nextInt(20);
				System.out.println(""+r);
				if(r%2==0)
				{
				for(i=0;i<str.length();i++)
				{
					out.writeUTF(String.valueOf(binary[i]));
				}
				}
				else
				{
					for(i=0;i<str.length();i++)
					{
						out.writeUTF(String.valueOf(binary2[i]));
					}
				}
				String aa=inp.readUTF();
				if(aa.equals("done"))
					break;
					
				//}
			}
				
			}
				catch(Exception e)
				{
					System.out.println(e);
				}
			
			//}
			try
			{
				input.close();
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
			CRC_client c=new CRC_client("127.0.0.1",5000);

		}



	}


