package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;

    @GetMapping("/accidents")
    public String accidents(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        model.addAttribute("accidents", accidentService.findAllAccidents());
        return "index";
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident() {
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        accidentService.create(accident);
        return "redirect:/";
    }

    @GetMapping("/formUpdateAccident")
    public String viewEditAccident(@RequestParam("id") int id, Model model) {
        Optional<Accident> optionalAccident = accidentService.findById(id);
        if (optionalAccident.isPresent()) {
            model.addAttribute("accident", optionalAccident.get());
            return "editAccident";
        } else {
            model.addAttribute("textMessage", "Инцидент не найден, попробуйте позднее");
            return "message";
        }
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident, Model model) {
        Optional<Accident> optionalAccident = accidentService.update(accident);
        if (optionalAccident.isEmpty()) {
            model.addAttribute("textMessage", "Ошибка при обновлении, попробуйте позднее.");
            return "message";
        }
        return "redirect:/";
    }
}
