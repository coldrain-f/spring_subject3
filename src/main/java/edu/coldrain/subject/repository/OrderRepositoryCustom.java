package edu.coldrain.subject.repository;

import edu.coldrain.subject.entity.Order;

import java.util.List;

public interface OrderRepositoryCustom {

    List<Order> findOrdersAll();

}
