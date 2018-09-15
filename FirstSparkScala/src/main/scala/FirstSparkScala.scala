
import org.apache.spark.{SparkContext, SparkConf}

object FirstSparkScala {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Ex1_SimpleRDD").setMaster("local[4]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    // put some data in an RDD
    val numbers = 1 to 100
    val numbersRDD = sc.parallelize(numbers,numSlices = 100)
    numbersRDD.persist()
    println("Print each element of the original RDD")
    numbersRDD.collect().foreach(println)
  }
}