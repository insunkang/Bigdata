package mapred.exam01;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapperExam 
		extends Mapper<LongWritable, Text,  Text, IntWritable> {
	//output데이터를 mapper의 실행결과로 내보낼 수 있도록 키와 value를 저장하는 변수 선언
		//output데이터의 value는 무조건 1이므로 상수로 정의
		
		static final IntWritable outputVal =new IntWritable(1);
		//output데이터의 key는 문자열이므로 Text타입으로 정의
		
		Text outputKey = new Text();
		
		@Override
		protected void map(LongWritable key, Text value, 
				Mapper<LongWritable, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			//key는 linenumber ex) 1
			//value는 입력데이터의 한 라인에 해당하는 문장 ex) read a book
			StringTokenizer st = new StringTokenizer(value.toString());
			 System.out.println(key+":"+value);
			
			while(st.hasMoreTokens()) {
				String token = st.nextToken();
				if(token.length()>=5) {
				
				outputKey.set(token);//output데이터의 키를 생성
				//context객체의 write메소드를 통해서 output으로 내보낼 데이터의 key와 value를 정의
				context.write(outputKey, outputVal);
				
				}
			}
		}
		
		

}
