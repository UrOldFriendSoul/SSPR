package ru.ulstu.is.sbapp.array.configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ulstu.is.sbapp.array.domain.ArrayNorm;
import ru.ulstu.is.sbapp.array.domain.ArrayTrans;

@Configuration
public class ArrayConfiguration {
    private final Logger log = LoggerFactory.getLogger(ArrayConfiguration.class);

    @Bean(value = "ArrayNorm", initMethod = "init", destroyMethod = "destroy")
    public ArrayNorm createNormArray() {
        log.info("Call createNormArray()");
        return new ArrayNorm();
    }

    @Bean(value = "ArrayTrans")
    public ArrayTrans createTransArray() {
        log.info("Call createTransArray()");
        return new ArrayTrans();
    }
}
