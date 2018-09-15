import java.io.{File, PrintWriter}

import org.apache.spark.{SparkConf, SparkContext}

object DriverClass1 {
  val conf =new SparkConf().setAppName("DriverClass1").setMaster("local[4]")
  val sc=new SparkContext(conf)
  sc.setLogLevel("ERROR")
  val filenamewithPath="C:\\Users\\Rajesh\\IdeaProjects\\AssignmentsTotal\\file1.txt"

  def main(args: Array[String]): Unit = {

   // generateFile(50,filenamewithPath)
    //println("Sum of total values in file is "+calculateSumRDD(filenamewithPath))
    calculateAvgOfRDD(filenamewithPath)

  }


  def generateFile(number : Int , filenameWithPath: String)={

    val pw= new PrintWriter(new File(filenameWithPath))
    for (a <- 2 to number)
      {
        if(a%2==0)
          {
            pw.write(a.toDouble.toString)
            pw.write("\n")
          }
      }
    pw.close()
  }
  def calculateSumRDD (filepath : String)={
    val fileRDD= sc.textFile(filepath).map(x=>x.toDouble)
    val sumofRDD=fileRDD.reduce(_+_)
    sumofRDD
  }


  def calculateAvgOfRDD (filepath :String)={
val i =1
    val fileRDD= sc.textFile(filepath).map(x=>x.toDouble)
val result =fileRDD.reduce((x,y)=> (x+y))
    //rddKV.collect.foreach(println)
    println(result/fileRDD.count())
  }
}