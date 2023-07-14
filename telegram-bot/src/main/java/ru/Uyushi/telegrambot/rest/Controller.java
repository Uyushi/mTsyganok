package ru.Uyushi.telegrambot.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.Uyushi.telegrambot.entities.*;

import java.util.List;

@RestController
public class Controller {

    private final ServiceLayer serviceLayer;

    public Controller(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @GetMapping(path = "/rest/categoryproducts/{id}")
    public List<Product> getProductsByCategoryId(@PathVariable Long id){
        return serviceLayer.getProductsByCategoryId(id);
    }

    @GetMapping(path = "/rest/clientorders/{id}")
    public List<ClientOrder> getClientOrders(@PathVariable Long id){
        return serviceLayer.getClientOrders(id);
    }

    @GetMapping(path = "/rest/clientproducts/{id}")
    public List<Product> getClientProducts(@PathVariable Long id){
        return serviceLayer.getClientProducts(id);
    }

    @GetMapping(path = "/rest/mostpopproducts/{id}")
    public List<Product> getTopPopularProducts(@PathVariable Integer limit){
        return serviceLayer.getTopPopularProducts(limit);
    }

    public List<Client> searchClientsByName(String name) {
        throw new UnsupportedOperationException("Доп. задание");
    }

    public List<Product> searchProductsByName(String name) {
        throw new UnsupportedOperationException("Доп. задание");
    }
}
