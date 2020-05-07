package aarexer.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Message {
    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;

    @NotBlank(message = "Message is required field")
    @Length(max = 2048, message = "Message too long")
    private String text;

    private String tag;

    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Message(String text, String tag, User user) {
        this.user = user;
        this.text = text;
        this.tag = tag;
    }
}
