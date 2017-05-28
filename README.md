## Email Sender

Sending mails with big csv file as input given is a big csv file (size: multiple gigabytes, encoding: UTF-8) with email, firstname and lastname separated with ; and " as quotes. The application should read the csv file and send emails to all reciptions in an effective way. For testing please do not send any emails.
Just implement a mock mail sending service. It should wait for half a second to mock the sending time needed and then log a success message mentioning the recipient.

## How to run

First you need Java 8  and  can run the application using

mvn clean package

runKafka.sh in order to run kafka and zookeeper

Put the file will be parsed into project and state name of it in application.properties file.

mvn spring-boot:run Or you can build the JAR file with


## API EndPoints

1.You can trigger sending email by http://localhost:8082/email  endpoint. Just post the endpoint
and watch the logs.

## How

The huge csv file reads line by line because in case of reading all line in huge file makes you
trouble,some says out of memory :) .After reading file the application sends object to to kafka
topic which is divided 3 partition and  here is kafka-multi-threaded-consumption. Each listener
waits 0.5 second and send to mock email service.Docker compose up kafka and zookeeper images and
linked each other run dependently. In case of failure of the application kafka listener continues
consuming the waited messages in the topic.


ENJOY!
