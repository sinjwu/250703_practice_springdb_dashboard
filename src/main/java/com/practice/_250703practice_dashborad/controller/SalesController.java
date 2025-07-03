package com.practice._250703practice_dashborad.controller;

import com.practice._250703practice_dashborad.model.DailySales;
import com.practice._250703practice_dashborad.repository.DashboardRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/sales")
public class SalesController {
    private final DashboardRepository dashboardRepository;
    public SalesController(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }
    @GetMapping("/")
    public String dailySales(Model model) {
        List<DailySales> sales = dashboardRepository.findDailySales();
        model.addAttribute("sales", sales);
        return "daily-sales";
    }
    @GetMapping("/add")
    public String showAddForm() {
        return "sale-add";
    }
    @PostMapping("/add")
    public String addSale(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saleDate,
            @RequestParam int customerId,
            @RequestParam BigDecimal amount
            ) {
        dashboardRepository.saveSale(saleDate, customerId, amount);
        return "redirect:/";
    }
}
