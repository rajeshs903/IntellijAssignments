import java.io.{File, PrintWriter}

import org.apache.spark.{SparkConf, SparkContext}


object Spark_SQL_DriverClass1 {

  val datapath= "G:\\retail_db\\"
  //problem_1
  // Get the customers who have not placed any orders, sorted by customer_lname and then customer_fname
  val conf =new SparkConf().setAppName("Spark_SQL_Driver").setMaster("local[*]")
  val sc=new SparkContext(conf)
sc.setLogLevel("ERROR")

  def main(args: Array[String]): Unit = {

    val customers = sc.textFile(datapath + "customers.txt")
    customers.take(10).foreach(println)
  }


  }



