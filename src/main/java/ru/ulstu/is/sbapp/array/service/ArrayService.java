package ru.ulstu.is.sbapp.array.service;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.ulstu.is.sbapp.array.domain.Array;

@Service
public class ArrayService {
    private final ApplicationContext applicationContext;

    public ArrayService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String runArray(int row, int column, String arrayType) {
        final Array array = (Array) applicationContext.getBean(arrayType);
        return array.runArray(row, column);
    }
}
