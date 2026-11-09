package com.joao.casadocodigo.greendogdelivery.controller;

import com.joao.casadocodigo.greendogdelivery.domain.Client;
import com.joao.casadocodigo.greendogdelivery.repository.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/")
    public ModelAndView list() {
        Iterable<Client> clients = this.clientRepository.findAll();
        return  new ModelAndView("clients/list", "clients", clients);
    }
}
