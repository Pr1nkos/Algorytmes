import utils.Result;
import utils.SortMethods;

import java.util.*;
import java.util.stream.IntStream;


public class Main {
    private static Random rand = new Random();

    public static void main(String[] args) {

        int numbersToGenerate = 10000;

        List<Integer> listOfGeneratedNumbers = IntStream.range(0, numbersToGenerate)
                .mapToObj(i -> rand.nextInt(10000))
                .distinct()
                .toList();
        System.out.println("Сгенерированный лист: " + listOfGeneratedNumbers);

        //Методы сортировки

        Result<List<Integer>> resultSelectionSort = SortMethods.measureTime(() -> SortMethods.sortSelection(new ArrayList<>(listOfGeneratedNumbers)));
        Result<List<Integer>> resultQuicksort = SortMethods.measureTime(() -> SortMethods.quickSort(new ArrayList<>(listOfGeneratedNumbers)));
        System.out.println("Сортированный лист : " + resultSelectionSort.getValue());

        //Замеры времени
        System.out.println("Сортировка выбором: " + resultSelectionSort.getTime() + " миллисекунд");
        System.out.println("Быстрая сортировка: " + resultQuicksort.getTime() + " миллисекунд");



    }
}
