package Business;

import Model.Product;

import java.util.List;
import java.util.Map;

public interface IProductService extends IBaseService<Product,String>{
    String getMaxId();
    List<Product> findByName(String name);
//    int countByPriceRange(double min, double max);
    List<Product> findByPriceRange(double min, double max);
//    List<Product> findByQuantity(int minQuantity, int maxQuantity);
    boolean existById(String id);
    Map<String, Integer> statisticsProduct();
}
