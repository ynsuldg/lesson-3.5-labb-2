package se.iths.yunus.unittesting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.yunus.unittesting.service.ATMService;

@Controller
@RequestMapping("/balance")
public class ATMServiceController {

    private ATMService atmService;

    public ATMServiceController(ATMService atmService) {
        this.atmService = atmService;
    }

    @GetMapping
    public String showBalance(Model model) {
        double balance = atmService.showBalance();
        model.addAttribute("balance", balance);
        return "balance";
    }
}