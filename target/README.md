# Introduction
[Documentation](https://jcustenborder.github.io/kafka-connect-documentation/projects/QueryBasedJdbcConnector) | [Confluent Hub](https://www.confluent.io/hub/com.caleb.kafka.connect/QueryBasedJdbcConnector)

This plugin is used to add additional JSON parsing functionality to Kafka Connect.

# Installation

## Confluent Hub

The following command can be used to install the plugin directly from the Confluent Hub using the
[Confluent Hub Client](https://docs.confluent.io/current/connect/managing/confluent-hub/client.html).

```bash
confluent-hub install com.caleb.kafka.connect/QueryBasedJdbcConnector:latest
```

## Manually

The zip file that is deployed to the [Confluent Hub](https://www.confluent.io/hub/com.caleb.kafka.connect/QueryBasedJdbcConnector) is available under
`target/components/packages/`. You can manually extract this zip file which includes all dependencies. All the dependencies
that are required to deploy the plugin are under `target/kafka-connect-target` as well. Make sure that you include all the dependencies that are required
to run the plugin.

1. Create a directory under the `plugin.path` on your Connect worker.
2. Copy all of the dependencies under the newly created subdirectory.
3. Restart the Connect worker.

# Converters
## [Super Converter](https://jcustenborder.github.io/kafka-connect-documentation/projects/QueryBasedJdbcConnector/sources/MyConverter.html)

```
com.caleb.kafka.connect.MyConverter
```
This is a description of this connector and will show up in the documentation

# Source Connectors
## [Super Source Connector](https://jcustenborder.github.io/kafka-connect-documentation/projects/QueryBasedJdbcConnector/sources/MySourceConnector.html)

```
com.caleb.kafka.connect.MySourceConnector
```

This is a description of this connector and will show up in the documentation
### Important

This is a important information that will show up in the documentation.
### Note

This is a note that will show up in the documentation
### Tip

This is a tip that will show up in the documentation.
### Configuration

#### General


##### `my.setting`

This is a setting important to my connector.

*Importance:* HIGH

*Type:* STRING




# Sink Connectors
## [Super Sink Connector](https://jcustenborder.github.io/kafka-connect-documentation/projects/QueryBasedJdbcConnector/sinks/MySinkConnector.html)

```
com.caleb.kafka.connect.MySinkConnector
```

This is a description of this connector and will show up in the documentation
### Important

This is a important information that will show up in the documentation.
### Note

This is a note that will show up in the documentation
### Tip

This is a tip that will show up in the documentation.
### Configuration

#### General


##### `jdbc.connection`

The jdbc connection URL to the database.

*Importance:* HIGH

*Type:* STRING



##### `jdbc.insertMode`

This says whether to use query or update to execute the query to execute the query (query can be needed when calling functions sometimes)

*Importance:* HIGH

*Type:* STRING



##### `jdbc.password`

The jdbc connection PASSWORD to the database.

*Importance:* HIGH

*Type:* STRING



##### `jdbc.query`

The actual query that will run, Place variables in according to sql2o documentation

*Importance:* HIGH

*Type:* STRING



##### `jdbc.username`

The jdbc connection USERNAME to the database.

*Importance:* HIGH

*Type:* STRING




# Transformations
## [Super Cool Transformation](https://jcustenborder.github.io/kafka-connect-documentation/projects/QueryBasedJdbcConnector/transformations/MyKeyValueTransformation.html)

*Key*
```
com.caleb.kafka.connect.MyKeyValueTransformation$Key
```
*Value*
```
com.caleb.kafka.connect.MyKeyValueTransformation$Value
```

This transformation will change one record to another record.
### Configuration

#### General


##### `my.setting`

This is a setting important to my connector.

*Importance:* HIGH

*Type:* STRING





# Development

## Building the source

```bash
mvn clean package
```

## Contributions

Contributions are always welcomed! Before you start any development please create an issue and
start a discussion. Create a pull request against your newly created issue and we're happy to see
if we can merge your pull request. First and foremost any time you're adding code to the code base
you need to include test coverage. Make sure that you run `mvn clean package` before submitting your
pull to ensure that all of the tests, checkstyle rules, and the package can be successfully built.