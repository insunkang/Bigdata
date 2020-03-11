package mapreduce.product;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PageViewReducer_ver2 
	extends	Reducer<MyKey,Text , Text, Text> {
		Text resultVal = new Text();
		
		Text resultKey = new Text();
		
		
		@Override
		protected void reduce(MyKey key, Iterable<Text> values,
				Reducer<MyKey, Text, Text, Text>.Context context) throws IOException, InterruptedException {
			int productSum= 0; //하나의 상품이 클릭된 총 횟수
			int userSum = 0; //클릭한 사용자 수
			String beforeUserId = "";
		
			for(Text value:values) {
				String currentUser = value.toString();
				
				
				if(!beforeUserId.equals(currentUser)) {
					userSum++; //사용자가 다른 경우					
				}
				productSum++; //하나의 상품에 접속한 모든 횟수
				beforeUserId = currentUser;
			}
			//상품코드가 바뀔때마다 출력
			resultKey.set(key.getProductId());
			StringBuffer data = new StringBuffer();
			data.append(userSum).append("\t").append(productSum);
			
			resultVal.set(data.toString());	
			context.write(resultKey,resultVal);
				
			
			
			
		}
}
