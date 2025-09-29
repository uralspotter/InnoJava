package task2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamExamples {
    public static void main(String[] args) {
        System.out.println(getThirdMax(Arrays.asList(5, 2, 10, 9, 4, 3, 10, 1, 13)));
        System.out.println(getUniqueThirdMax(Arrays.asList(5, 2, 10, 9, 4, 3, 10, 1, 13)));
        Employee employee1 = new Employee("Ivan", 35, "test");
        Employee employee2 = new Employee("Sergey", 55, "Engineer");
        Employee employee3 = new Employee("Anton", 25, "test");
        Employee employee4 = new Employee("Roman", 45, "test");
        Employee employee5 = new Employee("Petr", 25, "test");
        Employee employee6 = new Employee("Alexandr", 25, "Engineer");
        Employee employee7 = new Employee("Andrey", 25, "test");
        List<Employee> employees = Arrays.asList(employee1, employee2, employee3, employee4, employee5, employee6, employee7);
        System.out.println(getOldestEmployees(employees));
        System.out.println(getAverageAgeByPosition(employees, "Engineer"));
        List<String> strings = Arrays.asList("airplane", "world", "java");
        System.out.println(getLongestWord(strings));
        String exampleString = "test test test world world";
        System.out.println(getWordsCount(exampleString).size());
        System.out.println(getWordsCount(exampleString).get("test"));
        System.out.println(getWordsCount(exampleString).get("world"));
        printSortedStrings(strings);
        String[] exampleStrings = {"test test test", "test test test world world", "airplane world java"};
        System.out.println(getLongestWordFromMassive(exampleStrings));
    }

    public static int getThirdMax(List<Integer> list){
        return list
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .toList()
                .getLast();
    }

    public static int getUniqueThirdMax(List<Integer> list){
        return list
                .stream()
                .sorted(Comparator.reverseOrder())
                .distinct()
                .limit(3)
                .toList()
                .getLast();
    }

    public static List<String> getOldestEmployees(List<Employee> list){
        return list
                .stream()
                .sorted(Comparator.comparingInt(Employee::getAge).reversed())
                .map(Employee::getName)
                .distinct()
                .limit(3)
                .toList();
    }

    public static double getAverageAgeByPosition(List<Employee> list, String position){
        return list
                .stream()
                .filter(employee -> employee.getPosition().equals(position))
                .mapToInt(Employee::getAge)
                .average()
                .getAsDouble();
    }

    public static String getLongestWord(List<String> list){
        return list
                .stream()
                .max(Comparator.comparing(String::length))
                .orElseThrow();
    }

    public static Map<String, Long> getWordsCount(String string){
        return Arrays.stream(string.split(" "))
                .distinct()
                .collect(Collectors.toMap(
                    s -> s,
                    s -> Arrays.stream(string.split(" ")).filter(str -> str.equals(s)).count()
                ));
    }

    public static void printSortedStrings(List<String> strings){
        strings
            .stream()
            .sorted(Comparator.comparing(String::length))
            .toList()
            .forEach(System.out::println);
    }

    public static String getLongestWordFromMassive(String[] strings){
        return getLongestWord(
                Arrays
                    .stream(strings)
                    .map(s -> s.split(" "))
                    .flatMap(Arrays::stream)
                    .toList()
        );
    }
}
