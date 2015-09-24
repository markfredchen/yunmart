package com.handchina.yunmart.core.domain;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Persist AuditEvent managed by the Spring Boot actuator
 * @see org.springframework.boot.actuate.audit.AuditEvent
 */
@Entity
@Table(name = "YM_PersistentAuditEvent")
public class PersistentAuditEvent  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventID;

    @NotNull
    @Column(nullable = false)
    private String principal;

    @Column(name = "eventDate")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime auditEventDate;
    @Column(name = "eventType")
    private String auditEventType;

    private UUID domainOID;

    @ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="value")
    @CollectionTable(name="YM_PersistentAuditEventData", joinColumns=@JoinColumn(name="eventID"))
    private Map<String, String> data = new HashMap<>();

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public LocalDateTime getAuditEventDate() {
        return auditEventDate;
    }

    public void setAuditEventDate(LocalDateTime auditEventDate) {
        this.auditEventDate = auditEventDate;
    }

    public String getAuditEventType() {
        return auditEventType;
    }

    public void setAuditEventType(String auditEventType) {
        this.auditEventType = auditEventType;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public UUID getDomainOID() {
        return domainOID;
    }

    public void setDomainOID(UUID domainOID) {
        this.domainOID = domainOID;
    }
}
