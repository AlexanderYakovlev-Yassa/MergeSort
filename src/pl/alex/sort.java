package pl.alex;

public class sort {

    public static void main(String[] args) {

        String sourceFile = "arrayToSort.txt";
        String targetFile = "C:\\Users\\AlexanderYakovlev\\GitRepo\\MergeSort\\sortedArray.txt";

        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(sourceFile, targetFile);
    }
}
