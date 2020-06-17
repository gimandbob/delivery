package covid;

import covid.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired
    private DeliveryRepository deliveryRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverKitChecked_Ship(@Payload KitChecked kitChecked){

        if(kitChecked.isMe()){
            System.out.println("##### listener Ship : " + kitChecked.toJson());
            Delivery delivery = new Delivery(kitChecked);
            deliveryRepository.save(delivery);
        }
    }

}
