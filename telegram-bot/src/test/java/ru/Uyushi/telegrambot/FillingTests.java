package ru.Uyushi.telegrambot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Uyushi.telegrambot.entities.Category;
import ru.Uyushi.telegrambot.entities.Product;
import ru.Uyushi.telegrambot.repositories.CategoryRepository;
import ru.Uyushi.telegrambot.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Тестирование программы
 */
@SpringBootTest
public class FillingTests {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void fillCategories(){

        List<Category> categories = new ArrayList<>();

        //  Создание категорий

        addCategory("Пицца",null, categories);
        addCategory("Роллы",null, categories);
        addCategory("Бургеры",null, categories);
        addCategory("Напитки",null, categories);

        addCategory("Классические роллы", categories.get(1), categories);
        addCategory("Запеченные роллы",categories.get(1), categories);
        addCategory("Сладкие роллы",categories.get(1), categories);
        addCategory("Наборы",categories.get(1), categories);

        addCategory("Классические бургеры",categories.get(2), categories);
        addCategory("Острые бургеры",categories.get(2), categories);

        addCategory("Газированные напитки",categories.get(3), categories);
        addCategory("Энергетические напитки",categories.get(3), categories);
        addCategory("Соки",categories.get(3), categories);
        addCategory("Другие",categories.get(3), categories);

        //  Создание продуктов

        fillProducts(categories);

    }

    void fillProducts(List<Category> categories){

        for(int i=0;i<categories.size();i++){
            for(int j=1;j<4;j++)
                addProduct(categories.get(i),("Вкусные " + categories.get(i).getName() + String.valueOf(j)),
                    Math.floor(Math.random() * 100),"Крутые, очень вкусные " + categories.get(i).getName());
        }

    }

    void addCategory(String name, Category parent, List<Category> categories){

        Category category = new Category();
        category.setName(name);
        category.setParent(parent);
        categoryRepository.save(category);
        categories.add(category);

    }

    void addProduct(Category category, String name, Double price, String description){

        Product product = new Product();
        product.setCategory(category);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        productRepository.save(product);

    }

}
