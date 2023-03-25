package com.api.controller;

import com.api.entity.Product;
import com.api.helper.Helper;
import com.api.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/product/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (Helper.checkExcelFormat(file)) {
        	String name = file.getOriginalFilename();
        	String fileName = name.substring(0, name.indexOf("."));
        	EntityTransaction txn = null;
    		try {
    			EntityManager entityManager = entityManagerFactory.createEntityManager();
    			txn = entityManager.getTransaction();
    			txn.begin();
    			entityManager.createNativeQuery( "CREATE TABLE "+fileName+" (productId int NOT NULL, productName varchar(255) NOT NULL, productDescription varchar(255), productPrice int, PRIMARY KEY (productId))" ).executeUpdate();

    			txn.commit();
    		}
    		catch ( Throwable e ) {
    			if ( txn != null && txn.isActive() ) {
    				txn.rollback();
    			}
    			throw e;
    		}
        	
            //this.productService.save(file);

            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }


    @GetMapping("/product")
    public List<Product> getAllProduct() {
        return this.productService.getAllProducts();
    }

}
