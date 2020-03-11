package hdfs.exam;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSExam02 {

	public static void main(String[] args) {
		Configuration conf = new Configuration();
		FileSystem hdfs = null;
		//3. HDFS로 부터 input하기 위해 스트림객체
		FSDataInputStream hdfsin = null;
		try {
			hdfs = FileSystem.get(conf);
			//4. HDFS의 경로를 표현할 수 있는 객체
			// 	 => HDFS로 부터 읽을 파일의 경로를 명령행매개변수로 
			//	 	받아서 적용하겠다는 의미
			Path path = new Path(args[0]);
			
			//5. HDFS에 저장된 파일을 읽어야 하므로 스트림 생성하기
			hdfsin = hdfs.open(path);
			//6. 입력된 스트림을 통해 데이터를 읽는다
			String inputString =  hdfsin.readUTF();
			System.out.println("hdfs에서 읽은 데이터="+inputString);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
