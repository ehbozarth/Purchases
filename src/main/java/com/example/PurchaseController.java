package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileReader;

/**
 * Created by earlbozarth on 11/11/15.
 */

@Controller
public class PurchaseController {

    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    PurchaseRepository purchaseRepo;


    @PostConstruct
    public void init() {
        if (customerRepo.count() == 0) {
            String fileContent = readFile("customers.csv");
            String [] lines = fileContent.split("\n");
            for(String line : lines){
                if(line == lines[0]){
                    continue;
                }
                Customer tempCustomer = new Customer();
                String [] columns = line.split(",");
                tempCustomer.name = columns[0];
                tempCustomer.email = columns[1];
                customerRepo.save(tempCustomer);
            }
        }
        if (purchaseRepo.count() == 0) {
            String fileContent = readFile("purchases.csv");
            String [] lines = fileContent.split("\n");
            for(String line : lines){
                if(line == lines[0]){
                    continue;
                }
                Purchase tempPurchase = new Purchase();
                String [] columns = line.split(",");

                Customer customer = customerRepo.findOne(Integer.valueOf(columns[0]));
                tempPurchase.date = columns[1];
                tempPurchase.credit_card = columns[2];
                tempPurchase.cvv = columns[3];
                tempPurchase.category = columns[4];
                tempPurchase.customer = customer;
                purchaseRepo.save(tempPurchase);
            }
        }
    }//End of init()


    @RequestMapping("/")
    public String home(Model model, String category){

        if(category != null) {
            model.addAttribute("customers", customerRepo.findAll());
            model.addAttribute("purchases", purchaseRepo.findByCategory(category));
            return "home";
        }
            model.addAttribute("customers", customerRepo.findAll());
            model.addAttribute("purchases", purchaseRepo.findAll());
            return "home";


    }


    static String readFile(String fileName) {
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String(fileContent);
        } catch (Exception e) {
            return null;
        }
    }//End of readFile()







}
