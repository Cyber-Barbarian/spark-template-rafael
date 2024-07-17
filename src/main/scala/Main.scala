package com.rafaelproenca.sparktemplate

import org.apache.spark.sql.SparkSession

object Main {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("spark-template-rafael")
      .master("local[*]")
      //.config("spark.driver.binderAddress","127.0.0.1")
      .getOrCreate()

    val df = spark.read.option("header", true)
      .csv("data/AAPL.csv")

    df.show()

  }
}