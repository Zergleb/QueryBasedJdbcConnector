package com.caleb.kafka.connect.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jcustenborder.kafka.connect.utils.VersionUtil;
import com.github.jcustenborder.kafka.connect.utils.config.Description;
import com.github.jcustenborder.kafka.connect.utils.config.DocumentationNote;
import com.github.jcustenborder.kafka.connect.utils.config.DocumentationTip;
import com.github.jcustenborder.kafka.connect.utils.config.Title;

@Description("Sink connector that runs a custom query for every message and dynamically allows data to be loaded")
@DocumentationTip("Use query type 'update' for writes and 'query' for single row queries that you wish to add data to the context")
@Title("Query Based Jdbc Sink") //This is the display name that will show up in the documentation.
public class QueryBasedJdbcSinkConnector extends SinkConnector {
  /*
  Your connector should never use System.out for logging. All of your classes should use slf4j
  for logging
   */
  private static Logger log = LoggerFactory.getLogger(QueryBasedJdbcSinkConnector.class);
  private QueryBasedJdbcSinkConfig connectorConfig;

  @Override
  public List<Map<String, String>> taskConfigs(int maxTasks) {

    /**
     * This is used to schedule the number of tasks that will be running. This should not exceed maxTasks.
     * Here is a spot where you can dish out work. For example if you are reading from multiple tables
     * in a database, you can assign a table per task.
     */

    log.info("Setting task configurations for {} workers.", maxTasks);
    final List<Map<String, String>> configs = new ArrayList<>(maxTasks);
    for (int i = 0; i < maxTasks; ++i) {
      HashMap<String, String> taskConfig = new HashMap<>();
      connectorConfig.addConfigsToMap(taskConfig);
      configs.add(taskConfig);
    }
    return configs;
  }

  @Override
  public void start(Map<String, String> settings) {
    connectorConfig = new QueryBasedJdbcSinkConfig(settings);
  }

  @Override
  public void stop() {
  }

  @Override
  public ConfigDef config() {
    return QueryBasedJdbcSinkConfig.config();
  }

  @Override
  public Class<? extends Task> taskClass() {
    return QueryBasedJdbcSinkTask.class;
  }

  @Override
  public String version() {
    return VersionUtil.version(this.getClass());
  }
}
