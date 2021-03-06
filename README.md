# Introduction

Welcome to your new Kafka Connect plugin!

# Running in development


The [docker-compose.yml](docker-compose.yml) that is included in this repository is based on the Confluent Platform Docker
images. Take a look at the [quickstart](http://docs.confluent.io/current/cp-docker-images/docs/quickstart.html#getting-started-with-docker-client)
for the Docker images. 

Your development workstation needs to be able to resolve the hostnames that are listed in the `docker-compose.yml` 
file in the root of this repository. If you are using [Docker for Mac](https://docs.docker.com/v17.12/docker-for-mac/install/)
your containers will be available at the ip address `127.0.0.1`. If you are running docker-machine
you will need to determine the ip address of the virtual machine with `docker-machine ip confluent`
to determine the ip address.

```
127.0.0.1 zookeeper
127.0.0.1 kafka
127.0.0.1 schema-registry
```


```
docker-compose up -d
```


The debug script assumes that `connect-standalone` is in the path on your local workstation. Download 
the latest version of the [Kafka](https://www.confluent.io/download/) to get started.


Start the connector with debugging enabled.
 
```
./bin/debug.sh
```

Start the connector with debugging enabled. This will wait for a debugger to attach.

```
export SUSPEND='y'
./bin/debug.sh
```

mvn -Dmaven.javadoc.skip=true -DskipTests=true install

docker run -v /Users/caleb.holt/hack/jdbcconnector/QueryBasedJdbcConnector/target/kafka-connect-target/usr/share/kafka-connect/QueryBasedJdbcConnector:/connectors/QueryBasedJdbcConnector -e ADV_HOST=127.0.0.1 -e SAMPLEDATA=0 -e EULA="https://dl.lenses.io/d/?id=38a5ed11-aa2b-433e-b8a2-f0aa748fdea9" --rm -p  3030:3030 -p 9092:9092 -p 2181:2181 lensesio/box

```
connector.class=com.caleb.kafka.connect.jdbc.QueryBasedJdbcSinkConnector
jdbc.password=**
jdbc.username=**
//172.17.0.1 is how you reference your host machine in docker
jdbc.connection=jdbc:mysql://172.17.0.1:3306/mydatabase
topics=test_topic_local
tasks.max=1
//patient_id and filler_number are values in the avro definition
jdbc.query.1=insert into test.new_table (name, filler) values (:patient_id, :filler_number)
//Queries add attributes to the context (only single rows allowed. Will overwrite any existing avro or previous query values so rename your columns on conflict)
jdbc.query.2=select 'AnyValue' newAttribute)
jdbc.queryType.2=query
//This time it inserts with 'AnyValue" for 'filler' because query 2 added 'AnyValue' to the context (This type of thing can ussually be handled by joins and inserts instead)
jdbc.query.3=replace into test.new_table (name, filler) values (:patient_id, :newAttribute)
//replace is a construct for upsert based on primary key in MYSQL look up your own database specifics
```