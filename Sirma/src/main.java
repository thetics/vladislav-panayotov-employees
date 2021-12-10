import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter input file path and name: ");
        BufferedReader br = new BufferedReader(new FileReader(reader.readLine()));
//        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\panai\\OneDrive\\Desktop\\sirmaTextt.txt"));
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

        long daysBetween1;
        long daysBetween2;
        long longestPeriodTogetherOnProject = 0;
        LinkedHashMap<ArrayList<Employee>, Long> result = new LinkedHashMap<>();

        for (int i = 0; i < employees.size(); i++) {
            for (int j = i + 1; j < employees.size(); j++) {
                if (employees.get(i).ProjectID == employees.get(j).getProjectID()) {
                    Employee emp1 = employees.get(i);
                    Employee emp2 = employees.get(j);

                    daysBetween1 = DAYS.between(emp1.getDateFrom(), emp1.getDateTo());
                    daysBetween2 = DAYS.between(emp2.getDateFrom(), emp2.getDateTo());
                    long longestPeriodTogether = Math.min(daysBetween1, daysBetween2);

                    ArrayList<Employee> toAdd = new ArrayList<>();
                    toAdd.add(emp1);
                    toAdd.add(emp2);

                    if (longestPeriodTogether > longestPeriodTogetherOnProject) {
                        longestPeriodTogetherOnProject = longestPeriodTogether;
                        result.putIfAbsent(toAdd, longestPeriodTogetherOnProject);
                    }

//                   else if (longestPeriodTogether<longestPeriodTogetherOnProject){
//                        result.put(toAdd, result.get(toAdd)+longestPeriodTogether);
//                    }
//
                }
            }
        }

        result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(1).forEach(e -> {
                    System.out.println(String.format
                            ("Employee ID= %d and employee ID= %d worked the longest period on a project together(%d days).",
                                    e.getKey().get(0).getEmpID(), e.getKey().get(1).getEmpID(), e.getValue()));
                });


    }
}
