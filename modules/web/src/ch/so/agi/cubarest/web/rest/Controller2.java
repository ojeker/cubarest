package ch.so.agi.cubarest.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/doc")
public class Controller2 {

    private static Logger log = LoggerFactory.getLogger(Controller2.class);

    @GetMapping()
    public ResponseEntity<String> get(@NotEmpty @RequestParam String dataident, @NotEmpty @RequestParam String published) {

        //OffsetDateTime odt = OffsetDateTime.parse( published );

        String template = "<!DOCTYPE html>\n" +
                "<html lang=\"de\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Datadoc</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <h1>Datadoc von {0}</h1>\n" +
                "    <p>Freue mich auf den Publisher :)</p>\n" +
                "  </body>\n" +
                "</html>";

        String body = MessageFormat.format(template, dataident);

        HttpHeaders heads = new HttpHeaders();
        heads.setContentType(MediaType.TEXT_HTML);

        /*
        List<Charset> charsets = new ArrayList<>(2);
        charsets.add(Charset.forName("UTF-8"));
        heads.setAcceptCharset(charsets);
         */

        log.info("Returned doc for {}", dataident);

        return new ResponseEntity<String>(body, heads, HttpStatus.OK);
    }
}
