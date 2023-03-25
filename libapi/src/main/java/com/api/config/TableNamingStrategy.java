//package com.api.config;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import org.hibernate.boot.model.naming.Identifier;
//import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
//import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
//import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TableNamingStrategy extends SpringPhysicalNamingStrategy {
//  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd_MM_YYYY");
//  public static final PhysicalNamingStrategyStandardImpl INSTANCE =
//      new PhysicalNamingStrategyStandardImpl();
//
//  @Override
//  public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
////    if (!name.getText().equals("game2data"))
////      return name;
//    StringBuilder customName =
//        new StringBuilder("orders").append('_').append(DATE_FORMATTER.format(LocalDate.now()));
//    return new Identifier(customName.toString(), name.isQuoted());
//  }
//}
