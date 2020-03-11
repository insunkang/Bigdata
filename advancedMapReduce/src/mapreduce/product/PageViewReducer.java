package mapreduce.product;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PageViewReducer 
	extends	Reducer<MyKey,IntWritable , Text, Text> {
		Text resultVal = new Text();
		
		Text resultKey = new Text();
		
		
		@Override
		protected void reduce(MyKey key, Iterable<IntWritable> values,
				Reducer<MyKey, IntWritable, Text, Text>.Context context) throws IOException, InterruptedException {
			int productSum= 0; //하나의 상품이 클릭된 총 횟수
			int userSum = 0; //클릭한 사용자 수
			String beforeUserId = key.getUserId();
		
			for(IntWritable value:values) {
				System.out.println(key.toString());
				if(beforeUserId.equals(key.getUserId())) {
										
					
					
				}
				productSum = productSum+value.get();
				beforeUserId = key.getUserId();
			}
		
			resultKey.set(key.getProductId());		
			resultVal.set(productSum+"");	
			context.write(resultKey,resultVal);
				
			
			
			
		}
}
