package ru.ulstu.is.sbapp.array.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.sbapp.array.service.ArrayService;

@RestController
public class ArrayController {
    private final ArrayService arrayService;

    public ArrayController(ArrayService arrayService) {
        this.arrayService = arrayService;
    }

    @GetMapping("/")
    public String array(@RequestParam(value = "row", defaultValue = "5") int row,
                        @RequestParam(value = "column", defaultValue = "5") int column,
                        @RequestParam(value = "arrayType", defaultValue = "ArrayNorm") String arrayType) {
        return arrayService.runArray(row, column, arrayType);
    }
}
