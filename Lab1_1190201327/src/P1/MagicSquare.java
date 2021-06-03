package P1;
import java.util.*;
import java.io.*;

public class MagicSquare {
	private static boolean isLegalNumber(String number)
	{
		if(number.matches("[1-9]+[0-9]*")) return true;
		else return false;
	}
	private static boolean isLegalMagicSquare(String fileName)throws Exception//new Scanner���׳�һ��IO�쳣
	{		
		//�ж��Ƿ���ǺϷ��ַ� ��Ҫ�����ж�\t
		InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName),"UTF-8");
		BufferedReader br = new BufferedReader(reader);
		String line = "";
		int rownumber [] = new int[1000];//׼����ÿһ�����������Ƿ����
		int rowi = 0;
		int coln = 0;
		int [][] magis = new int[10000][10000];
		int [] sumrow = new int[10000];
		int [] sumcol = new int[10000];
		int [] sumdia = new int[2];
		while((line = br.readLine()) != null)
		{
			String number[] = line.split("\t");
			int i = 0;
			int cnt = number.length;
			rownumber[rowi]  = cnt;
			if(rownumber[rowi] != rownumber[0])
			{
				System.out.printf("���󲻷���Ҫ��");
				return false;
			}
			while(i < cnt && number[i] != null)
			{
				if(isLegalNumber(number[i]))
					{
						magis[coln][i] = Integer.parseInt(number[i]);
						i++;
					}
				else {
					/*
					System.out.println(coln);
					System.out.println(i);
					System.out.printf(number[i]);
					*/
					System.out.printf("���󲻷���Ҫ��");
					return false;
				}
			}
			rowi++;
			coln++;
		}
		if(rownumber[0] != coln)
		{
			System.out.printf("���󲻷���Ҫ��");
			return false;
		}
		br.close();
		//�������ǿ��Ƿ��ǻ÷�
		int k, j;
		for(k = 0; k < coln; k++)
		{
			for(j = 0; j < coln; j++)
			{
				sumrow[k] += magis[k][j]; 
				sumcol[j] += magis[k][j];
				if(k == j)
				{
					sumdia[0] += magis[k][j];
				}
				if(k + j + 2 == coln + 1)
				{
					sumdia[1] += magis[k][j];
				}
			}
		}
		for(k = 0; k < coln; k++)
		{
			if(sumrow[0] != sumrow[k])
			{
				return false;
			}
		}
		for(k = 0; k < coln; k++)
		{
			if(sumcol[0] != sumcol[k])
			{
				return false;
			}
		}		
		if(sumcol[0] != sumrow[0] || sumcol[0] != sumdia[0] || sumcol[0] != sumdia[1])
		{
			return false;
		}
		return true;
	}
	private static boolean generateMagicSquare(int n) throws IOException
	{
		
		if(n % 2 == 0 || n < 0)
		{
			System.out.printf("n������Ҫ��");
			return false;
		}
		
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("src/P1/txt/6.txt"), "UTF-8");
		BufferedWriter bw = new BufferedWriter(writer);
		int magic[][] = new int[n][n];
		int row = 0, col = n / 2, i, j, square = n * n;
		for (i = 1; i <= square; i++) 
		{ 
			magic[row][col] = i;
			if (i % n == 0) 
				row++;
			else 
			{
				if (row == 0) 
					row = n - 1;
				else
					row--;
				if (col == (n - 1)) 
					col = 0;
				else
					col++;
			}
		}
		for(i = 0; i < n; i++)
		{
			for(j = 0; j < n; j++)
			{
				bw.write(Integer.toString(magic[i][j]));
				if (j < n - 1) bw.write("\t");
			}
			if(i < n - 1) bw.newLine();
			bw.flush();
		}
		
		for (i = 0; i < n; i++) 	
		{ 
			for (j = 0; j < n; j++)
				System.out.print(magic[i][j] + "\t"); 
			System.out.println();
		}
		bw.close();
		
		return true;
	}
	
	
	public static void main(String[] args)throws Exception
	{
		//P1�ĵ�һ����
		
		int x = 0;
		String name[] = new String[] {"src/P1/txt/1.txt",
									"src/P1/txt/2.txt",
									"src/P1/txt/3.txt",
									"src/P1/txt/4.txt",
									"src/P1/txt/5.txt",
									"src/P1/txt/6.txt"};
		while(x < 6)
		{
			if(isLegalMagicSquare(name[x]))
			{
				System.out.println("��" + (x + 1) + "���ǻ÷�");
			}
			else
			{
				System.out.println("��" + (x + 1) + "�����ǻ÷�");
			}
			x++;
		}
		return ;
		
		//P1�ĵڶ�����
		//generateMagicSquare(17);
	}
}
