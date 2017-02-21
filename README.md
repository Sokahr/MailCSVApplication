##CSV - Mailer - Batch - Application

Build with Spring-Boot.

###How to run:

`java -jar ./projectATest-0.0.1-SNAPSHOT.jar resource=./TestAufgabe.csv`

Commandline Parameter
 
_resource_ : Defines the CSV-File which contains email,firstname,lastname separated with ; and " as quotes

###Change default configuration

corePoolSize = The corePoolSize for multithreded taskExecution.

maxPoolSize = The maxPoolSize for multithreded taskExecution

sendMailChunkSize = The chunk size for one sendMail Job

