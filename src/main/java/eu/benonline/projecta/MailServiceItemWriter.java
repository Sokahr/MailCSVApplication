package eu.benonline.projecta;

import lombok.NoArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Benjamin Peter.
 */
@NoArgsConstructor
@Component
public class MailServiceItemWriter<EmailRecipient> implements ItemWriter<EmailRecipient> {

    @Autowired
    public MailService mailService;

    @Override
    public void write(List items) throws Exception {
        for (Iterator iterator = items.iterator(); iterator.hasNext(); ) {

            mailService.sendMailToRecipient((eu.benonline.projecta.EmailRecipient) iterator.next());
        }
    }
}
