package Test2;

public class Test {
    public static void main(String[] args) {
        //Transfrom an object of class employee into JSON
        Employee emp1 = new Employee();
        emp1.setEmpNo(101);
        emp1.setName("Valva");
        emp1.setSalary(1000);
        String jsonEmployee = JsonUtil.convertJavaToJson(emp1);
        System.out.println(jsonEmployee);
        
        System.out.println("= = = = = = = = = = = = =");
        
        //Transfrom an object with JSON to a class
        Employee emp2 = JsonUtil.convertJsonToJava(jsonEmployee, Employee.class);
        System.out.println(emp2.getEmpNo()+" "+emp2.getName()+" "+emp2.getSalary());
    }   
}