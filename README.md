# nwfp-api-java
Accessing North Wyke Farm Platform (NWFP) RESTful endpoints from Java

## NWFP has currently 12 RESTful endpoints supporting GET, POST operations

### Endpoints supporting GET operations
    - getCatchment
    - getCatchmentMeasurementType
    - getField
    - getFieldEvent
    - getAnimalBasicData
    - getDataQuality
    - getMeasurementLocation
    - getMeasurementType
    - getMeasurementTypeLong
### Endpoints supporting POST operations
    - getMeasurementByCatchmentName
    - getMeasurementByDateRange
    - getMeasurementByTypeId 


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


