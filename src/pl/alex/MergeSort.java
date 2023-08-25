package pl.alex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

    public void sort(String source, String target) {

        int[] sourceArray = extractIntArrayFromFile(source);
        int[] sortedArray = mergeSort(sourceArray);
        saveResult(target, sortedArray);
    }

    private void saveResult(String target, int[] sortedArray) {

        List<String> listToSave = new ArrayList<>();
        for (int element: sortedArray) {
            listToSave.add(String.valueOf(element));
        }

        try {
            Files.write(Paths.get(target), listToSave);
        } catch (IOException e) {
            throw new RuntimeException("Fail to save target file", e);
        }
    }

    private int[] extractIntArrayFromFile(String source) {

        List<String> fileContent = null;

        try {
            fileContent = Files.readAllLines(Paths.get(source));
        } catch (IOException e) {
            throw new RuntimeException("Fail to read file content", e);
        }

        int[] intArrayToSort = convertToIntList(fileContent);

        return intArrayToSort;
    }

    private int[] mergeSort(int[] intArrayToSort) {

        if (intArrayToSort.length < 2) {
            return intArrayToSort;
        }

        int[] firstPart = getFirstPart(intArrayToSort);
        int[] secondPart = getSecondPart(intArrayToSort);

        int[] firstSorted = mergeSort(firstPart);
        int[] secondSorted = mergeSort(secondPart);

        return merge(firstSorted, secondSorted);
    }

    private int[] merge(int[] one, int[] two) {

        int oneIndex = 0;
        int twoIndex = 0;
        int resIndex = 0;

        int[] res = new int[one.length + two.length];

        while (resIndex < res.length) {

            if (one[oneIndex] < two[twoIndex]) {
                res[resIndex] = one[oneIndex];
                oneIndex++;
            } else {
                res[resIndex] = two[twoIndex];
                twoIndex++;
            }
            resIndex++;

            if (oneIndex == one.length) {
                while (twoIndex < two.length) {
                    res[resIndex] = two[twoIndex];
                    resIndex++;
                    twoIndex++;
                }
            } else if (twoIndex == two.length) {
                while (oneIndex < one.length) {
                    res[resIndex] = one[oneIndex];
                    resIndex++;
                    oneIndex++;
                }
            }
        }

        return res;
    }

    private int[] getSecondPart(int[] intArrayToSort) {

        int startIndex = intArrayToSort.length / 2;
        int[] secondPart = Arrays.copyOfRange(intArrayToSort, startIndex, intArrayToSort.length);

        return secondPart;
    }

    private int[] getFirstPart(int[] intArrayToSort) {

        int resLength = intArrayToSort.length / 2;
        int[] firstPart = Arrays.copyOfRange(intArrayToSort, 0, resLength);

        return firstPart;
    }

    private int[] convertToIntList(List<String> fileContent) {

        int[] intArray = new int[fileContent.size()];

        for (int i = 0; i < fileContent.size(); i++) {
            try {
                intArray[i] = Integer.parseInt(fileContent.get(i));
            } catch (Exception e) {
                throw new IllegalArgumentException("Fail to convert string to int");
            }
        }

        return intArray;
    }
}
