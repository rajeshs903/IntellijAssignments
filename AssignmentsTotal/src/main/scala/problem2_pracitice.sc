
import org.apache.spark.{SparkConf, SparkContext}
val datapath= "G:\\retail_db\\"
val conf =new SparkConf().setAppName("scala_sheet").setMaster("local[*]")
val sc=new SparkContext(conf)

sc.setLogLevel("ERROR")

val customers = sc.textFile(datapath + "customers.txt")

val customersMap= customers.map(c => {
  val x=c.split(",")
  (x(0).toInt,c)
})


val orders = sc.textFile(datapath + "orders.txt")

val ordersMap= orders.map(o => {
  val x=o.split(",")
  (x(0).toInt,o)
})


val ordersCustomerJoin= ordersMap.rightOuterJoin(customersMap)

val temp1 =ordersCustomerJoin.first

//val customersWhoNeverPlacedOrders = ordersCustomerJoin.filter(x=> x._2._1 ==None).map(x=>x._2._2)
