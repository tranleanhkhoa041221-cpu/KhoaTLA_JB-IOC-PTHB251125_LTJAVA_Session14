package Business.impl;

import Business.IProductService;
import Model.Product;
import Model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements IProductService {
    private List<Product> products = new ArrayList<>();

    @Override
    public String getMaxId() {
        int max = 0;
        for (Product product : products) {
            int number = Integer.parseInt(product.getId().substring(1));
            if (number > max) {
                max = number;
            }
        }
        return String.format("P%03d", max + 1);
    }


    @Override
    public List<Product> findByName(String name) {
        List<Product> searchedProducts = new ArrayList<>();
        for (Product product : products) {
            if(product.getName().toLowerCase().contains(name.toLowerCase())){
                searchedProducts.add(product);
            }
        }
        return searchedProducts;
    }

 //   @Override
//    public int countByPriceRange(double min, double max) {
//        int total = 0;
//        for (Product p : products) {
//            if (p.getPrice() >= min && p.getPrice() <= max) {
//                total += p.getQuantity();
//            }
//        }
//        return total;
//    }

    @Override
    public List<Product> findByPriceRange(double min, double max) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() >= min && product.getPrice() <= max) {
                result.add(product);
            }
        }
        return result;
    }

 //   @Override
//    public List<Product> findByQuantity(int minQuantity, int maxQuantity) {
//        List<Product> result4 = new ArrayList<>();
//        for (Product product : products) {
//            if (product.getQuantity() >= minQuantity && product.getQuantity() <= maxQuantity) {
//                result4.add(product);
//            }
//        }
//        return result4;
//    }

    @Override
    public boolean existById(String id) {
        for (Product product : products){
            if (product.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, Integer> statisticsProduct() {
        return Map.of();
    }

    @Override
    public void add(Product product) {
        product.setId(getMaxId());
        products.add(product);
    }

    @Override
    public void update(Product product, String id) {
        products.set(products.indexOf(findById(id)), product );
    }

    @Override
    public void delete(String id) {
        Product product = findById(id);
        if (product != null) {
            products.remove(product);
        }

    }

    @Override
    public Product findById(String id) {
        for(Product product : products){
            if (product.getId().equals(id)){
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void sort() {

    }
}
