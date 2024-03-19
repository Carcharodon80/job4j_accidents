package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.AccidentService;

import java.util.List;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidents;
    private final List<AccidentType> types = List.of(new AccidentType(0, "Две машины"),
            new AccidentType(1, "Машина и человек"),
            new AccidentType(2, "Машина и велосипед"));

    @GetMapping("/accidents")
    public String accidents(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        model.addAttribute("accidents", accidents.findAllAccidents());
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
        accidents.create(accident);
        return "redirect:/";
    }

    @GetMapping("/formUpdateAccident")
    public String viewEditAccident(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidents.findById(id));
        model.addAttribute("types", types);
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident) {
        AccidentType type = types.get(accident.getType().getId());
        accident.setType(type);
        accidents.update(accident);
        return "redirect:/";
    }
}
