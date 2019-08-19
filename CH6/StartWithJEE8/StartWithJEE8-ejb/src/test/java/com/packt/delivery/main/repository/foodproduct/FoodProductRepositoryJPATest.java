package com.packt.delivery.main.repository.foodproduct;

import com.packt.delivery.abstraction.entity.FoodProduct;
import com.packt.delivery.main.repository.foodservice.FoodServiceData;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FoodProductRepositoryJPATest {
    @Mock
    private EntityManager entityManager;
    
    @Mock
    private TypedQuery typedQuery;
    
    @InjectMocks
    private FoodProductRepositoryJPA foodProductRepositoryJPA;

    @Test
    public void save() {
        FoodProduct newFoodProduct = new FoodProduct(null, "Pizza", 23500, "Cheese Pizza", true, "imageUrl2", "email1@email.com");
        
        FoodProductData foodProductData = new FoodProductData();
        foodProductData.setActive(newFoodProduct.isActive());
        foodProductData.setDescription(newFoodProduct.getDescription());
        foodProductData.setId(newFoodProduct.getId());
        foodProductData.setImageUrl(newFoodProduct.getImageUrl());
        foodProductData.setName(newFoodProduct.getName());
        foodProductData.setPrice(newFoodProduct.getPrice());
        
        foodProductRepositoryJPA.save(newFoodProduct);
        
        verify(entityManager).persist(foodProductData);
    }
    
    @Test
    public void update() {
        FoodProduct newFoodProduct = new FoodProduct(1, "Pizza", 23500, "Cheese Pizza", true, "imageUrl2", "email1@email.com");
        
        FoodServiceData foodServiceData = new FoodServiceData();
        foodServiceData.setEmail(newFoodProduct.getFoodService());
        
        FoodProductData foodProductData = new FoodProductData();
        foodProductData.setActive(newFoodProduct.isActive());
        foodProductData.setDescription(newFoodProduct.getDescription());
        foodProductData.setId(newFoodProduct.getId());
        foodProductData.setImageUrl(newFoodProduct.getImageUrl());
        foodProductData.setName(newFoodProduct.getName());
        foodProductData.setPrice(newFoodProduct.getPrice());
        foodProductData.setFoodService(foodServiceData);
        
        when(entityManager.merge(foodProductData)).thenReturn(foodProductData);
        
        foodProductRepositoryJPA.update(newFoodProduct);
        
        verify(entityManager).merge(foodProductData);
    }
    
    @Test
    public void getAll() {
        FoodProduct newFoodProduct = new FoodProduct(1, "Pizza", 23500, "Cheese Pizza", true, "imageUrl2", "email1@email.com");
        
        FoodServiceData foodServiceData = new FoodServiceData();
        foodServiceData.setEmail(newFoodProduct.getFoodService());
        
        FoodProductData foodProductData = new FoodProductData();
        foodProductData.setActive(newFoodProduct.isActive());
        foodProductData.setDescription(newFoodProduct.getDescription());
        foodProductData.setId(newFoodProduct.getId());
        foodProductData.setImageUrl(newFoodProduct.getImageUrl());
        foodProductData.setName(newFoodProduct.getName());
        foodProductData.setPrice(newFoodProduct.getPrice());
        foodProductData.setFoodService(foodServiceData);
        
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(foodProductData));
        when(entityManager.createNamedQuery("FoodProductData.findAll", FoodProductData.class)).thenReturn(typedQuery);
                
        List<FoodProduct> foodProducts = foodProductRepositoryJPA.getAll();
        
        assertThat(foodProducts).isEqualTo(Arrays.asList(newFoodProduct));
    }
    
    @Test
    public void getByFoodService() {
        FoodProduct newFoodProduct = new FoodProduct(1, "Pizza", 23500, "Cheese Pizza", true, "imageUrl2", "email1@email.com");
        
        FoodServiceData foodServiceData = new FoodServiceData();
        foodServiceData.setEmail(newFoodProduct.getFoodService());
        
        FoodProductData foodProductData = new FoodProductData();
        foodProductData.setActive(newFoodProduct.isActive());
        foodProductData.setDescription(newFoodProduct.getDescription());
        foodProductData.setId(newFoodProduct.getId());
        foodProductData.setImageUrl(newFoodProduct.getImageUrl());
        foodProductData.setName(newFoodProduct.getName());
        foodProductData.setPrice(newFoodProduct.getPrice());
        foodProductData.setFoodService(foodServiceData);
        
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(foodProductData));
        when(typedQuery.setParameter("email", "email1@email.com")).thenReturn(typedQuery);
        when(entityManager.createNamedQuery("FoodProductData.findByFoodService", FoodProductData.class)).thenReturn(typedQuery);
                
        List<FoodProduct> foodProducts = foodProductRepositoryJPA.getByFoodService("email1@email.com");
        
        assertThat(foodProducts).isEqualTo(Arrays.asList(newFoodProduct));
    }
    
}
