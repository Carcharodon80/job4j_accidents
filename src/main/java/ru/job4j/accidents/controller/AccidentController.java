package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.AccidentService;

import java.util.List;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;
    private final List<AccidentType> types = List.of(new AccidentType(0, "Две машины"),
            new AccidentType(1, "Машина и человек"),
            new AccidentType(2, "Машина и велосипед"));

    @GetMapping("/accidents")
    public String accidents(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        model.addAttribute("accidents", accidentService.findAllAccidents());
        return "index";
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", types);
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        AccidentType type = types.get(accident.getType().getId());
        accident.setType(type);
        accidentService.create(accident);
        return "redirect:/";
    }

    @GetMapping("/formUpdateAccident")
    public String viewEditAccident(@RequestParam("id") int id, Model model) {
        Optional<Accident> optionalAccident = accidentService.findById(id);
        if (optionalAccident.isEmpty()) {
            model.addAttribute("textMessage", "Инцидент не найден, попробуйте позднее");
            return "message";
        }
        model.addAttribute("accident", optionalAccident.get());
        model.addAttribute("types", types);
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident, Model model) {
        AccidentType type = types.get(accident.getType().getId());
        accident.setType(type);
        if (!accidentService.update(accident)) {
            model.addAttribute("textMessage", "Ошибка при обновлении, попробуйте позднее.");
            return "message";
        }
        return "redirect:/";
    }
}
