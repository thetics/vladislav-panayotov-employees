import java.time.LocalDate;

public class Employee implements Comparable<Employee>{
    int EmpID;
    int ProjectID;
    LocalDate DateFrom;
    LocalDate DateTo;

    public Employee() {
    }

    public Employee(int empID, int projectID) {
        EmpID = empID;
        ProjectID = projectID;
    }

    public Employee(int empID, int projectID, LocalDate dateFrom, LocalDate dateTo) {
        EmpID = empID;
        ProjectID = projectID;
        DateFrom = dateFrom;
        DateTo = dateTo;
    }


    public int getEmpID() {

        return EmpID;
    }

    public void setEmpID(int empID) {
        if (Validator.validateEmpID(empID)) {
            this.EmpID = empID;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int projectID) {
        if (Validator.validateProjectID(projectID)) {
            this.ProjectID = projectID;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public LocalDate getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(String dateFrom) {
        if (dateFrom.equals("NULL")) {
            this.DateFrom=LocalDate.now();
        } else if (!dateFrom.equals("NULL")) {
            this.DateFrom=LocalDate.parse(dateFrom);
        }

    }
    public LocalDate getDateTo() {
        return DateTo;
    }

    public void setDateTo(String dateTo) {
        if (dateTo.equals("NULL")) {
            this.DateTo=LocalDate.now();
        } else if (!dateTo.equals("NULL")) {
            this.DateTo=LocalDate.parse(dateTo);
        }
    }

    @Override
    public int compareTo(Employee o) {
        return this.ProjectID-o.ProjectID;
    }
}
