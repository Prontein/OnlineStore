package ru.geekbrains.web.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

@Data
@NoArgsConstructor
public class StatisticMessageDTO {
    private ConcurrentHashMap <String,Long> serviceMap;
}
