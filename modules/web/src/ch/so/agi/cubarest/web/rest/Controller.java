package ch.so.agi.cubarest.web.rest;

import ch.so.agi.cubarest.web.rest.dto.DataThemePublication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.validation.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/pubsignal")
public class Controller {

    private static Logger log = LoggerFactory.getLogger(Controller.class);

    @GetMapping()
    public ResponseEntity<@Valid DataThemePublication> get() {
        DataThemePublication pub = null;

        if(Math.random() > 0.5) {
            pub = new DataThemePublication(
                    "ch.so.agi.av",
                    List.of("224", "225")
            );
        }
        else {
            pub = new DataThemePublication("ch.so.afu.gewaesserschutz", null);
        }

        return ResponseEntity.ok(pub);
    }

    @PutMapping()
    public ResponseEntity put(
            @RequestBody DataThemePublication pub) {

        log.info("Submitted info: {}", pub);

        ResponseEntity res = null;

        try {
            assertValid(pub);
            res = ResponseEntity.ok(HttpStatus.OK);
        }
        catch(RuntimeException r){
            res = ResponseEntity.badRequest().body(r.getMessage());
        }

        return res;
    }

    private static void assertValid(DataThemePublication pub){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<DataThemePublication>> violations = validator.validate(pub);

        if(violations != null && violations.size() > 0){
            String innerMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(" | "));
            throw new RuntimeException("Validation error(s): " + innerMessages);
        }
    }
}
