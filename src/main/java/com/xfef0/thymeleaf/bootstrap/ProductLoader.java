package com.xfef0.thymeleaf.bootstrap;

import com.xfef0.thymeleaf.domain.Product;
import com.xfef0.thymeleaf.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductLoader.class);
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Product shirt = new Product();
        shirt.setDescription("Spring Framework xFEF0 Shirt");
        shirt.setPrice(new BigDecimal("18.95"));
        shirt.setImageUrl("http://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
        shirt.setProductId("235268845711068308");
        productRepository.save(shirt);

        LOGGER.info("Saved Shirt - id: {}", shirt.getId());

        Product mug = new Product();
        mug.setDescription("Spring Framework xFEF0 Mug");
        mug.setPrice(new BigDecimal("5.99"));
        mug.setImageUrl("http://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
        mug.setProductId("168639393495335947");
        productRepository.save(mug);

        LOGGER.info("Saved Mug - id: {}", mug.getId());
    }
}
