package com.henriqueproject.course.repositories;

import com.henriqueproject.course.entities.OrderItem;
import com.henriqueproject.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

// não precisa colocar @Repository, pois está herdando do JpaRepository que
// ja está registrado como um componente do Spring
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {



}
