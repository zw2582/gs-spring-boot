package java.base.lean.exception;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ExceptionCatch {

	/**
	 * 
	 * 遍历压缩包，并将压缩包名称输出到指定文件
	 * 
	 * 使用try-with-resources创建多个可关闭资源，并执行业务
	 * 可关闭资源指任何实现了java.lang.AutoCloseable的对象，包括实现java.io.Closeable的所有对象
	 */
	public static void writeToFileZipFileContents(String zipFileName, String outputFileName) throws java.io.IOException {
		Path outputFilePath = Paths.get(outputFileName);
		
		/*
		 * try-with-resources声明两个可关闭资源Zipfile和BufferedWriter
		 */
		try(ZipFile zf = new ZipFile(zipFileName);
				BufferedWriter wirte = Files.newBufferedWriter(outputFilePath, StandardCharsets.US_ASCII)) {
			for(Enumeration entries = zf.entries(); entries.hasMoreElements();) {
				String newline = System.getProperty("line.seperator");
				String zipEntryName = ((ZipEntry)entries.nextElement()).getName()+newline;
				wirte.write(zipEntryName, 0 , zipEntryName.length());
			}
		};
	}
	
	/**
	 * 自动关闭java.sql.Statement对象
	 * @throws SQLException 
	 */
	public static void viewTable(Connection con) {
		String query = "select * from integle_apply";
		
		/*
		 * try-with-resources声明Statement可关闭资源，并在执行结束之后关闭资源，不论try执行成功还是失败
		 */
		try(Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String name = rs.getString("name");
			}
		} catch (SQLException e) {
			//捕获异常
			e.printStackTrace();
		}
	}
}
