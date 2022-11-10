package springpostgressanddocker.example.springpostgressanddocker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springpostgressanddocker.example.springpostgressanddocker.Models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
