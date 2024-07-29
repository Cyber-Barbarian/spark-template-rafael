package com.rafaelproenca.sparktemplate

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{col, lit, year}
import org.apache.spark.sql.types._

object Main {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("spark-template-rafael")
      .master("local[*]")
      .config("spark.driver.binderAddress","127.0.0.1")
      .getOrCreate()

    val userSchema = new StructType().add("Date", DateType)
      .add("Open", DoubleType)
      .add("High", DoubleType)
      .add("Low", DoubleType)
      .add("Close", DoubleType)
      .add("Adj Close", DoubleType)
      .add("Volume", IntegerType)

    //leitura normal
    /*
        val df = spark.read.option("header", true)
        .option("sep", ",")
          .schema(userSchema)
        .csv("data/")
         */


    val rawPath = "data/"
        val dfSilver = spark.readStream
          .option("sep", ",")
          .option("header", true)
          .schema(userSchema)
          //.option("maxFilesPerTrigger", 1)
          .csv(rawPath)

    //writeStream na tela do console
    def writeStreamDataOnConsole(dataFrame: DataFrame): Unit = {
      /**
       * write the given dataframe into a file or console
       * :params: dataframe
       */
      dataFrame
        .writeStream
        .format("console")
        .outputMode("append")
        .start()
        .awaitTermination(30)
    }

    //writeStream na pasta output/silver output/checkpoint
    def writeStreamDataOnParquet(dataFrame: DataFrame): Unit = {

    val querySilver = dataFrame.writeStream
      .format("parquet")
      .outputMode("append")
      .option("checkpointLocation", "output/checkpoint")
      .start("output/silver")

    querySilver.awaitTermination(30)
      querySilver.stop()

    }
    writeStreamDataOnConsole(dfSilver)

    //writeStreamDataOnParquet(dfSilver)

    spark.stop()



  }
}