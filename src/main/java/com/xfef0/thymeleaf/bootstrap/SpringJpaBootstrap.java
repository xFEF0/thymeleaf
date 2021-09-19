package com.xfef0.thymeleaf.bootstrap;

import com.xfef0.thymeleaf.domain.Product;
import com.xfef0.thymeleaf.domain.Role;
import com.xfef0.thymeleaf.domain.User;
import com.xfef0.thymeleaf.repositories.ProductRepository;
import com.xfef0.thymeleaf.services.RoleService;
import com.xfef0.thymeleaf.services.UserService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringJpaBootstrap.class);
    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";

    private ProductRepository productRepository;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(@NotNull ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadUsers();
        loadRoles();
        assignUsersToUserRole();
        assignUsersToAdminRole();
    }

    private void assignUsersToAdminRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (ADMIN.equalsIgnoreCase(role.getRole())) {
                users.forEach(user -> {
                    if ("admin".equals(user.getUsername())) {
                        user.addRole(role);
                        
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }

    private void assignUsersToUserRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (USER.equalsIgnoreCase(role.getRole())) {
                users.forEach(user -> {
                    if (user.getUsername().equals("user")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }

    private void loadRoles() {
        Role roleUser = new Role();
        roleUser.setRole(USER);
        roleService.saveOrUpdate(roleUser);
        LOGGER.info("Saved role" + roleUser.getRole());

        Role roleAdmin = new Role();
        roleAdmin.setRole(ADMIN);
        roleService.saveOrUpdate(roleAdmin);
        LOGGER.info("Saved role" + roleAdmin.getRole());
    }

    private void loadUsers() {
        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword("user");
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUsername("admin");
        user2.setPassword("admin");
        userService.saveOrUpdate(user2);
    }

    private void loadProducts() {
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
