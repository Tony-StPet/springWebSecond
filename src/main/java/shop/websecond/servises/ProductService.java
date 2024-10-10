package shop.websecond.servises;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import shop.websecond.entities.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    List<Product> products = new ArrayList<>();

    public List<Product> getAllList() {
        return products;
    }

    @PostConstruct
    public void fill(){
        try {products.add(new Product(1, "Макарошки", 49.99));
        products.add(new Product(2, "Тушенка", 245.99));
        products.add(new Product(3, "Лук", 37.99));
        products.add(new Product(4, "Картошечка", 43.70));
        products.add(new Product(5, "Сгущенка", 120));
        }
        catch (Exception e){
            System.out.println("что-то не так c заполнением списка продуктов" + e.getMessage());
        }
    }

    public void addProduct (Product product){
        products.add(product);
    }

    public Product findFirstByName (String s){
        for (Product p: products)
            if(p.getName().equals(s)) return p;

        return null;
    }

    public List<Product> filterByName(String s){
        return products.stream().filter(p->p.getName().equals(s)).toList();
    }

}
