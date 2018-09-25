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
    customers.take(3).foreach(println)
    // 1,Richard,Hernandez,XXXXXXXXX,XXXXXXXXX,6303 Heather Plaza,Brownsville,TX,78521
    //customer_id, customer_fname, customer_lname........etc
    val orders = sc.textFile(datapath + "orders.txt")
    //verify the records :
    orders.take(3).foreach(println)
    // 1,2013-07-25 00:00:00.0,11599,CLOSED
    //order_id, order_date, order_customer_id, order_status
    // PROBLEM 2
    // 1)  Get the customers who have not placed any orders, sorted by customer_lname and then customer_fname
    // i) by RDD
    // ii) SPARK DataFrame
    // (order_id, order_date, order_customer_id, order_status) => (order_id, order)
    val ordersMap= orders.map(o => {
      val x=o.split(",")
      (x(0).toInt,o)
    })

    val customersMap= customers.map(c => {
      val x=c.split(",")
      (x(0).toInt,c)
    })

    val ordersCustomerJoin= ordersMap.rightOuterJoin(customersMap)

    val temp1 =ordersCustomerJoin.first()
    println("temp1 "+temp1)
println("ordersCustomerJoin 10 results \n")
    ordersCustomerJoin.take(10).foreach(println)


    //now filter out the customers who never placed the orders

    val customersWhoNeverPlacedOrders = ordersCustomerJoin.filter(x=> x._2._1 ==None).map(x=>x._2._2)
    println("ordersCustomerJoin.count= "+ordersCustomerJoin.count)
    println("customersWhoNeverPlacedOrders.count= "+customersWhoNeverPlacedOrders.count)

    println("total number of customers who didnt placed the orders= "+(ordersCustomerJoin.count-customersWhoNeverPlacedOrders.count) )

    customersWhoNeverPlacedOrders.take(10).foreach(println)
  }




  }



