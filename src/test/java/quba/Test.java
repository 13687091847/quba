package quba;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Test {
	public static void main(String[] args) throws Exception {
//		List<String> warnings = new ArrayList<String>();
//		boolean overwrite = true;
//		File configFile = new File("mbg.xml");
//		ConfigurationParser cp = new ConfigurationParser(warnings);
//		Configuration config = cp.parseConfiguration(configFile);
//		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
//				callback, warnings);
//		myBatisGenerator.generate(null);
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date1 = format.parse("2019-03-19 17:16:59");
//		Date date2 = new Date();
//		if (date1.before(date2)) {
//            System.out.println(date1 + "在当前时间前面");
//        } else if (date1.after(date2)) {
//            System.out.println(date1 + "在当前时间后面");
//        } else {
//            System.out.println("是同一天的同一时间");
//        }
//		long c = (date1.getTime() - date2.getTime())/60/1000;
//		System.err.println(c);
		Date d = new Date();
		long currtime = d.getTime(); //获取当前时间
		System.err.println(currtime);
	}
}
