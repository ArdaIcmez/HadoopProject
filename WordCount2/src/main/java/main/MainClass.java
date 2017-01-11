package main;

import org.apache.hadoop.util.ToolRunner;

/**
 * Created by mathi on 11/01/2017.
 */
public class MainClass {
    public static void main(String[] args) throws Exception{
        int exitCode = ToolRunner.run(new WordCount(), args);
        System.exit(exitCode);
    }
}
