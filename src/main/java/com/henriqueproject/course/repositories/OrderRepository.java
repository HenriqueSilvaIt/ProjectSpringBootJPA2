package com.henriqueproject.course.repositories;

import com.henriqueproject.course.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

// não precisa colocar @Repository, pois está herdando do JpaRepository que
// ja está registrado como um componente do Spring
public interface OrderRepository extends JpaRepository<Order, Long> {



}
