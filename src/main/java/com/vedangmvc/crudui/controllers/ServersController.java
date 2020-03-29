package com.vedangmvc.crudui.controllers;


import com.vedangmvc.crudui.models.Servers;
import com.vedangmvc.crudui.repositories.ServersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



@Controller
public class  ServersController {
    @Autowired
    private ServersRepository repository;

    @RequestMapping(value = "/server")
    public String getAllServers(Model model) {
        model.addAttribute("servers", repository.findAll());
        return "server";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        return "create";
    }

    @RequestMapping("/save")
    public String save(@RequestParam String name, @RequestParam String language, @RequestParam String framework) {
        Servers server = new Servers();
        server.setName(name);
        server.setLanguage(language);
        server.setFramework(framework);
        repository.save(server);

        return "redirect:/show/" + server.getId();
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("server", repository.findById(id).get());
        return "show";
    }


    @RequestMapping("/delete")
    public String delete(@RequestParam String id) {
        Optional<Servers> server = repository.findById(id);
        repository.delete(server.get());

        return "redirect:/server";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("server", repository.findById(id).get());
        return "edit";
    }

    @RequestMapping("/update")
    public String update(@RequestParam String id, @RequestParam String name, @RequestParam String language, @RequestParam String framework) {
        Optional<Servers> server = repository.findById(id);
        server.get().setId(id);
        server.get().setName(name);
        server.get().setLanguage(language);
        server.get().setFramework(framework);
        repository.save(server.get());

        return "redirect:/show/" + server.get().getId();
    }
}
