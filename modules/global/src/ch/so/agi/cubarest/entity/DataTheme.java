package ch.so.agi.cubarest.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Table(name = "CUBAREST_DATA_THEME")
@Entity(name = "cubarest_DataTheme")
@NamePattern("%s|identifier")
public class DataTheme extends BaseUuidEntity {
    private static final long serialVersionUID = 4698073363094460849L;

    @NotNull
    @Column(name = "IDENTIFIER", nullable = false, unique = true)
    private String identifier;

    @Column(name = "LAST_UPDATE")
    private LocalDateTime lastUpdate;

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}