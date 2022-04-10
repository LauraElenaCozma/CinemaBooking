package com.awbd.CinemaBookings.controller;

import com.awbd.CinemaBookings.domain.Customer;
import com.awbd.CinemaBookings.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/new")
    public String createCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "customerform";
    }

    @PostMapping
    public String saveOrUpdateCustomer(@ModelAttribute Customer customer) {
        Customer savedCustomer = customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping
    public ModelAndView getAllCustomers() {
        ModelAndView modelAndView = new ModelAndView("customers");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public String getCustomer(@PathVariable String id, Model model) {
        model.addAttribute("customer", customerService.findById(Long.valueOf(id)));
        return "customerinfo";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return "redirect:/customers";
    }
}
