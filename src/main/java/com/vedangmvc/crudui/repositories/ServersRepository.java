package com.vedangmvc.crudui.repositories;

import com.vedangmvc.crudui.models.Servers;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ServersRepository extends CrudRepository<Servers,String> {

    List<Servers> findByName(String name);
}

