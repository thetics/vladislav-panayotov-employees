import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter input file path and name: ");
        String path= reader.readLine();
        BufferedReader br = new BufferedReader(new FileReader(path));
//        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\panai\\OneDrive\\Desktop\\sirmaText.txt"));
        String s;
        ArrayList<Employee> employees = new ArrayList<>();
        while ((s = br.readLine()) != null) {
            String[] data = s.split(", ");
            Employee employee = new Employee(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
            employee.setDateFrom(data[2]);
            employee.setDateTo(data[3]);


            employees.add(employee);

        }


        Collections.sort(employees);


        long daysBetween;
        LinkedHashMap<Employee, Long> result = new LinkedHashMap<>();

        for (Employee employee : employees) {
            LocalDate dateBefore = employee.getDateFrom();
            LocalDate dateAfter = employee.getDateTo();
            daysBetween = DAYS.between(dateBefore, dateAfter);

            result.putIfAbsent(employee, daysBetween);
        }

        result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(2)
                .forEach(e -> {
                    System.out.printf(
                            String.format("Employee %d with project id %d - Worked %d days\n",
                                    e.getKey().getEmpID(),
                                    e.getKey().getProjectID(),
                                    e.getValue()));
                });


    }
}
