package com.caleb.kafka.connect.jdbc;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.ResultSetHandler;
import org.sql2o.Sql2o;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.github.jcustenborder.kafka.connect.utils.VersionUtil;
import com.google.common.base.Strings;

public class QueryBasedJdbcSinkTask extends SinkTask {
  private static Logger log = LoggerFactory.getLogger(QueryBasedJdbcSinkTask.class);

  QueryBasedJdbcSinkConfig config;

  private Sql2o sql2o;

  @Override
  public void start(Map<String, String> settings) {
    this.config = new QueryBasedJdbcSinkConfig(settings);
    sql2o = new Sql2o(config.jdbcConnectionString, config.username, config.password);
  }

  @Override
  public void put(Collection<SinkRecord> records) {
      for(SinkRecord record : records) {
      Map<String, Object> visitor = new HashMap<>();
      Struct struct = (Struct) record.value();
      for(Field field : record.valueSchema().fields()) {
        Object value = struct.get(field);
        visitor.put(field.name(), value);
      }
      
      try (Connection con = sql2o.beginTransaction()) {
        executeQuery(con, config.firstQuery, config.firstQueryType, visitor);
        executeQuery(con, config.secondQuery, config.secondQueryType, visitor);
        executeQuery(con, config.thirdQuery, config.thirdQueryType, visitor);
        executeQuery(con, config.fourthQuery, config.fourthQueryType, visitor);
        con.commit();
      }
    }
  }

  public void executeQuery(Connection con, String query, String queryType, Map<String, Object> visitor) {
    if(Strings.isNullOrEmpty(query)) {
      return;
    }
    Query execution = con.createQuery(query);
    for(Entry<String, Object> entry : visitor.entrySet()) {
      Object value = entry.getValue();
      String key = entry.getKey();
      if(execution.getParamNameToIdxMap().containsKey(key)) {
        if(value instanceof String || value instanceof Number) {
          execution = execution.addParameter(key, value);
        }
      }
    }
    
    log.info("Executing query '" + query + "'");
    if(queryType.contains("query")) {
      execution.executeAndFetch((ResultSetHandler<Map<String, Object>>) (handler) -> {
        int count = 0;
        do {
          log.info("Parsing row");
          if(count > 0) {
            throw new RuntimeException("Created a query that should create values with a single row but the query returned multiple rows '" + query + "'");
          }
          int columnCount = handler.getMetaData().getColumnCount();
          log.info("Column Count: " + columnCount);
          for(int i = 1; i <= columnCount; i++) {
            String columnName = handler.getMetaData().getColumnName(i);
            String columnLabel = handler.getMetaData().getColumnLabel(i);
            Object columnValue = handler.getObject(i);

            log.info("name: " + columnName + " label: " + columnLabel + " value: " + columnValue);
            visitor.put(columnLabel, columnValue);
          }
        } while (handler.next());
        return null;
      });
    } else {
      execution.executeUpdate();
    }
  }
    
  @Override
  public void flush(Map<TopicPartition, OffsetAndMetadata> map) {
    
  }
    
  @Override
  public void stop() {
  }

  @Override
  public String version() {
    return VersionUtil.version(this.getClass());
  }
}
