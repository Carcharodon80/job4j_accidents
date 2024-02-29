package ru.job4j.accidents.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Getter
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private Integer maxID = 0;

    {
        for (int i = 0; i < 5; i++) {
            addAccident();
        }
    }

    public synchronized void addAccident() {
        accidents.put(maxID, new Accident(maxID, "Name" + maxID, "Text" + maxID, "Address" + maxID));
        maxID++;
    }

    public Map<Integer, Accident> getAllAccidents() {
        return accidents;
    }
}