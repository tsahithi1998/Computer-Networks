//package comp_networks;
import java.net.*;
import java.util.Scanner;
import java.io.*;
public class hamming_server {

	private Socket socket=null;
	private ServerSocket server=null;
	private DataInputStream input=null;
	private DataOutputStream out =null;
	public hamming_server(int port)
	{
		
		try
		{
			int[] e1= {0,2,4,6,8,10};
			int[] e2= {1,2,5,6,9,10};
			int[] e3= {3,4,5,6};
			int[] e4= {7,8,9,10};
			char[] error =new char[4];
		server=new ServerSocket(port);
		socket =server.accept();
		System.out.println("client accepted");
		input=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		out=new DataOutputStream(socket.getOutputStream());
		String str;
		str=input.readUTF();
		int len=str.length();
		int i,flag=0,j;
		char[][] res=new char[len][11];
		//char[][] res1=new char[len][11];
		for(i=0;i<len;i++)
		{
			str=input.readUTF();
			res[i]=str.toCharArray();
		}
		System.out.println("the data that has been received");
		for(i=0;i<len;i++)
		{
			for(j=0;j<11;j++)
			{
				System.out.print(""+res[i][j]);
			}
			System.out.println("");
		}
		for(i=0;i<len;i++)
		{
		error[3]=find_r(res[i],e1);
		error[2]=find_r(res[i],e2);
		error[1]=find_r(res[i],e3);
		error[0]=find_r(res[i],e4);
		String w=String.copyValueOf(error);
		int dec=Integer.parseInt(w, 2);
		//System.out.println(""+dec+" "+w);
		if(dec!=0)
		{
			//System.out.println(""+dec+" "+w);
			flag=1;
			System.out.println("there is an error in the frame "+i+" at "+dec+" th bit");
			if(res[i][dec-1]=='0')
				res[i][dec-1]='1';
			else
				res[i][dec-1]='0';
		}
		
		}
		if(flag==0)
		{
			System.out.println("there is no error in yha data");
		}
		else
		{
			System.out.println("after error correction");
			for(i=0;i<len;i++)
			{
				for(j=0;j<11;j++)
				{
					System.out.print(""+res[i][j]);
				}
				System.out.println("");
			}
		}
		
		
		
		
		System.out.println("closing connection");
		input.close();
		socket.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	public static void main(String[] args) {
		
			hamming_server s=new hamming_server(5000);
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
