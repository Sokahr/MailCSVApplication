package eu.benonline.projecta;

import org.springframework.batch.item.ItemProcessor;

/**
 * Created by Benjamin Peter.
 */
public class EmailRecipientItemProcessor implements ItemProcessor<EmailRecipient, EmailRecipient> {

    @Override
    public EmailRecipient process(EmailRecipient item) throws Exception {
        return new EmailRecipient(item.getEmail(), item.getFirstName(), item.getLastName());
    }
}
