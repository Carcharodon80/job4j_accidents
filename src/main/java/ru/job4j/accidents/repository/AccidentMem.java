package ru.job4j.accidents.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@Getter
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger maxID = new AtomicInteger(0);

    public AccidentMem() {
        for (int i = 0; i < 5; i++) {
            addNewAccident();
        }
    }

    public synchronized void addNewAccident() {
        accidents.put(maxID.get(), new Accident(maxID.get(), "Name" + maxID, "Text" + maxID, "Address" + maxID));
        maxID.incrementAndGet();
    }

    public synchronized void addAccident(Accident accident) {
        accident.setId(getMaxID().get());
        maxID.incrementAndGet();
        accidents.put(accident.getId(), accident);
    }

    public List<Accident> findAllAccidents() {
        return new ArrayList<>(accidents.values());
    }
}