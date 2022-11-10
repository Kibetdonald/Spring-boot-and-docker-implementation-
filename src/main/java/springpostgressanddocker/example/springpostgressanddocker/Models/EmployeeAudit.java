package springpostgressanddocker.example.springpostgressanddocker.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

//Provides inheritance support
@MappedSuperclass
//Persist the entities
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"Created_At", "Updated_At"}, allowGetters = true)
public class EmployeeAudit {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Created_At", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Updated_At", nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    @Column(name="Created_By", nullable = false)
    @CreatedBy
    private String createdBy;

    @Column(name="Updated_By", nullable = false)
    @LastModifiedBy
    private String modifiedBy;


}
