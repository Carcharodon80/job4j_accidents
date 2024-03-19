package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.AccidentService;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class IndexController {
    private final AccidentService accidentService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        model.addAttribute("accidents", accidentService.findAllAccidents());
        List<AccidentType> types = new ArrayList<>();
        types.add(new AccidentType(0, "Две машины"));
        types.add(new AccidentType(1, "Машина и человек"));
        types.add(new AccidentType(2, "Машина и велосипед"));
        model.addAttribute("types", types);
        return "index";
    }
}
