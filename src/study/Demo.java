package study;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import WordSegmentation.study.CLibrary;
import com.sun.jna.Native;
import utils.DB;
import utils.Printers;
import utils.Writers;
import utils.Utils;


public class Demo {
	
	public static String reg(String address){
		String new_address=null;
		Pattern pattern1=Pattern.compile("[\u4e00-\u9fa5]+");
		Matcher match1=pattern1.matcher(address);
		if(match1.find()){
			new_address=match1.group();
		}
		return new_address;			
	}
	

	
	public static void main(String[] args) throws Exception {
		// 初始化
		CLibrary instance = (CLibrary) Native.loadLibrary(
				System.getProperty("user.dir") + "\\source\\NLPIR",
				CLibrary.class);
		int init_flag = instance.NLPIR_Init("", 1, "0");
		String resultString = null;
		String resultString2=null;
		if (0 == init_flag) {
			resultString = instance.NLPIR_GetLastErrorMsg();
			System.err.println("初始化失败！\n" + resultString);
			return;
		}

		try {
			/*
			String encoding="GBK";
			File file=new File("d:\\fenci\\phonelist.txt");
			FileWriter filewriter=new FileWriter("d:\\fenci\\phonelist.result.txt");
			if(file.isFile() & file.exists()){
				InputStreamReader read=new InputStreamReader(new FileInputStream(file),encoding);
				BufferedReader bufferReader=new BufferedReader(read);
				String linetxt=null;
				int line=0;
				Hashtable<String, Integer> wordcount=new Hashtable<String,Integer>();
				while((linetxt=bufferReader.readLine())!=null){
					//linetxt=bufferReader.readLine();
					resultString=instance.NLPIR_ParagraphProcess(linetxt,0);
					System.out.println("结果为："+resultString+"\n");
					filewriter.write(resultString+"\n");
					String[] resultString_new=resultString.split(" ");
					for(int i=0;i<resultString_new.length;i++){
						if(wordcount.containsKey(resultString_new[i]))
						{
							wordcount.put(resultString_new[i], wordcount.get(resultString_new[i])+1);
						}else{
							wordcount.put(resultString_new[i],1);
						}
					}
					
					line++;
				}
				Map.Entry[] set = Utils.getSortedHashtableByValue(wordcount);
				//Printers.hashTablePrinter(wordcount);
				Writers.hashTableWritter(wordcount, "D:/fenci/test1.txt");
				
				System.out.println(line);
				filewriter.flush();
				filewriter.close();
			}*/	
			
			
			
			
			//FileWriter filewriter=new FileWriter("E://all.txt");
			DB newdb=new DB();
			//String sql="select distinct ga_ctr from dw_orders where ga_ctr<>'' limit 10";
			//String sql="select distinct init_address from dpf_risk.jsd_user_stat where init_address<>'' and init_address not like '%lat%' limit 10";
			//String sql="select b.uid, b.name from dpf.dw_jsd_user_final_record a left join dpf_risk.jsd_phonelist b on a.userid=b.uid where a.status=2 and a.userid!=10000025";
			String sql="select a.userid, group_concat(b.name) as name from dpf.dw_jsd_user_final_record a left join dpf_risk.jsd_phonelist b on a.userid=b.uid where a.status=2 and a.userid!=10000025 and b.name is not null group by a.userid";
			String sql_all="select uid, group_concat(name) as name from dpf_risk.jsd_phonelist where name is not null and uid<>0 group by uid";
			newdb.RunSqlCmd(sql);
			Hashtable<String, Integer> wordcount=new Hashtable<String,Integer>();
			while(newdb.rs.next()){
				//resultString=instance.NLPIR_ParagraphProcess(newdb.rs.getString("ga_ctr"),1);
				resultString=instance.NLPIR_ParagraphProcess(newdb.rs.getString("name"),0);
				System.out.println("分词结果为：\n"+resultString);	
				String resultString_new[]=resultString.split(" ");
				System.out.println(resultString_new.length);
				Set<String> set=new HashSet<String>();//不允许插入重复元素
				for(int i=0;i<resultString_new.length;i++){
					set.add(resultString_new[i]);
				}
				System.out.println(set.size());
				for(String str:set){
					//System.out.println(str);
					if(wordcount.containsKey(str))
					{
						wordcount.put(str, wordcount.get(str)+1);
					}else{
						wordcount.put(str,1);
					}
					
				}
					
					
				
				//String x=reg(resultString);
//				instance.NLPIR_ImportUserDict(System.getProperty("user.dir")
//						+ "\\source\\userdic.txt");
//				resultString2 = instance.NLPIR_ParagraphProcess(newdb.rs.getString("ga_ctr"), 1);
//				System.out.println("导入用户词典文件后分词结果为：\n" + resultString2);
				//filewriter.write(resultString+"\n");
			}
			java.util.Enumeration<String> e= wordcount.keys();
			while(e.hasMoreElements()){
				String key=e.nextElement();
				int value=wordcount.get(key);
				String sql_insert="insert into dpf_temp.jsd_phonebook_blacklist_antifraud (word, count_antifraud) values ('"+key+"',"+value+")";
				System.out.println(sql_insert);
				newdb.RunInsert(sql_insert);
			}
			
			Map.Entry[] set = Utils.getSortedHashtableByValue(wordcount);
			Writers.hashTableWritter(wordcount, "D:/fenci/all_user_amt.txt");
//			filewriter.flush();
//			filewriter.close();
			
			
			
//			String sInput="融360目前还没有催收团队";
//			resultString2 = instance.NLPIR_ParagraphProcess(sInput, 1);
//			System.out.println("分词结果为：\n " + resultString2);

			
			
			
//			instance.NLPIR_AddUserWord("融360");
//			resultString = instance.NLPIR_ParagraphProcess(sInput, 1);
//			System.out.println("增加用户词典后分词结果为：\n" + resultString);
//
//			instance.NLPIR_DelUsrWord("鹏民");
//			resultString = instance.NLPIR_ParagraphProcess(sInput, 1);
//			System.out.println("删除用户词典后分词结果为：\n" + resultString);
//
//			instance.NLPIR_ImportUserDict(System.getProperty("user.dir")
//					+ "\\source\\userdic.txt");
//			resultString = instance.NLPIR_ParagraphProcess(sInput, 1);
//			System.out.println("导入用户词典文件后分词结果为：\n" + resultString);
//
//			resultString = instance.NLPIR_GetKeyWords(sInput, 10, false);
//			System.out.println("从段落中提取的关键词：\n" + resultString);
//
//			resultString = instance.NLPIR_GetNewWords(sInput, 10, false);
//			System.out.println("新词提取结果为：\n" + resultString);
//
//			Double d = instance.NLPIR_FileProcess("d:\\fenci\\1.txt", "d:\\fenci\\1.result.txt", 1);
//
//			System.out.println("对文件内容进行分词的运行速度为： ");
//			if (d.isInfinite())
//				System.out.println("无结果");
//			else {
//				BigDecimal b = new BigDecimal(d);
//				System.out.println(b.divide(new BigDecimal(1000), 2,
//						BigDecimal.ROUND_HALF_UP) + "秒");
//			}
//			resultString = instance.NLPIR_GetFileKeyWords("D:\\3.txt", 10,
//					false);
//			System.out.println("从文件中提取关键词的结果为：\n" + resultString);

			instance.NLPIR_Exit();

		} catch (Exception e) {
			System.out.println("错误信息：");
			e.printStackTrace();
		}

	}

}

