package edu.iu.adicher.historyservice.rabbitmq;

import edu.iu.adicher.historyservice.model.PrimesRecord;
import edu.iu.adicher.historyservice.repository.PrimesHistoryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class MQReceiver {


    private final PrimesHistoryRepository primesHistoryRepository;


    public MQReceiver (PrimesHistoryRepository primesHistoryRepository) {

        this.primesHistoryRepository = primesHistoryRepository;

    }

    @RabbitListener(queues = {"primes"})

    public void receiveMessage(@Payload String message) {

        System.out.println(message);

        Gson gson = new Gson();

        PrimesRecord primesRecord = gson

                .fromJson(message, PrimesRecord.class);

        primesHistoryRepository.save(primesRecord);

    }

}