##CSV - Mailer - Batch - Application

Build with Spring-Boot.

###Build
With Gradle 
just run gradle build

or execute the gradle-wrapper

`gradlew build`

###How to run:
`java -jar ./build/libs/csvMailer-0.0.1-SNAPSHOT.jar resource=./example.csv`

####Commandline Parameter
 
_resource_ : Defines the CSV-File which contains email,firstname,lastname separated with ; and " as quotes

###Change default configuration
Just put a __application.properties__ File with the following keys in it.

_corePoolSize_ = The corePoolSize for multithreded taskExecution.

_maxPoolSize_ = The maxPoolSize for multithreded taskExecution

_sendMailChunkSize_ = The chunk size for one sendMail Job

