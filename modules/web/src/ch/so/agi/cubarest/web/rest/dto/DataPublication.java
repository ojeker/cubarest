package ch.so.agi.cubarest.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class DataPublication {

    @NotBlank(message = "Data identifier must not be blank.")
    String dataIdent;

    @JsonInclude(JsonInclude.Include.NON_EMPTY) // Greift nur beim serialisieren. Beim deserialisieren wird leere collection erzeugt
    List<@NotBlank(message = "Part identifier must not be blank.") String> partIdentifiers;

    public DataPublication(){}
    public DataPublication(String dataIdent, List<String> partIdentifiers){
        setDataIdent(dataIdent);
        setPartIdentifiers(partIdentifiers);
    }

    public String getDataIdent() {
        return dataIdent;
    }

    public void setDataIdent(String dataIdent) {
        this.dataIdent = dataIdent;
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
        return MessageFormat.format("identifier: {0}, parts: {1}", getDataIdent(), parts);
    }
}
