package eu.benonline.projecta;

import lombok.extern.log4j.Log4j;
import lombok.val;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by Benjamin Peter.
 */
@Service
@Log4j
public class MailService {

    @Async
    public void sendMailToRecipient(EmailRecipient emailRecipient)
    {
        String status;
        try {
            //Fake Mailsending delay
            synchronized (this) {
                this.wait(500);
            }
            val logMessage = String.format("Email for %s %s has been send to %s",
                    emailRecipient.getFirstName(),
                    emailRecipient.getLastName(),
                    emailRecipient.getEmail());
            log.info(logMessage);
            status = "SEND";
        } catch (InterruptedException e) {
            e.printStackTrace();
            status = "ERROR";
        }
    }
}
