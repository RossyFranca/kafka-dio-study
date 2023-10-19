package com.franca.kafkaconsumer.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.franca.kafkaconsumer.data.OrderData;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SaveOrderService {

    @KafkaListener(topics = "saveorder", groupId = "MicrosserviceSaveOrder")
    private void execute(ConsumerRecord<String, String> record){
        log.info("Key = " + record.key());
        log.info("Headers = " + record.headers());
        log.info("Partition = " + record.partition());

        String data = record.value();

        ObjectMapper mapper = new ObjectMapper();
        OrderData order = null;

        try {
            order = mapper.readValue(data, OrderData.class);
        } catch (JsonProcessingException e) {
           log.error("Fail to convert event [data = ]", data, e);
        }

        log.info("Received event = " , order);

        //TODO: Gravar no banco de dados
        //TODO: Retornar que os dados foram salvos no banco de dados
    }
}
