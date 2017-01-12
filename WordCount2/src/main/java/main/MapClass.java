package main;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Map Class which extends MaReduce.Mapper class
 * Map is passed a single line at a time, it splits the line based on space
 * and generated the token which are output by map with value as one to be consumed
 * by reduce class
 * @author Raman
 */
public class MapClass extends Mapper<LongWritable, Text, IntWritable, IntWritable>{
	private static final Pattern REGEX_TEMP = Pattern.compile("^([0-9]+);[^;]*;[^;]*;[^;]*;[^;]*;.[^;]*;.[^;]*;([0-9]+)");
	private final static IntWritable one = new IntWritable(1);

	private IntWritable station = new IntWritable();
	private IntWritable temperature = new IntWritable();
    
    /**
     * map function of Mapper parent class takes a line of text at a time
     * splits to tokens and passes to the context as word along with value as one
     */
//	@Override
//	protected void map(LongWritable key, Text value,
//			Context context)
//			throws IOException, InterruptedException {
//
//		String line = value.toString();
//		StringTokenizer st = new StringTokenizer(line," ");
//
//		while(st.hasMoreTokens()){
//			word.set(st.nextToken());
//			context.write(word,one);
//		}
//	}

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		Matcher m = REGEX_TEMP.matcher(line);

		if (m.find()) {
			station.set(Integer.parseInt(m.group(1)));
			temperature.set(Integer.parseInt(m.group(2))-273);
			context.write(station,temperature);
		}
	}
}
