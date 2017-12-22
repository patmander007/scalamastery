package com.patmander.spark

import org.apache.log4j.Logger
import org.apache.spark.SparkContext
import org.apache.log4j.Level

object CustomerSpent {

  val logger: Logger = Logger.getLogger("org")

  def main(args: Array[String]) {
    logger.setLevel(Level.ERROR)
    val sc = new SparkContext("local[*]", "CustomerSpent")
    val lines = sc.textFile("C:\\SparkScala\\SparkScala\\customer-orders.csv")

    val customerExpenses = lines.map(line => line.split(",")).map(csvLine => (csvLine(0).toInt, csvLine(2).toFloat)).reduceByKey((val1, val2) => val1 + val2).sortByKey()
    
    
    println("Printing values sorted by customer ID") 
    
//    customerExpenses.collect().foreach(x=> println(f"customerId: ${x._1}, total: ${x._2}%.2f"))
    
    val customerExpensesSortedByAmount = customerExpenses.map(x=>(x._2, x._1)).sortByKey().map(x=>(x._2, x._1))
    
    println("Printing values sorted by amount desc") 
    
    customerExpensesSortedByAmount.collect().foreach(x=> println(f"customerId: ${x._1}, total: ${x._2}%.2f"))

  }

}