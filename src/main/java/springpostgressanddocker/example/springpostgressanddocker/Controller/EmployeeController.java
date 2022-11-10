package springpostgressanddocker.example.springpostgressanddocker.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springpostgressanddocker.example.springpostgressanddocker.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import springpostgressanddocker.example.springpostgressanddocker.Repository.EmployeeRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepo;

//    Create a method for posting the data
    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee ){
       return employeeRepo.save(employee);
    }

//    Method to fetch the data from PostgreSQL
    @GetMapping("/getAllemployees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return ResponseEntity.ok(employeeRepo.findAll());
    }

//    Method to fetch based on employeeId
    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<Employee> findEmployeeId(@PathVariable(value = "id") Integer id){
       Employee employee = employeeRepo.findById(id).orElseThrow(
               ()->new ResourceNotFoundException("Employee Not Found"+id));
               return ResponseEntity.ok().body(employee);
    }
//   Update employee records
    @PutMapping("/updateEmployee/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable int id) {
        return employeeRepo.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    return employeeRepo.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepo.save(newEmployee);
                });
    }
//    Delete employee
    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(value="id") Integer id){
        Employee employee = employeeRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Employee Not Found"+id));
        employeeRepo.delete(employee);
        System.out.print("Deleted successfully");
        return ResponseEntity.ok().build();
    }

}
