package mapreduce.product;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PageViewMapper_ver2 extends
			Mapper<LongWritable, Text, MyKey, Text>{
	//output데이터를 mapper의 실행결과로 내보낼 수 있도록 키와 value를 저장하는 변수 선언
		//output데이터의 value는 무조건 1이므로 상수로 정의
		Text outputValue =new Text();
		//output데이터의 key는 문자열이므로 Text타입으로 정의
		MyKey outputKey = new MyKey();
		@Override
		protected void map(LongWritable key, Text value, 
				Mapper<LongWritable, Text, MyKey, Text>.Context context)
				throws IOException, InterruptedException {
			
			String line = value.toString();
			String[] data = line.split("\\t");
			
					outputKey.setProductId(data[2]);
					outputKey.setUserId(data[9]);
					outputValue.set(data[9]);
					context.write(outputKey, outputValue);
			
			
		}

}
