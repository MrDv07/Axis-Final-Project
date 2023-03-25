//package com.api.config;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Map;
//import org.hibernate.resource.jdbc.spi.StatementInspector;
//import org.springframework.stereotype.Component;
//
//import javassist.NotFoundException;
//
//@Component
//public class StatementModifier implements StatementInspector{
//	
//	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd_MM_YYYY");
//	
//	public static final String UPDATE_PATTERN = "%s_%s";
//	
//	public static final Map<String, String> UPDATED_TABLE_NAMES = Map.of("orders","orders");
//
//	@Override
//	public String inspect(String sql) {
//		// TODO Auto-generated method stub
//		String today = DATE_FORMATTER.format(LocalDate.now());
//		for(var updatedTableName : UPDATED_TABLE_NAMES.entrySet()) {
//			if (sql.contains("CREATE TABLE " + updatedTableName.getKey())) {
//		        return getCreationQuery(updatedTableName.getKey()).replace(updatedTableName.getKey(),
//		            "IF NOT EXISTS " + String.format(UPDATE_PATTERN, updatedTableName.getValue(), today));
//		      }
//			
//			sql = sql.replaceAll(updatedTableName.getKey(), String.format(UPDATE_PATTERN, updatedTableName.getValue(),today));
//		}
//		return sql;
//	}
//	
//	private String getCreationQuery(String defaultTableName) {
//		InputStream createQueryStream = this.getClass().getClassLoader().getResourceAsStream("create.sql");
//		try {
//		      return findEntityCreationQueryInInputStream(createQueryStream, defaultTableName);
//		    } catch (IOException | NotFoundException e) {
//		      throw new RuntimeException("Couldn't find creation statement for table " + defaultTableName);
//		    }
//
//		  }
//
//		  private String findEntityCreationQueryInInputStream(InputStream inputStream,
//		      String defaultTableName) throws IOException, NotFoundException {
//
//		    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
//		      String line;
//		      while ((line = br.readLine()) != null) {
//		        if (line.contains("create table " + defaultTableName)) {
//		          return line;
//		        }
//		      }
//		    }
//		    throw new NotFoundException("No creation statement found for table " + defaultTableName);
//		  }
//		}