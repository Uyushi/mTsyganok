package ru.Uyushi.telegrambot.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.Uyushi.telegrambot.entities.*;
import ru.Uyushi.telegrambot.repositories.*;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServiceLayer implements EntitiesService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientOrderRepository clientOrderRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;

    /**
     * Получить список товаров в категории
     * @param id идентификатор категории
     */
    @Override
    public List<Product> getProductsByCategoryId(Long id){

        Category category = new Category();
        Product exampleProduct = new Product();
        category.setId(id);
        exampleProduct.setCategory(category);
        return productRepository.findAll(Example.of(exampleProduct));

    }
    /**
     * Получить список заказов клиента
     * @param id идентификатор клиента
     */
    @Override
    public List<ClientOrder> getClientOrders(Long id){

        Client client = new Client();
        ClientOrder exampleClientOrder = new ClientOrder();
        client.setId(id);
        exampleClientOrder.setClient(client);
        return clientOrderRepository.findAll(Example.of(exampleClientOrder));

    }
    /**
     * Получить список всех товаров во всех заказах клиента
     * @param id идентификатор клиента
     */
    @Override
    public List<Product> getClientProducts(Long id){

        Client client = new Client();
        ClientOrder clientOrder = new ClientOrder();
        OrderProduct exampleOrderProduct = new OrderProduct();
        client.setId(id);
        clientOrder.setClient(client);
        exampleOrderProduct.setClientOrder(clientOrder);
        return productRepository.findAll(Example.of(exampleOrderProduct.getProduct()));

    }
    /**
     * Получить указанное кол-во самых популярных (наибольшее
     * количество штук в заказах) товаров среди клиентов
     * @param limit максимальное кол-во товаров
     */
    @Override
    public List<Product> getTopPopularProducts(Integer limit){

        List<Product> mostPopularProducts = null;
        List<OrderProduct> orderProducts = orderProductRepository.findAll();
        Product mostPopProduct = new Product();

        for(int i=0;limit>i;i++) {
            for(int j=0;orderProducts.size()>=j; j++){
                if(orderProducts.get(j).getCountProduct() > orderProducts.get(j + 1).getCountProduct())
                    mostPopProduct = orderProducts.get(j).getProduct();
            }
            mostPopularProducts.add(mostPopProduct);
            orderProducts.remove(mostPopProduct);
        }
        return mostPopularProducts;
    }
    /**
     * Найти всех клиентов по подстроке имени
     * @param name подстрока имени клиента
     */
    @Override
    public List<Client> searchClientsByName(String name) {
        throw new UnsupportedOperationException("Доп. задание");
    }
    /**
     * Найти все продукты по подстроке названия
     * @param name подстрока названия продукта
     */
    @Override
    public List<Product> searchProductsByName(String name) {
        throw new UnsupportedOperationException("Доп. задание");
    }
}
