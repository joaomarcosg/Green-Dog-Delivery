package com.joao.casadocodigo.greendogdelivery.load;

import com.joao.casadocodigo.greendogdelivery.domain.Client;
import com.joao.casadocodigo.greendogdelivery.domain.Item;
import com.joao.casadocodigo.greendogdelivery.domain.Order;
import com.joao.casadocodigo.greendogdelivery.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

@Component
public class RepositoryTest implements ApplicationRunner {

    Logger logger = LoggerFactory.getLogger(RepositoryTest.class.getSimpleName());

    private static final long ID_CLIENT_FERNANDO = 11L;
    private static final long ID_CLIENT_BRUNA = 22L;

    private static final long ID_ITEM1 = 100L;
    private static final long ID_ITEM2 = 101L;
    private static final long ID_ITEM3 = 103L;

    private static final long ID_ORDER1 = 1000L;
    private static final long ID_ORDER2 = 1001L;
    private static final long ID_ORDER3 = 1002L;

    private ClientRepository clientRepository;

    public RepositoryTest(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        logger.info(">>> Starting data load...");

        var fernando = new Client(ID_CLIENT_FERNANDO, "Fernando Boaglio", "São Paulo");
        var bruna = new Client(ID_CLIENT_BRUNA, "Bruna Marco", "Rio de Janeiro");

        var dog1 = new Item(ID_ITEM1, "Green Dog tradicional", 25d);
        var dog2 = new Item(ID_ITEM2, "Green Dog tradicional picante", 25d);
        var dog3 = new Item(ID_ITEM3, "Green Dog mix salada", 30d);

        var orderListFernando1 = new ArrayList<Item>();
        orderListFernando1.add(dog1);

        var orderListBruna1 = new ArrayList<Item>();
        orderListBruna1.add(dog2);
        orderListBruna1.add(dog3);

        var orderFernando = new Order(ID_ORDER1, fernando, orderListFernando1, dog1.getPrice());
        fernando.newOrder(orderFernando);

        var orderBruna = new Order(ID_ORDER2, bruna, orderListBruna1, dog2.getPrice() + dog3.getPrice());
        bruna.newOrder(orderBruna);

        logger.info(">>> Order 1 - Fernando : " + orderFernando);
        logger.info(">>> Order 2 - Bruna : " + orderBruna);

        clientRepository.saveAndFlush(bruna);

        logger.info(">>> Recorded client 2 : " + bruna);

        var orderListFernando2 = new ArrayList<Item>();
        orderListFernando2.add(dog2);

        var order2Fernando = new Order(ID_ORDER3, fernando, orderListFernando2, dog2.getPrice());
        fernando.newOrder(order2Fernando);
        logger.info(">>> Order 2 - Fernando : " + order2Fernando);

        clientRepository.saveAndFlush(fernando);
        logger.info(">>> Recorded client 1 : " + fernando);
    }

}
