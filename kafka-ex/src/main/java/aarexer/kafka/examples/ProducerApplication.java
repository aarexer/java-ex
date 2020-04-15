package aarexer.kafka.examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

public class ProducerApplication {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final String topic = "Messages2";

        final Properties props = new Properties() {
            {
                put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
                put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
                put(VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);

            }
        };

        final KafkaProducer<String, byte[]> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 100; i++) {
            System.out.println("Produce " + i + ", to topic: " + topic);
            byte[] myvar = ("Any String you want " + i).getBytes();
            producer.send(new ProducerRecord<>(topic, Integer.toString(i), myvar)).get();
        }

        producer.close();
        System.out.println("Stop producing");
    }
}

