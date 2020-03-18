package mapred.basic;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//Mapper - 데이터를 분류하는 역할
//1. Mapper클래스를 상속한다.
// 	 => mapper에 전달될 input데이터의 key,value타입과 
//		mapper의 실행결과로 출력되는 output데이터의 key,value타입을 정의

//2. map메소드를 오버라이딩해서 map작업을 수행하면서 처리할 내용을 구현
//	=> 입력된 값을 분석하기 위한 메소드 : 입력된 데이터에 조건을 적용해서 
//								 원하는 데이터를 추출하기 위한 반복작업 수행
// map메소드의 매개변수 - 입력데이터 키 , 입력값, Context
//									   --------
//Context->맵리듀스 작업을 수행하며 맵메소드의 실행결과 즉, 출력데이터를 기록하고 shuffle하고 
// 		     리듀서로 내보내는 작업을 수행하는 객체
//		   프레임워크 내부에서 기본작업을 처리하는 객체
//			내부에서 머신들끼리 통신할때 필요한 여러가지 정보를 갖고 있다.
public class CommentwordCountMapper 
		extends Mapper<LongWritable, Text, Text, IntWritable> {
	//output데이터를 mapper의 실행결과로 내보낼 수 있도록 키와 value를 저장하는 변수 선언
	//output데이터의 value는 무조건 1이므로 상수로 정의
	static final IntWritable outputVal =new IntWritable(1);
	//output데이터의 key는 문자열이므로 Text타입으로 정의
	Text outputKey = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, 
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		String[] line = value.toString().split(",");
		Pattern p = Pattern.compile("은|는|이|가|요|서");
		
		StringTokenizer st = new StringTokenizer(line[1]);
		StringBuffer sb = new StringBuffer();
		while(st.hasMoreTokens()) {	
			String next= st.nextToken();
			Matcher m = p.matcher(next);
			if(m.find()) {
				m.appendReplacement(sb, "");
				if(!sb.toString().equals("")) {
					outputKey.set(sb.toString());
					context.write(outputKey, outputVal);
				}
				
			}else {
				m.appendTail(sb);
			}
			if(!sb.toString().equals("")) {
			outputKey.set(sb.toString());
			context.write(outputKey, outputVal);
			}sb.replace(0, sb.length(), "");
		}			
			
			
			
		
//		StringTokenizer st = new StringTokenizer(value.toString());
//		
//		while(st.hasMoreTokens()) {
//			String token = st.nextToken();
//			String[] data = token.split(",");
//			Pattern p = Pattern.compile("은|는|이|가|요|서");
//			Matcher m = p.matcher(data[1]);
//			String db = m.toString();
//			StringBuffer sb = new StringBuffer();
//			
//			while(m.find()) {
//				m.appendReplacement(sb, "");
//			}
//			m.appendTail(sb);
//			outputKey.set(sb);
//		}
//		
		
		
	}

}
