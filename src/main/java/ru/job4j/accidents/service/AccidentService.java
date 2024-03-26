package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentMem accidentMem;

    public List<Accident> findAllAccidents() {
        return accidentMem.findAllAccidents();
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    public void create(Accident accident) {
        accidentMem.addAccident(accident);
    }

    public boolean update(Accident accident) {
        return accidentMem.updateAccident(accident);
    }
}
