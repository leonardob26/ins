package com.ins.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ins.jpa.People;

@Repository
public interface PeopleRepository extends CrudRepository<People, Integer> {
	List<People> findByPname(String pname);
}
