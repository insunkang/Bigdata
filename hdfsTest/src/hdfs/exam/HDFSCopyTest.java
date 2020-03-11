package hdfs.exam;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSCopyTest {

	public static void main(String[] args) {
		
		Configuration conf = new Configuration();
		FileSystem hdfs = null;
		FSDataInputStream hdfsin = null;
		FSDataOutputStream hdfsout = null;
		
		
		try {
			hdfs = FileSystem.get(conf);
		
			Path path = new Path(args[0]);
		
			hdfsin = hdfs.open(path);
			
			String inputString =  hdfsin.readUTF();						
			
			Path path1 = new Path(args[1]);
			
			hdfsout = hdfs.create(path1);
			
			hdfsout.writeUTF(inputString);	
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
