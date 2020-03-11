package mapreduce.air.combine;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AirCombinerReducer extends
			Reducer<Text,IntWritable , Text, IntWritable> {
	//reduce작업의 결과를 저장할 변수
		IntWritable resultVal = new IntWritable();
		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,
				Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
			int sum= 0;
			for(IntWritable value:values) {//reduce에 전달된 입력데이터의 값을 꺼내서 모두 더하기
				sum= sum + value.get();	   
			}
			resultVal.set(sum); //계산된 결과를 IntWritable에 셋팅
			//reduce의 실행결과를 context에 write
			context.write(key,resultVal);
			context.getCounter("mycounter", "combiber_wordcount").increment(1);
		}
}
