package mapred.exam.stock.option;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StockOptionMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	String stockType;
	static final IntWritable one = new IntWritable(1);

	Text outputKey = new Text();

	@Override
	protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		stockType = context.getConfiguration().get("stockType");
	}

	@Override
		protected void map(LongWritable key, Text value, 
				Mapper<LongWritable, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
		if(key.get()>0) {	
				String[] line = value.toString().split(",");
					
				if(line!=null & line.length>0) {
					
					if(stockType.equals("up")) {
						outputKey.set(line[2].substring(0, 4));
						double result = Double.parseDouble(line[6])-
								Double.parseDouble(line[3]);
						if(result>0) {
							context.write(outputKey, one);
						}
					}else if(stockType.equals("down")) {
						outputKey.set(line[2].substring(0, 4));
						double result = Double.parseDouble(line[6])-
								Double.parseDouble(line[3]);
						if(result<0) {
							context.write(outputKey, one);
						}
					}else if(stockType.equals("equal")) {
						outputKey.set(line[2].substring(0, 4));
						double result = Double.parseDouble(line[6])-
								Double.parseDouble(line[3]);
						if(result==0) {
							context.write(outputKey, one);
						}
					}	
				}
		}
			
		}

}
