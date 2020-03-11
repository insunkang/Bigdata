package hdfs.exam;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.HarFileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSTestCopy {

	public static void main(String[] args) {
		Configuration conf = new Configuration();
		FileSystem hdfs = null;		
		FSDataInputStream hdfsin = null;
		FSDataOutputStream hdfsout = null;
		try {
			hdfs = FileSystem.get(conf);
			Path pathin = new Path(args[0]);
			Path pathout = new Path(args[1]);
			hdfsin = hdfs.open(pathin);
			hdfsout = hdfs.create(pathout);
			
			while(true) {
				int data = hdfsin.read();
				System.out.println((char)data);
				if(data==-1) {
					break;
				}
				hdfsout.write((char)data);
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
