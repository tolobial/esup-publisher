/**
 * Copyright (C) 2014 Esup Portail http://www.esup-portail.org
 * @Author (C) 2012 Julien Gribonvald <julien.gribonvald@recia.fr>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *                 http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.esupportail.publisher.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.esupportail.publisher.domain.enums.WritingFormat;
import org.esupportail.publisher.domain.enums.WritingMode;
import org.esupportail.publisher.domain.util.CstPropertiesLength;
import org.esupportail.publisher.domain.util.CustomEnumSerializer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * @author GIP RECIA - Julien Gribonvald 16 juin 2014
 */
@Data
@ToString(callSuper = true, exclude = "organizationReaderRedactors")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = {"name"})
@Entity
@Table(name = "T_REDACTOR")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Redactor extends AbstractAutoGeneratedIdEntity implements Serializable {

    /** */
    private static final long serialVersionUID = -4180550119349006534L;

    /** This field corresponds to the database column name. */
    @NotNull
    @NonNull
    @Size(min = 3, max = CstPropertiesLength.NAME)
    @Column(unique = true, nullable = false, length = CstPropertiesLength.NAME)
    private String name;

    /** This field corresponds to the database column display_name. */
    @NotNull
    @NonNull
    @Size(min = 3, max = CstPropertiesLength.DISPLAYNAME)
    @Column(length = CstPropertiesLength.DISPLAYNAME, name = "display_name", nullable = false)
    private String displayName;

    /** This field corresponds to the database column description. */
    @NotNull
    @NonNull
    @Size(min = 5, max = CstPropertiesLength.DESCRIPTION)
    @Column(nullable = false, length = CstPropertiesLength.DESCRIPTION)
    private String description;

    @NotNull
    @NonNull
    @Column(length = 50, name = "format", nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonSerialize(using = CustomEnumSerializer.class)
    private WritingFormat format = WritingFormat.HTML;

    @NotNull
    @NonNull
    @Column(length = 50, name = "writing_mode", nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonSerialize(using = CustomEnumSerializer.class)
    private WritingMode writingMode = WritingMode.STATIC;

    /** This field corresponds to the database column name. */
    @Min(1)
    @Max(2)
    @Column(length = 1, name = "nb_level_classification", nullable = false)
    private int nbLevelsOfClassification;

    @OneToMany(mappedBy = "context.redactor", fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonBackReference("redactor-key")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Publisher> organizationReaderRedactors;// = new HashSet<Publisher>();

    /**
     * @param name
     * @param displayName
     * @param description
     * @param nbLevelsOfClassification
     */
    public Redactor(@NotNull final String name, @NotNull final String displayName, @NotNull final String description,
                    @NotNull final WritingFormat format, @NotNull final WritingMode writingMode, final int nbLevelsOfClassification) {
        super();
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.format = format;
        this.writingMode = writingMode;
        this.nbLevelsOfClassification = nbLevelsOfClassification;
    }

}