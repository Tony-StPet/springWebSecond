package shop.websecond.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.websecond.entities.Product;
import shop.websecond.servises.ProductService;

import java.util.List;

@Controller
public class CatalogController {
    @Autowired
    ProductService productService;

    @GetMapping("/catalog")
    public String showCatalog(Model model) {
        List<Product> spisok = productService.getAllList();

        model.addAttribute("spisok_prod", spisok);
        return "catalog";
    }

    @GetMapping("/newProduct")
    public String newProduct() {

        return "newProduct";
    }

    @GetMapping("/saveProduct")
    public String saveProduct(@RequestParam(name = "id") String strId,
                              @RequestParam (name = "name") String name,
                              @RequestParam(name = "cost") String strCost, Model model) {

        if (strId !=null && name !=null && strCost !=null) {

        try {
            int id = Integer.parseInt(strId);
            double cost = Double.parseDouble(strCost);
            Product prod = new Product(id, name, cost);
            productService.addProduct(prod);
            }
            catch (Exception e) {
                System.out.println("something wrong");
            }
        }

        return "redirect:/catalog";
    }
    @GetMapping("/findPage")
    public String findProduct(@RequestParam(name = "s", required = false) String s, Model model){
        if (s == null || s.isEmpty() || s.isBlank())
            return "/catalog";

        Product product = productService.findFirstByName(s);
        model.addAttribute("product", product);
        System.out.println(product);
        return "/findPage";
    }
}
