package com.ikea.product;

import com.ikea.product.model.Color;
import com.ikea.product.model.Product;
import com.ikea.product.model.ProductType;

import com.ikea.product.service.ColorService;
import com.ikea.product.service.ProductService;
import com.ikea.product.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ProductApplication implements CommandLineRunner {
	@Autowired
	ColorService colorService;
	@Autowired
	ProductService productService;
	@Autowired
	ProductTypeService productTypeService;

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Color red = new Color("1","Red");
		Color blue = new Color("2","Blue");
		Color green = new Color("3","Green");
		Color brown = new Color("4","Brown");
		Color white = new Color("5","White");

		colorService.addColor(red);
		colorService.addColor(blue);
		colorService.addColor(green);
		colorService.addColor(brown);
		colorService.addColor(white);


		ProductType sofa= new ProductType("100","Sofa");
		ProductType wardrobes = new ProductType("101","Wardrobes");
		ProductType table = new ProductType("102","Table");
		productTypeService.addProductType(sofa);
		productTypeService.addProductType(table);
		productTypeService.addProductType(wardrobes);

		productService.deleteAllProducts();

		productService.addProduct(new Product("10001","SANDSBERG",table,List.of(red,blue)));
		productService.addProduct(new Product("10002","Hinged wardrobes",wardrobes,List.of(red,green)));
		productService.addProduct(new Product("10003","TONSTAD",table,List.of(brown,white)));
		productService.addProduct(new Product("10004","LISABO",table,List.of(brown,green)));
		productService.addProduct(new Product("10005","NORDEN",table,List.of(red,white,brown,blue)));
		productService.addProduct(new Product("10006","LACK",table,List.of(brown,white)));
		productService.addProduct(new Product("10007","Open wardrobes",wardrobes,List.of(red,green)));
		productService.addProduct(new Product("10008","Hallway wardrobes",wardrobes,List.of(red,green)));
		productService.addProduct(new Product("10009","Walk in wardrob",wardrobes,List.of(red,white,brown)));
		productService.addProduct(new Product("10010","FRIHETEN",sofa,List.of(red,brown)));
		productService.addProduct(new Product("10011","GLOSTAD",sofa,List.of(red,green)));
		productService.addProduct(new Product("10012","VIMLE",sofa,List.of(blue,brown)));
		productService.addProduct(new Product("10013","KIVIK",sofa,List.of(white)));

	}
}
