package com.github.nwzd.rsvp.spark.consumer;

import com.github.nwzd.rsvp.spark.model.Trade;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.VoidFunction2;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.StructType;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.from_json;
import static org.apache.spark.sql.types.DataTypes.*;

public class SparkApp {

    private static final String KAFKA_BROKERS = "localhost:9092";
    private static final String KAFKA_TOPIC = "test-topic";


    public static void main(String[] args) throws StreamingQueryException {
        System.setProperty("hadoop.home.dir", "/tmp/hadoop");
        final SparkConf conf = new SparkConf()
                .setMaster("local[*]")
                .setAppName("Spark Structured Streaming");

        SparkSession sparkSession = SparkSession.builder()
                .config(conf)
                .getOrCreate();

        Dataset<Trade> tradeDataset = sparkSession.readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", KAFKA_BROKERS)
                .option("subscribe", KAFKA_TOPIC)
                .option("startingOffsets", "earliest")
                .load()
                .select(from_json(col("value").cast("string"), Encoders.bean(Trade.class).schema()).alias("trade"))
                .select("trade.*")
                .as(Encoders.bean(Trade.class));
/*
        StreamingQuery query = tradeDataset.writeStream()
                .outputMode(OutputMode.Update())
                .format("console")
                .option("checkpointLocation", "/tmp/hadoop/checkpoint")
                .option("truncate", false)
                .start();

 */

        StreamingQuery query = tradeDataset.writeStream()
                .foreachBatch((VoidFunction2<Dataset<Trade>, Long>) (df, batchId) ->
                        df.write().format("mongo")
                                .option("spark.mongodb.output.uri", "mongodb://root:example@172.20.0.3/rsvps.trades?authSource=admin")
                                .option("spark.mongodb.database", "rsvps")
                                .option("spark.mongodb.collection", "trades")
                                .mode("append")
                                .save()
                )
                .start();

        query.awaitTermination();
    }
}
