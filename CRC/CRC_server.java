//package comp_networks;
import java.net.*;
import java.util.Scanner;
import java.io.*;
public class CRC_server {

	private Socket socket=null;
	private ServerSocket server=null;
	private DataInputStream input=null;
	private DataOutputStream out =null;
	public CRC_server(int port)
	{
		int error,len;
		try
		{
		server=new ServerSocket(port);
		socket =server.accept();
		System.out.println("client accepted");
		input=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		out=new DataOutputStream(socket.getOutputStream());
		String str="";
		char[] check= {'1','1','0','1'};
		char[] fin=new char[3];
		int i,j,k,z,m;
		String str1,str2;
		char[] res=new char[4];
		while(true)
		{
			error=0;
			String temp;
			str=input.readUTF();
			int len1=str.length();
			char[][] binary=new char[str.length()][10];
			char par='1';
			i=0;
			while(len1!=0)
			{
				String st=input.readUTF();
				binary[i]=st.toCharArray();
				i++;
				len1--;
			}
			for(i=0;i<str.length();i++)
			{
				for(j=0;j<10;j++)
				{
					System.out.print(""+binary[i][j]);
				}
				System.out.println("");
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
				fin=str5.toCharArray();
				binary[i][7]=fin[0];
				binary[i][8]=fin[1];
				binary[i][9]=fin[2];
				if((fin[0]!='0')||(fin[1]!='0')||(fin[2]!='0'))
					error=1;
		
				
			}
			System.out.println("after decoding the bits are");
			for(i=0;i<str.length();i++)
			{
				for(j=0;j<10;j++)
				{
					System.out.print(""+binary[i][j]);
				}
				System.out.println("");
			}
			
		System.out.println(""+error);
			if(error==1)
			{
				System.out.println("error in frame resend the message");
				out.writeUTF("resend");
			}
				else
			{
				out.writeUTF("done");
			break;
			}

		}
		System.out.println("closing connection");
		input.close();
		socket.close();
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		
	}
	public static void main(String[] args) {
		
			CRC_server s=new CRC_server(5000);
	}


}
