# Steps

## Installing spark
- install scala, sbt, python normaly
- download the tar.gz from the latest version of apache spark and create SPARK_HOME
- %SPARK_HOME% and %SPARK_HOME%/bin to the path envirroment variables
- copy winutils.exe and hadoop.dll to %SPARK_HOME%/bin
- cmd > pyspark
- cmd spark-shell
- http://localhost:4040/

# Steps

## new scala project
- sbt
- jdk correto-17
- sbt 1.9.8
- scala 2.13.12

## dependencies (sbt)
- spark core (3.5.1)
- spark sql (3.5.1, remove provided)
- use sparkVersion variable

## Application
- download kaggle stock market dataset, ([link](https://www.kaggle.com/datasets/jacksoncrow/stock-market-dataset) -> AAPL.csv)
- create a local SparkSession and read csv
- when we run we had an error

```  log
Exception in thread "main" java.lang.IllegalAccessError: class org.apache.spark.storage.StorageUtils$ (in unnamed module @0x6cb107fd) cannot access class sun.nio.ch.DirectBuffer (in module java.base) because module java.base does not export sun.nio.ch to unnamed module @0x6cb107fd
	at org.apache.spark.storage.StorageUtils$.<clinit>(StorageUtils.scala:213)
	...

```
-  solve with VM configuration argument:
edit configutations -> modify options -> add vm options -> --add-exports java.base/sun.nio.ch=ALL-UNNAMED
- we can do the same creating a new configuration template-> add vm options -> --add-exports java.base/sun.nio.ch=ALL-UNNAMED. 
- Delete the old one and run main, and will be auto created
