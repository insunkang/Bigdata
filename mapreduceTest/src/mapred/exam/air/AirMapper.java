package mapred.exam.air;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirMapper extends
			Mapper<LongWritable, Text, Text, IntWritable>{
	//output데이터를 mapper의 실행결과로 내보낼 수 있도록 키와 value를 저장하는 변수 선언
		//output데이터의 value는 무조건 1이므로 상수로 정의
		static final IntWritable one =new IntWritable(1);
		//output데이터의 key는 문자열이므로 Text타입으로 정의
		Text outputKey = new Text();
		@Override
		protected void map(LongWritable key, Text value, 
				Mapper<LongWritable, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
		if(key.get()>0 ) {	
				String[] line = value.toString().split(",");
					
			if(!line[15].equals("NA")) {	
				if(line!=null & line.length>0) {
					outputKey.set(line[1]);
					int result = Integer.parseInt(line[15]);
							
					if(result>0) {
						context.write(outputKey, one);
					}
				}
			}
		}
			
		}

}
