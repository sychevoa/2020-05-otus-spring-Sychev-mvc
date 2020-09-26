package ru.otus.otusspring.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.otusspring.exception.CheckDayException;

import java.util.Objects;

@Service
public class IsDayOffServiceImpl implements IsDayOffService {

    private final static String ISDAYOFF_URI = "https://isdayoff.ru/{date}";

    @Override
    public boolean isDayOff(String date) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(ISDAYOFF_URI, String.class, date);

        if (response.getStatusCodeValue() != 200 || Objects.isNull(response.getBody())) {
            throw new CheckDayException("Service is unavailable, try again later");
        }

        return Integer.parseInt(response.getBody()) == 1;
    }
}
