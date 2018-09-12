package Test2;

//Imports for exceptions
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;

//Sets the order of the attributes
@JsonPropertyOrder(value={
    "name",
    "empNo"
    //"slary"
})

public class Employee implements Serializable {
    private int empNo;
    private String name;
    //Ignores an attribute when converting into JSON
    @JsonIgnore
    private double salary;

    public int getEmpNo() {
        return empNo;
    }
    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }       
}