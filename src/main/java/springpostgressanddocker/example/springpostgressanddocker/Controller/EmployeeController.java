package springpostgressanddocker.example.springpostgressanddocker.Controller;


import lombok.SneakyThrows;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springpostgressanddocker.example.springpostgressanddocker.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import springpostgressanddocker.example.springpostgressanddocker.Repository.EmployeeRepository;
import springpostgressanddocker.example.springpostgressanddocker.Services.EmailSenderService;


import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepo;
    @Autowired
    private EmailSenderService emailService;


    public String sendmail() {
        try {
            emailService.sendEmailWithAttachment("kibetdonald97@gmail.com",  "There is a new employee that has been registered to the company employee database", "New Employee", "C:\\Users\\user\\Desktop\\employment-contract-revised.pdf");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return "Email has been sent";

    }
//    Create a method for posting the data


    @PostMapping("/addEmployee")
    @CrossOrigin(origins ="http://localhost:3000")
    public Employee addEmployee(@RequestBody Employee employee ){
//        sendmail();
       return employeeRepo.save(employee);
    }
//    Method to fetch the data from PostgreSQL
    @GetMapping("/getAllemployees")
    @CrossOrigin
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return ResponseEntity.ok(employeeRepo.findAll());
    }

//    Method to fetch based on employeeId
    @GetMapping("/getEmployee/{id}")
    @CrossOrigin
    public ResponseEntity<Employee> findEmployeeId(@PathVariable(value = "id") Integer id){
       Employee employee = employeeRepo.findById(id).orElseThrow(
               ()->new ResourceNotFoundException("Employee Not Found"+id));
               return ResponseEntity.ok().body(employee);
    }
//   Update employee records
    @PutMapping("/updateEmployee/{id}")
    @CrossOrigin
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
    @CrossOrigin
    public ResponseEntity<Void> deleteEmployee(@PathVariable(value="id") Integer id){
        Employee employee = employeeRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Employee Not Found"+id));
        employeeRepo.delete(employee);
        System.out.print("Deleted successfully");
        return ResponseEntity.ok().build();
    }

    @GetMapping("Say")
    public ResponseEntity<String> Say(){
        return ResponseEntity.ok("Hey Donald");
    }

    }
