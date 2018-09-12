package prueba2.json;

import prueba2.json.*;

public class Test {
    public static void main(String[] args) {
        Employee emp1 = new Employee();
        emp1.setEmpNo(101);
        emp1.setName("Valva");
        emp1.setSalary(1000);
        String jsonEmployee = JsonUtil.convertJavaToJson(emp1);
        System.out.println(jsonEmployee);
        
        /*
        System.out.println("=============");
        
        Employee emp2 = JsonUtil.convertJsonToJava(jsonEmployee, Employee.class);
        System.out.println(emp2.getEmpNo()+" "+emp2.getName()+" "+emp2.getSalary());
        */
    }   
}
