package com.joao.casadocodigo.greendogdelivery.load;

import com.joao.casadocodigo.greendogdelivery.domain.Client;
import com.joao.casadocodigo.greendogdelivery.domain.Item;
import com.joao.casadocodigo.greendogdelivery.domain.Order;
import com.joao.casadocodigo.greendogdelivery.repository.ClientRepository;
import com.joao.casadocodigo.greendogdelivery.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RepositoryTest implements ApplicationRunner {

    Logger logger = LoggerFactory.getLogger(RepositoryTest.class.getSimpleName());

    private ClientRepository clientRepository;
    private final ItemRepository itemRepository;

    public RepositoryTest(ClientRepository clientRepository, ItemRepository itemRepository) {
        this.clientRepository = clientRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        logger.info(">>> Starting data load...");

        var dog1 = itemRepository.save(new Item("Green Dog tradicional", 25d));
        var dog2 = itemRepository.save(new Item("Green Dog tradicional picante", 27d));
        var dog3 = itemRepository.save(new Item("Green Dog mix salada", 30d));

        var fernando = new Client("Fernando Boaglio", "São Paulo");
        var ana = new Client("Ana Marco", "Rio de Janeiro");

        var orderListFernando1 = new ArrayList<Item>();
        orderListFernando1.add(dog1);

        var orderListAna1 = new ArrayList<Item>();
        orderListAna1.add(dog2);
        orderListAna1.add(dog3);

        var orderFernando = new Order(fernando, orderListFernando1, dog1.getPrice());
        fernando.newOrder(orderFernando);

        var orderAna = new Order(ana, orderListAna1, dog2.getPrice() + dog3.getPrice());
        ana.newOrder(orderAna);

        logger.info(">>> Order 1 - Fernando : " + orderFernando);
        logger.info(">>> Order 2 - Ana : " + orderAna);

        clientRepository.saveAndFlush(ana);

        logger.info(">>> Recorded client 2 : " + ana);

        var orderListFernando2 = new ArrayList<Item>();
        orderListFernando2.add(dog2);

        var order2Fernando = new Order(fernando, orderListFernando2, dog2.getPrice());
        fernando.newOrder(order2Fernando);
        logger.info(">>> Order 2 - Fernando : " + order2Fernando);

        clientRepository.saveAndFlush(fernando);
        logger.info(">>> Recorded client 1 : " + fernando);
    }

}
