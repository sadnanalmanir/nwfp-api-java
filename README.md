# nwfp-api-java
This repository enables access to [North Wyke Farm Platform (NWFP) Data](https://nwfp.rothamsted.ac.uk/) RESTful [endpoints](https://red-crescent-623716.postman.co/documentation/7453000-ae05790f-8bea-4c3c-8b9c-006ebab9e13e) from Java

## NWFP has currently 12 RESTful endpoints supporting GET, POST operations

### Endpoints supporting GET operations
    - https://nwfp.rothamsted.ac.uk:8443/getCatchments
    - https://nwfp.rothamsted.ac.uk:8443/getMeasurementTypes
    - https://nwfp.rothamsted.ac.uk:8443/getFields
    - https://nwfp.rothamsted.ac.uk:8443/getFieldEvents
    - https://nwfp.rothamsted.ac.uk:8443/getAnimalBasicData
    - https://nwfp.rothamsted.ac.uk:8443/getDataQuality
    - https://nwfp.rothamsted.ac.uk:8443/getMeasurementLocations
    - https://nwfp.rothamsted.ac.uk:8443/getMeasurementTypes
    - https://nwfp.rothamsted.ac.uk:8443/getMeasurementTypesLong
### Endpoints supporting POST operations
    - https://nwfp.rothamsted.ac.uk:8443/getMeasurementSByCatchmentName
    - https://nwfp.rothamsted.ac.uk:8443/getMeasurementSByDateRange
    - https://nwfp.rothamsted.ac.uk:8443/getMeasurementSByTypeId 


## How to run

#### Clone the repo
```shell
$ git clone https://github.com/sadnanalmanir/nwfp-api-java.git
```

#### Move into the project directory
```shell
$ cd nwfp-api-java
```

#### Compile the project
```shell
$ mvn compile
```

#### Invoke the endpoints by executing the specific application
```shell
$ mvn exec:java -Dexec.mainClass=uk.ac.rothamsted.ide.GetCatchment
$ mvn exec:java -Dexec.mainClass=uk.ac.rothamsted.ide.GetCatchmentMeasurementType
$ mvn exec:java -Dexec.mainClass=uk.ac.rothamsted.ide.GetField
$ mvn exec:java -Dexec.mainClass=uk.ac.rothamsted.ide.GetFieldEvent
$ mvn exec:java -Dexec.mainClass=uk.ac.rothamsted.ide.GetAnimalBasicData
$ mvn exec:java -Dexec.mainClass=uk.ac.rothamsted.ide.GetDataQuality
$ mvn exec:java -Dexec.mainClass=uk.ac.rothamsted.ide.GetMeasurementLocation
$ mvn exec:java -Dexec.mainClass=uk.ac.rothamsted.ide.GetMeasurementType
$ mvn exec:java -Dexec.mainClass=uk.ac.rothamsted.ide.GetMeasurementTypeLong
$ mvn exec:java -Dexec.mainClass=uk.ac.rothamsted.ide.GetMeasurementByCatchmentName
$ mvn exec:java -Dexec.mainClass=uk.ac.rothamsted.ide.GetMeasurementByDateRange
$ mvn exec:java -Dexec.mainClass=uk.ac.rothamsted.ide.GetMeasurementByTypeId
```


