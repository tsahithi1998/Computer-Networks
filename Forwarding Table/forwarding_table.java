//package comp_networks;

import java.util.*;
import java.lang.*;
public class forwarding_table {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int no_subnets=0,no_hosts=0,m=0;
		String subnet_mask="",first="",last="",subnet_addr="";
		String adress,clas,address,broad="255.255.255.255";
		int[] octect=new int[4];
		int[] subnet=new int[4];
		int i=0,subnets,net_id=0;
		String nid="empty";
		Scanner s=new Scanner(System.in);
		System.out.println("enter the ip address");
		address=s.next();
		String[] res=address.split("/", 2);
		StringTokenizer st=new StringTokenizer(res[0],".");
		while(st.hasMoreTokens())
		{
			octect[i]=Integer.parseInt(st.nextToken());
			i++;
		}
		clas=find_clas(octect[0]);
		
			nid=res[1];
		
		String sample;
		int x,temp,dec,a,b,dec1,dec2,b1;
		if(clas.equals("A"))
		{
			temp=24;
			if(nid.equals(""))
			no_subnets=0;
			else
			{
				net_id=Integer.parseInt(nid);
				m=net_id-8;
				no_subnets=(int)Math.pow(2, m);
				//System.out.println(""+Math.pow(m, 2)+m);
			}
			no_hosts=(int)Math.pow(2,(int)24-m);
			StringBuilder sb=new StringBuilder("");
			x=net_id-8;
			while(temp!=0)
			{
				while(x!=0)
				{
					sb.append("1");
					x--;
					temp--;
				}
				sb.append("0");
				temp--;
			}
			sample=sb.toString();
			
			dec=Integer.parseInt(sample.substring(0, 8),2);
			dec1=Integer.parseInt(sample.substring(8,16),2);
			dec2=Integer.parseInt(sample.substring(16),2);
			//System.out.println(sample+"  "+sample.substring(0, 8)+"  "+sample.substring(8));
			subnet[0]=octect[0]&255;
			subnet[1]=octect[1]&dec;
			subnet[2]=octect[2]&dec1;
			subnet[3]=octect[3]&dec2;
			a=subnet[3]+1;
			b=no_hosts/255;
			b1=b/255;
			subnet_mask="255"+"."+Integer.toString(dec)+"."+Integer.toString(dec1)+"."+Integer.toString(dec2);
			
			subnet_addr=Integer.toString(subnet[0])+"."+Integer.toString(subnet[1])+"."+Integer.toString(subnet[2])+"."+Integer.toString(subnet[3]);
			first=Integer.toString(subnet[0])+"."+Integer.toString(subnet[1])+"."+Integer.toString(subnet[2])+"."+Integer.toString(a);
			if(b1>1)
			{
			last=Integer.toString(subnet[0])+"."+Integer.toString(b1)+"."+"255"+"."+"254";
			}
			else if(b>1)
			{
			last=Integer.toString(subnet[0])+"."+Integer.toString(subnet[1])+"."+Integer.toString(b)+"."+"254";
			}
			else
			{
			b=subnet[3]+no_hosts-2;
			last=Integer.toString(subnet[0])+"."+Integer.toString(subnet[1])+"."+Integer.toString(subnet[2])+"."+Integer.toString(b);
			}
		}
		if(clas.equals("B"))
		{
			temp=16;
			if(nid.equals(""))
			no_subnets=0;
			else
			{
				net_id=Integer.parseInt(nid);
				m=net_id-16;
				no_subnets=(int)Math.pow(2, m);
				//System.out.println(""+Math.pow(m, 2)+m);
			}
			no_hosts=(int)Math.pow(2,(int)16-m);
			StringBuilder sb=new StringBuilder("");
			x=net_id-16;
			while(temp!=0)
			{
				while(x!=0)
				{
					sb.append("1");
					x--;
					temp--;
				}
				sb.append("0");
				temp--;
			}
			sample=sb.toString();
			
			dec=Integer.parseInt(sample.substring(0, 8),2);
			dec1=Integer.parseInt(sample.substring(8),2);
			//System.out.println(sample+"  "+sample.substring(0, 8)+"  "+sample.substring(8));
			subnet[0]=octect[0]&255;
			subnet[1]=octect[1]&255;
			subnet[2]=octect[2]&dec;
			subnet[3]=octect[3]&dec1;
			a=subnet[3]+1;
			b=no_hosts/255;
			subnet_mask="255"+"."+"255"+"."+Integer.toString(dec)+"."+Integer.toString(dec1);
			
			subnet_addr=Integer.toString(subnet[0])+"."+Integer.toString(subnet[1])+"."+Integer.toString(subnet[2])+"."+Integer.toString(subnet[3]);
			first=Integer.toString(subnet[0])+"."+Integer.toString(subnet[1])+"."+Integer.toString(subnet[2])+"."+Integer.toString(a);
			if(b>1)
			{
			last=Integer.toString(subnet[0])+"."+Integer.toString(subnet[1])+"."+Integer.toString(b)+"."+"254";
			}
			else
			{
			b=subnet[3]+no_hosts-2;
			last=Integer.toString(subnet[0])+"."+Integer.toString(subnet[1])+"."+Integer.toString(subnet[2])+"."+Integer.toString(b);
			}
		}


		if(clas.equals("C"))
		{
			temp=8;
			if(nid.equals(""))
			no_subnets=0;
			else
			{
				net_id=Integer.parseInt(nid);
				m=net_id-24;
				no_subnets=(int)Math.pow(2, m);
				//System.out.println(""+Math.pow(m, 2)+m);
			}
			no_hosts=(int)Math.pow(2,(int)8-m);
			StringBuilder sb=new StringBuilder("");
			x=net_id-24;
			while(temp!=0)
			{
				while(x!=0)
				{
					sb.append("1");
					x--;
					temp--;
				}
				sb.append("0");
				temp--;
			}
			sample=sb.toString();
			System.out.println(sample);
			dec=Integer.parseInt(sample,2);
			subnet[0]=octect[0]&255;
			subnet[1]=octect[1]&255;
			subnet[2]=octect[2]&255;
			subnet[3]=octect[3]&dec;
			a=subnet[3]+1;
			b=subnet[3]+no_hosts-2;
			subnet_mask="255"+"."+"255"+"."+"255"+"."+Integer.toString(dec);
			subnet_addr=Integer.toString(subnet[0])+"."+Integer.toString(subnet[1])+"."+Integer.toString(subnet[2])+"."+Integer.toString(subnet[3]);
			first=Integer.toString(subnet[0])+"."+Integer.toString(subnet[1])+"."+Integer.toString(subnet[2])+"."+Integer.toString(a);
			last=Integer.toString(subnet[0])+"."+Integer.toString(subnet[1])+"."+Integer.toString(subnet[2])+"."+Integer.toString(b);

			
		}
		System.out.println("ip address belong to class: "+clas);
		System.out.println("no of subnets: "+no_subnets);
		System.out.println("no of hosts: "+no_hosts);
		System.out.println("broadcast address: "+broad);
		System.out.println("subnet mask: "+subnet_mask);
		System.out.println("first host address: "+first);
		System.out.println("last host address: "+last);
		System.out.println("subnet address: "+subnet_addr);
		
		
		
		
	}
public static String find_clas(int add)
{
	//String cla;
	if((add>=1)&&(add<=126))
		return "A";
	else if((add>=128)&&(add<=191))
		return "B";
	else if((add>=192)&&(add<=223))
		return "C";
	return "";
	
	
	
}
}
