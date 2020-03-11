package mapreduc.basic;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//Reducer - 데이터를 집계하는 역할
/*
 * 1. Reducer 클래스를 상속
 * 2. reduce메소드를 오버라이딩
*/
public class WordCountReducer 
	extends Reducer<Text,IntWritable , Text, IntWritable> {
	//reduce작업의 결과를 저장할 변수
	IntWritable resultVal = new IntWritable();
	Text appenddata = new Text();
	String data = "";
	Text resultKey = new Text();
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		int sum= 0;
		data = data+"reduce호출";
		appenddata.set(data);
		for(IntWritable value:values) {//reduce에 전달된 입력데이터의 값을 꺼내서 모두 더하기
			sum= sum + value.get();	   
		}
		resultVal.set(sum); //계산된 결과를 IntWritable에 셋팅
		//reduce의 실행결과를 context에 write
		resultKey.set(key+":"+appenddata);
		context.write(resultKey,resultVal);
	}
		

}
