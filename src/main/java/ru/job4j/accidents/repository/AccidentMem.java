package ru.job4j.accidents.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@Getter
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger maxID = new AtomicInteger(0);

    public AccidentMem() {
        for (int i = 0; i < 5; i++) {
            addAccident(new Accident(i, "Name" + i, "Text" + i, "Address" + i));
        }
    }

    public void addAccident(Accident accident) {
        accident.setId(getMaxID().get());
        maxID.incrementAndGet();
        accidents.put(accident.getId(), accident);
    }

    public Optional<Accident> updateAccident(Accident accident) {
        return Optional.ofNullable(accidents.computeIfPresent(accident.getId(), (key, value) -> value = accident));
    }

    public List<Accident> findAllAccidents() {
        return new ArrayList<>(accidents.values());
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }
}