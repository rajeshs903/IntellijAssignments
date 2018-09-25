import java.io.{File, PrintWriter}

import org.apache.spark.{SparkConf, SparkContext}


object Spark_SQL_DriverClass1 {

  val datapath= "G:\\retail_db\\"
  val conf =new SparkConf().setAppName("Spark_SQL_Driver").setMaster("local[*]")
  val sc=new SparkContext(conf)
sc.setLogLevel("ERROR")

  def main(args: Array[String]): Unit = {

    val customers = sc.textFile(datapath + "customers.txt")
    //verify the records :
    customers.take(10).foreach(println)
    // 1,Richard,Hernandez,XXXXXXXXX,XXXXXXXXX,6303 Heather Plaza,Brownsville,TX,78521
    //customer_id, customer_fname, customer_lname........etc
    val orders = sc.textFile(datapath + "orders.txt")
    //verify the records :
    orders.take(10).foreach(println)
    // 1,2013-07-25 00:00:00.0,11599,CLOSED
    //order_id, order_date, order_customer_id, order_status
    // PROBLEM 2
    // 1)  Get the customers who have not placed any orders, sorted by customer_lname and then customer_fname
    // i) by RDD
    // ii) SPARK DataFrame




  }




  }



