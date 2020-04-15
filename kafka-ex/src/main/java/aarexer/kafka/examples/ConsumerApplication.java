package aarexer.kafka.examples;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static java.time.temporal.ChronoUnit.MILLIS;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;


public class ConsumerApplication {
    public static void main(String[] args) {
        final String topic = "Messages2";

        final Properties props = new Properties() {
            {
                put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
                put(GROUP_ID_CONFIG, "test0");
                put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
                put(VALUE_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);

            }
        };

        final KafkaConsumer<String, Integer> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));

        while (true) {
            ConsumerRecords<String, Integer> records = consumer.poll(Duration.of(1000, MILLIS));
            System.out.println(records.count());
            records.forEach(r ->
                    System.out.println("Offset: " + r.offset() + ", value: " + r.value()));
        }
    }
}
