# eap-kafka-springboot

The application shows how to connect a Spring Boot application to Apache Kafka.
This basic application sends events to specific topics when dedicated REST endpoints are invoked.
This reproducer is by no means extensive of both Spring Boot & Apache Kafka features.
Please refer to Spring Boot documentation for all [*spring-kafka* properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#:~:text=spring.kafka.admin.auto%2Dcreate).


The following code has been built using:
- Spring Boot 3.0.0
- Apache Maven 3.9.1
- Java openjdk version "17.0.5"


In a new terminal window, launch Kafka typing:

``` 
docker-compose up
```

and wait for the Kafka instance to be up.


Launch Maven to build and deploy the application:

```
mvn spring-boot:run
```

Once started, the log should show events like the following:
```

```

Integers are sent every 5 seconds (as configured for _integer.scheduled.rate_ property in _application.properties_), whereas dates are sent every 13 seconds (related to property _date.scheduled.rate_).

Application uses two topics to publish and consume events:

- _kafkaspringboot.integerTopic_ which contains events as integers
- _kafkaspringboot.dateTopic_  which contains events as dates


During its execution, the application saves both its latest odd and even integers.

To retrieve the last even number, please invoke:
```
http :8080/kafka/integer/getLastEvenInteger
```


And for the last odd number:
```
http :8080/kafka/integer/getLastOddInteger
```


### Insights

Producer class _MyProducer_ has been built using _MyProducerFactoryConfig_, which reads properties from _application.properties_.
