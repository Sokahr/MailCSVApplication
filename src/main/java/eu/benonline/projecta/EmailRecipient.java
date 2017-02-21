package eu.benonline.projecta;

import lombok.*;

/**
 * Created by Benjamin Peter.
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailRecipient {
    String email;
    String firstName;
    String lastName;
}
