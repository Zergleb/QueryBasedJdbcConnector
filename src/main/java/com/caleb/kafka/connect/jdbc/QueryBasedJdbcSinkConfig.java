package com.caleb.kafka.connect.jdbc;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

public class QueryBasedJdbcSinkConfig extends AbstractConfig {
  private static final String JDBC_URL = "jdbc.connection";
  private static final String JDBC_USERNAME = "jdbc.username";
  private static final String JDBC_PASSWORD = "jdbc.password";
  private static final String QUERY = "jdbc.query";
  private static final String QUERY_TYPE = "jdbc.queryType";

  public final String jdbcConnectionString;
  public final String username;
  public final String password;
  
  public final String firstQuery;
  public final String firstQueryType;

  public final String secondQuery;
  public final String secondQueryType;

  public final String thirdQuery;
  public final String thirdQueryType;

  public final String fourthQuery;
  public final String fourthQueryType;

  public QueryBasedJdbcSinkConfig(Map<?, ?> originals) {
    super(config(), originals);
    this.jdbcConnectionString = this.getString(JDBC_URL);
    this.username = this.getString(JDBC_USERNAME);
    this.password = this.getString(JDBC_PASSWORD);
    this.firstQuery = this.getString(QUERY + ".1");
    this.firstQueryType = this.getString(QUERY_TYPE + ".1");
    this.secondQuery = this.getString(QUERY + ".2");
    this.secondQueryType = this.getString(QUERY_TYPE + ".2");
    this.thirdQuery = this.getString(QUERY + ".3");
    this.thirdQueryType = this.getString(QUERY_TYPE + ".3");
    this.fourthQuery = this.getString(QUERY + ".4");
    this.fourthQueryType = this.getString(QUERY_TYPE + ".4");
  }

  public void addConfigsToMap(Map<String, String> configs) {
    addSingleConfigToMap(configs, JDBC_URL);
    addSingleConfigToMap(configs, JDBC_USERNAME);
    addSingleConfigToMap(configs, JDBC_PASSWORD);
    addSingleConfigToMap(configs, QUERY + ".1");
    addSingleConfigToMap(configs, QUERY_TYPE + ".1");
    addSingleConfigToMap(configs, QUERY + ".2");
    addSingleConfigToMap(configs, QUERY_TYPE + ".2");
    addSingleConfigToMap(configs, QUERY + ".3");
    addSingleConfigToMap(configs, QUERY_TYPE + ".3");
    addSingleConfigToMap(configs, QUERY + ".4");
    addSingleConfigToMap(configs, QUERY_TYPE + ".4");
  }

  public void addSingleConfigToMap(Map<String, String> configs, String config) {
    configs.put(config, this.getString(config));
  }

  public static ConfigDef config() {
    return new ConfigDef()
                .define(JDBC_URL, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, "The jdbc connection URL to the database.")
                .define(JDBC_USERNAME, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, "The jdbc connection USERNAME to the database.")
                .define(JDBC_PASSWORD, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, "The jdbc connection PASSWORD to the database.")
                .define(QUERY + ".1", ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, "The actual query that will run, Place variables in according to sql2o documentation")
                .define(QUERY_TYPE + ".1", ConfigDef.Type.STRING, "update", ConfigDef.Importance.LOW, "This says whether to use query or update to execute the query to execute the query (query can be needed when calling functions sometimes)")
                .define(QUERY + ".2", ConfigDef.Type.STRING, "", ConfigDef.Importance.LOW, "The actual query that will run, Place variables in according to sql2o documentation")
                .define(QUERY_TYPE + ".2", ConfigDef.Type.STRING, "update", ConfigDef.Importance.LOW, "This says whether to use query or update to execute the query to execute the query (query can be needed when calling functions sometimes)")
                .define(QUERY + ".3", ConfigDef.Type.STRING, "", ConfigDef.Importance.LOW, "The actual query that will run, Place variables in according to sql2o documentation")
                .define(QUERY_TYPE + ".3", ConfigDef.Type.STRING, "update", ConfigDef.Importance.LOW, "This says whether to use query or update to execute the query to execute the query (query can be needed when calling functions sometimes)")
                .define(QUERY + ".4", ConfigDef.Type.STRING, "", ConfigDef.Importance.LOW, "The actual query that will run, Place variables in according to sql2o documentation")
                .define(QUERY_TYPE + ".4", ConfigDef.Type.STRING, "update", ConfigDef.Importance.LOW, "This says whether to use query or update to execute the query to execute the query (query can be needed when calling functions sometimes)");
  }
}
