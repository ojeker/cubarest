package ch.so.agi.cubarest.entity;

import com.haulmont.cuba.core.entity.BaseUuidEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Table(name = "CUBAREST_UPDATE_UNIT")
@Entity(name = "cubarest_UpdateUnit")
public class UpdateUnit extends BaseUuidEntity {
    private static final long serialVersionUID = -6955827239319246750L;

    @NotNull
    @Column(name = "LAST_UPDATE", nullable = false)
    private LocalDateTime lastUpdate;

    @NotNull
    @Column(name = "IDENTIFIER", nullable = false)
    private String identifier;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DATA_THEME_ID")
    private DataTheme dataTheme;

    public DataTheme getDataTheme() {
        return dataTheme;
    }

    public void setDataTheme(DataTheme dataTheme) {
        this.dataTheme = dataTheme;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}