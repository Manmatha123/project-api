package com.ecommerce.ecommerce.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.global.Status;
import com.ecommerce.ecommerce.order.model.Order;
import com.ecommerce.ecommerce.order.model.OrderDto;
import com.ecommerce.ecommerce.order.repository.OrderRepo;
import com.ecommerce.ecommerce.store.entity.Store;
import com.ecommerce.ecommerce.store.repository.StoreRepo;
import com.ecommerce.ecommerce.users.Entity.User;
import com.ecommerce.ecommerce.users.Repository.UserRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderServiceImpl implements OrderService{


    @Autowired
    OrderRepo orderRepo;

    @Autowired
    StoreRepo storeRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public Status deleteOrderById(Long id) {
        orderRepo.findById(id).ifPresentOrElse(order->{
            orderRepo.deleteById(id);
        },()->{
            throw new EntityNotFoundException("Order not found with id "+id);
        });
        return new Status(true,"Delete successfuly");
    }

    @Override
    public Order findByOrderId(Long id) {
        return orderRepo.findById(id).orElseThrow(()->{
            throw new EntityNotFoundException("Order not found with id "+id);
        });
    }

    @Override
    public List<OrderDto> listByStoreId(Long id) {
        return orderRepo.findAllByStore_id(id).stream().map(OrderDto::new).toList();
    }

    @Override
    public List<OrderDto> listByUserId(Long id) {
        return orderRepo.findAllByUser_id(id).stream().map(OrderDto::new).toList();
    }

    @Override
    public Status saveOrUpdate(OrderDto orderDto) {
        try {

            if (orderDto == null) {
                return new Status(false, "OrderDto cannot be null");
            }
    
            // Fetch the Store entity from the database to ensure it is managed by the current session
            Store store = storeRepo.findById(orderDto.getStore().getId()).orElse(null);
            if (store == null) {
                return new Status(false, "Store not found");
            }
            User user=userRepo.findById(orderDto.getUser().getId()).orElse(null);
        

        Order order=new Order(orderDto);
        order.setStore(store); 
        order.setUser(user); 
        orderRepo.save(order);
        return new Status(true,orderDto.getId()==null?"Added Successfuly":"Update Successfuly");
        } catch (Exception e) {
            return new Status(false,orderDto.getId()==null?"Fail to Added":"Fail to Update");
        }
    }

//     public Order setOrder(OrderDto orderDto){
//         return orderRepo.findById(orderDto.getId()).map(existOrder->{
// existOrder.setDevivered(orderDto.isDevivered());
// existOrder.setOrderid(orderDto.getOrderid());
// existOrder.setFinalprice(orderDto.getFinalprice());
// existOrder.setOnlinedeliver(orderDto.isOnlinedeliver());
// existOrder.set
// return existOrder;
//         }).orElseThrow(()->{
//             throw new EntityNotFoundException("Order not found with id "+id);
//         });
//     }


    
}
