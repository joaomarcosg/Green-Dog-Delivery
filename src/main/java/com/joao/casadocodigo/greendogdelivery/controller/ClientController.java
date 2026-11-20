package com.joao.casadocodigo.greendogdelivery.controller;

import com.joao.casadocodigo.greendogdelivery.domain.Client;
import com.joao.casadocodigo.greendogdelivery.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/{id}")
    public ModelAndView view(@PathVariable("id") Client client) {
        return new ModelAndView("clients/view", "client", client);
    }

    @GetMapping("/new")
    public String createForm(@ModelAttribute Client client) {
        return "clients/form";
    }

    @PostMapping(params = "form")
    public ModelAndView create(@Valid Client client, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("clients/form", "formErrors", result.getFieldErrors());
        }
        client = this.clientRepository.save(client);
        redirect.addAttribute("globalMessage", "Cliente adicionado com sucesso!");
        return new ModelAndView("redirect:/clients/{client.id}", "client.id", client.getId());
    }

    @GetMapping(value = "update/{id}")
    public ModelAndView updateForm(@PathVariable("id") Client client) {
        return new ModelAndView("clients/form", "client", client);
    }

    @GetMapping(value = "remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id, RedirectAttributes redirect) {
        this.clientRepository.deleteById(id);
        var clients = this.clientRepository.findAll();
        var mv = new ModelAndView("clients/list", "clients", clients);
        mv.addObject("globalMessage", "Cliente removido com sucesso!");
        return mv;
    }
}
