package controllers

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

object ReadImage {
  type Image = Array[Byte]

  val env = StreamExecutionEnvironment.getExecutionEnvironment
  env.setParallelism(1)




}
