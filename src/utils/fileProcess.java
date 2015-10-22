package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import study.Demo;
import study.fileDemo;




public class fileProcess{
	
	public static void readfile(String filePath){
		try{
			String encoding="GBK";
			File file=new File(filePath);
			if(file.isFile() & file.exists()){
				InputStreamReader read=new InputStreamReader(new FileInputStream(file),encoding);
				BufferedReader bufferReader=new BufferedReader(read);
				String linetxt=null;
				int count=0;
				while(bufferReader.readLine()!=null){
					linetxt=bufferReader.readLine();
					
				}
				
			}
			
			
		}catch(Exception e){
			System.out.println("ÎÄ¼þ³ö´í£¡");
		}
		
	}
	
	public static void writefile(String filePath){
		
		
	}
	
	public static void main(String[] args){
		String filepath="...";
		fileDemo demo=new fileDemo();
		fileProcess fileprocess=new fileProcess();
		fileProcess.readfile(filepath);
	}
	
}