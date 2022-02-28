package com.example.apidemo.repository;

import com.example.apidemo.model.Random;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RandomRepository extends JpaRepository<Random,Long>, JpaSpecificationExecutor<Random> {


}
