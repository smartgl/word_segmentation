package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Userdict{
	
	public static String RegParse(String txt){
		Pattern pattern=Pattern.compile("[\u4e00-\u9fa5]+");
		Matcher match=pattern.matcher(txt);
//		if(match.find()){
//			System.out.println(match.group(0));
//		}
		Pattern pattern2=Pattern.compile("\\s[a-z]{1,2}\\s");
		Matcher match2=pattern2.matcher(txt);
		if(match.find() & match2.find()){
			String x=match.group()+match2.group();
			System.out.println(x);
			return x;
		}else{
			return "null";
		}
		
	}
	
	public static void ReadtxtFile(String filePath){
		try{
			String encoding="GBK";
			File file=new File(filePath);
			if(file.isFile() & file.exists()){
				InputStreamReader read=new InputStreamReader(new FileInputStream(file),encoding);
				BufferedReader bufferReader=new BufferedReader(read);
				FileWriter filewriter=new FileWriter("E://newdic.txt");
				String linetxt=null;
				int count=0;
				while((linetxt=bufferReader.readLine())!=null){
					//System.out.println(linetxt);
					//count++;
					//System.out.println(count);
					String x=RegParse(linetxt);	
					filewriter.write(x+"\n");
				}
				read.close();
				filewriter.flush();
				filewriter.close();
			}
			
		}catch(Exception e){
			System.out.println("文件出错！");
			e.printStackTrace();
		}
	}
	
//	public static void WritetxtFile(){
//		try{
//			FileWriter filewriter=new FileWriter("E://newdic.txt");
//			filewriter.write(RegParse().);
//		}catch(Exception e){
//			System.out.println("写文件出错！");
//		}
//	}
	
	
	public static void main(String []args){
		//Userdict dict=new Userdict();
		String file_path="E://userdic.txt";
		ReadtxtFile(file_path);
	}
	
}