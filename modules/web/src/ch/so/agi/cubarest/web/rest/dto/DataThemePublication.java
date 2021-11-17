package ch.so.agi.cubarest.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class DataThemePublication {

    @NotBlank(message = "Theme identifier must not be blank.")
    String themeIdent;

    @JsonInclude(JsonInclude.Include.NON_EMPTY) // Greift nur beim serialisieren. Beim deserialisieren wird leere collection erzeugt
    List<@NotBlank(message = "Part identifier must not be blank.") String> partIdentifiers;

    public DataThemePublication(){}
    public DataThemePublication(String themeIdent, List<String> partIdentifiers){
        setThemeIdent(themeIdent);
        setPartIdentifiers(partIdentifiers);
    }

    public String getThemeIdent() {
        return themeIdent;
    }

    public void setThemeIdent(String themeIdent) {
        this.themeIdent = themeIdent;
    }

    public List<String> getPartIdentifiers() {
        if(partIdentifiers == null)
            partIdentifiers = List.of();

        return partIdentifiers;
    }

    public void setPartIdentifiers(List<String> partIdentifiers) {
        this.partIdentifiers = partIdentifiers;
    }

    @Override
    public String toString(){
        String parts = getPartIdentifiers().stream().collect(Collectors.joining(", "));
        return MessageFormat.format("identifier: {0}, parts: {1}", getThemeIdent(), parts);
    }
}
