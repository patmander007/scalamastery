package com.patmander.spark

import scala.math.max

import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.SparkContext

object MaxTemps {

//  def parseLine(line: String) = {
//    val fields = line.split(",")
//    val stationID = fields(0)
//    val entryType = fields(2)
//    val temperature = convertFahrenheitToCelcius(fields(3).toFloat)
//    (stationID, entryType, temperature)
//  }

  def convertFahrenheitToCelcius(tempInFahrenheit: Float): Float = {
    logger.info(f"converting: $tempInFahrenheit%.2f to celsius...")
    val tempInCelcius = tempInFahrenheit * .01f * (9.0f / 5.0f) + 32.0f
    logger.info(f"$tempInFahrenheit%.2f Fahrenheit is equal to $tempInCelcius%.2f Celcius")
    tempInCelcius
  }

  val logger = Logger.getLogger("org")

  def main(args: Array[String]): Unit = {
    logger.setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]", "MaxTemps")

    val lines = sc.textFile("../1800.csv")
//    lines.foreach(println)
    val filteredLines = lines.map(line=>line.split(",")).map(x=>(x(0), x(2), convertFahrenheitToCelcius(x(3).toFloat))).filter(_._2 == "TMAX").map(x => (x._1, x._3))

    val rddKVMax = filteredLines.reduceByKey((x, y) => max(x, y)).sortByKey();

    rddKVMax.foreach(x => println(f"ID: ${x._1}, temp: ${x._2}%.2f"))

  }
}