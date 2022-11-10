package springpostgressanddocker.example.springpostgressanddocker;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springpostgressanddocker.example.springpostgressanddocker.Models.AuditorAwareImpl;
import springpostgressanddocker.example.springpostgressanddocker.Services.EmailSenderService;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SpringpostgressanddockerApplication {
	@Autowired
	private EmailSenderService service;
	@Bean
	public AuditorAware<String> auditorAware(){
		return new AuditorAwareImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringpostgressanddockerApplication.class, args);
	}

//	@SneakyThrows
//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail(){
//		Sending simple emails without attachment
//		service.sendSimpleEmail("kibetdonald97@gmail.com",  "Greetings Donald, I" +
//				" am writing this email to notify you that you qualified for the Chevening scholarship" +
//				"at the Burnimngham university.", "Greetings"  );

//		sending emails with attachment
//		service.sendEmailWithAttachment("kibetdonald97@gmail.com",  "Greetings Donald", "This is a reminder", "C:\\Users\\user\\Desktop\\Scholarship.docx");
//	}
}
