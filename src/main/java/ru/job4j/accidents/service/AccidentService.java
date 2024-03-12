package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.List;

@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentMem accidentMem;

    public List<Accident> findAllAccidents() {
        return accidentMem.findAllAccidents();
    }

    public Accident findById(int id) {
        return accidentMem.findById(id).orElseThrow();
    }

    public synchronized void create(Accident accident) {
        accidentMem.addAccident(accident);
    }

    public synchronized void update(Accident accident) {
        accidentMem.updateAccident(accident);
    }
}
