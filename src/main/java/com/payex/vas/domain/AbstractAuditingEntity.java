package com.payex.vas.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Base abstract class for entities which will hold definitions for created, last modified by and created,
 * last modified by date.
 */

@MappedSuperclass
@Data
public abstract class AbstractAuditingEntity implements Serializable {
    
    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "created_by", length = 50, updatable = false)
    private String createdBy;

    @Column(name = "modified")
    private LocalDateTime modified;

    @Column(name = "modified_by", length = 50)
    private String modifiedBy;
}
