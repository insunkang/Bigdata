package mapreduce.product;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
/*
 * 	사용자가 -D 옵션을 이용해서 입력한 옵션값을 프로그램 안에서 사용하기 위해서 
 * 	즉, Mapper가 사용할 수 있도록 전달
 * 	1. Configured와 Tool을 상속해야 한다.
 * 		=> Configured는 환경설정 정보를 활용
 * 		=> Tool은 사용자 정의 옵션을 사용하기 위해
 * 	2. run메소드를 오버라이딩
 * 		=> run메소드 안에 Driver에서 구현했던 모든 코드를 구현
 * 	3. run메소드를 main메소드에서 실행되도록 호출해야 한다.
 * 		=> run메소드를 직접 호출 할 수 없고 ToolRunner라는 헬퍼클래스를 이용해서 호출
 */
public class PageViewDriver extends Configured implements Tool {
	@Override
	public int run(String[] optionlist) throws Exception {
		GenericOptionsParser parser = 
				new GenericOptionsParser(getConf(), optionlist);
		String[] otherArgs = parser.getRemainingArgs();
		/*Configuration conf = new Configuration();*/
		Job job = new Job(getConf(), "exam_sort");			
		
		job.setMapperClass(PageViewMapper_ver2.class);
		job.setReducerClass(PageViewReducer_ver2.class);
		job.setJarByClass(PageViewDriver.class);
		
		//Shuffle할때 사용할 클래스를 사용자정의 클래스가 실행되도록 등록
		job.setPartitionerClass(MyKeyPartitioner_ver2.class);
		job.setGroupingComparatorClass(GroupKeyComparator.class);
		job.setSortComparatorClass(MyKeyComparator.class);
		job.setMapOutputKeyClass(MyKey.class);
		job.setMapOutputValueClass(Text.class);
		
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		
		job.waitForCompletion(true);
		return 0;
	}
	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new PageViewDriver(), args);
		
	}

	
}
