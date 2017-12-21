package com.patmander.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.math.min

/** Find the minimum temperature by weather station */
object MinTemperatures {
  
  def parseLine(line:String)= {
    val fields = line.split(",")
    val stationID = fields(0)
    val entryType = fields(2)
    val temperature = convertFahrenheitToCelcius(fields(3).toFloat)
    (stationID, entryType, temperature)
  }
  
  def convertFahrenheitToCelcius(tempInFahrenheit: Float) : Float = {
    println(f"converting: $tempInFahrenheit%.2f to celsius...")
    val tempInCelcius = tempInFahrenheit * .01f * (9.0f / 5.0f) + 32.0f
    println(f"$tempInFahrenheit%.2f Fahrenheit is equal to $tempInCelcius%.2f Celcius")
    tempInCelcius
  }
  
  
    /** Our main function where the action happens */
  def main(args: Array[String]) {
   
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "MinTemperatures")
    
    // Read each line of input data
    val lines = sc.textFile("../1800.csv")
    
    // Convert to (stationID, entryType, temperature) tuples
    val parsedLines = lines.map(parseLine)
    
    // Filter out all but TMIN entries
    val minTemps = parsedLines.filter(x => x._2 == "TMIN")
    
    // Convert to (stationID, temperature)
    val stationTemps = minTemps.map(x => (x._1, x._3.toFloat))
    
    // Reduce by stationID retaining the minimum temperature found
    val minTempsByStation = stationTemps.reduceByKey( (x,y) => min(x,y))
    
    // Collect, format, and print the results
    val results = minTempsByStation.collect()
    
    for (result <- results.sorted) {
       val station = result._1
       val temp = result._2
       val formattedTemp = f"$temp%.2f F"
       println(s"$station minimum temperature: $formattedTemp") 
    }
    
  }
}