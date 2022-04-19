# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.github.nwzd.rsvp-spark' is invalid and this project uses 'com.github.nwzd.rsvpspark' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.6/maven-plugin/reference/html/#build-image)
* [WebSocket](https://docs.spring.io/spring-boot/docs/2.6.6/reference/htmlsingle/#boot-features-websockets)

### Guides
The following guides illustrate how to use some features concretely:

* [Using WebSocket to build an interactive web application](https://spring.io/guides/gs/messaging-stomp-websocket/)

#### Kafka

`kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic test-topic`

`kafka-console-consumer --bootstrap-server localhost:9092 --from-beginning --topic test-topic`