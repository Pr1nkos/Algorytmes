package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SortMethods {

    private static int findSmallest(List<Integer> arr) {
        int smallest = arr.getFirst();
        int smallestIndex = 0;
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) < smallest) {
                smallest = arr.get(i);
                smallestIndex = i;
            }
        }
        return arr.get(smallestIndex);
    }

    public static List<Integer> sortSelection(List<Integer> arr) {
        List<Integer> sorted = new ArrayList<>();
        List<Integer> copy = new ArrayList<>(arr);
        while (!copy.isEmpty()) {
            int smallestNumber = findSmallest(copy);
            sorted.add(smallestNumber);
            copy.remove((Integer) smallestNumber);
        }
        return sorted;
    }

    public static List<Integer> quickSort(List<Integer> arr) {

        if (arr.size() <= 1) {
            return arr;
        } else {
            int pivotIndex = arr.size() / 2;
            int pivot = arr.get(pivotIndex);
            List<Integer> left = arr.stream()
                    .skip(1)
                    .filter(e -> e < pivot)
                    .toList();
            List<Integer> right = arr.stream()
                    .skip(1)
                    .filter(e -> e >= pivot)
                    .toList();


            List<Integer> sortedLeft = quickSort(left);
            List<Integer> sortedRight = quickSort(right);

            List<Integer> result = new ArrayList<>(sortedLeft);
            result.add(pivot);
            result.addAll(sortedRight);
            return result;
        }
    }



    public static <T> Result<T> measureTime(Callable<T> task) {
        long startTime;
        T result;
        long endTime;
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            startTime = System.currentTimeMillis();
            Future<T> future = executor.submit(task);

            result = null;

            try {
                result = future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            endTime = System.currentTimeMillis();
            executor.shutdown();
            try {
                if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }
        return new Result<>(result, endTime - startTime);
    }
}
