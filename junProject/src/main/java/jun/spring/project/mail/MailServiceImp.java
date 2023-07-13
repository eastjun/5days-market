package jun.spring.project.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImp implements MailService{

	@Autowired
	JavaMailSender mailSender;
	
	@Override
	public void sendEmail(Mail mail) {
		
		final MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				
				final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				helper.setFrom(mail.getMailFrom());
				helper.setTo(mail.getMailTo());
				helper.setSubject(mail.getMailSubject());
				helper.setText(mail.getMailContent(), true);				
			}
		};
		mailSender.send(preparator);		
	}

}
