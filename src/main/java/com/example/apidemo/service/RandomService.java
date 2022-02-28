package com.example.apidemo.service;

import com.example.apidemo.model.Random;
import com.example.apidemo.repository.RandomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RandomService {

    private final RandomRepository repo;

    public Page<Random> findByArgs(final Optional<String> nombre, final Optional<Integer> numero1, Pageable pageable){
        Specification<Random> specNombre = new Specification<Random>() {
            @Override
            public Predicate toPredicate(Root<Random> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if(nombre.isPresent()){
                    return criteriaBuilder.like(criteriaBuilder.lower(root.get("nombre")),nombre.get());
                }else{
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };

        Specification<Random> numero1IgualQue = new Specification<Random>() {
            @Override
            public Predicate toPredicate(Root<Random> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if(numero1.isPresent()){
                    return criteriaBuilder.equal(root.get("numero1"),numero1.get());
                }else{
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };

        Specification<Random> ambas = specNombre.and(numero1IgualQue);

        return this.repo.findAll(ambas,pageable);
    }

    public Optional<Random> insertRandom(Random r){
        return Optional.of(repo.save(r));
    }

}
