package org.esupportail.publisher.web.rest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.esupportail.publisher.web.rest.dto.evaluators.EvaluatorDTO;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@ToString(callSuper=true)
public class PermOnSubjWClassifDTO extends PermOnSubjDTO implements Serializable {

    /** */
    private static final long serialVersionUID = 2573623640790851210L;

    @Setter
    private Set<ContextKeyDTO> authorizedContexts = new HashSet<ContextKeyDTO>();

    /**
     * Constructor to use when creating the object from JPA model.
     * @param modelId
     * @param creationDate
     * @param lastUpdateDate
     * @param createdBy
     * @param lastUpdateBy
     * @param context
     * @param evaluator
     * @param rolesOnSubjects
     * @param authorizedContexts
     */
    public PermOnSubjWClassifDTO(@NotNull final Long modelId, final DateTime creationDate,
            final DateTime lastUpdateDate, @NotNull final SubjectDTO createdBy, final SubjectDTO lastUpdateBy,
            @NotNull final ContextKeyDTO context, @NotNull final EvaluatorDTO evaluator,
            @NotNull @Size(min=1) final Set<SubjectPermKeyDTO> rolesOnSubjects,
            final Set<ContextKeyDTO> authorizedContexts) {
        super(modelId, creationDate, lastUpdateDate, createdBy, lastUpdateBy,
                context, evaluator, rolesOnSubjects);
        this.authorizedContexts = authorizedContexts;
    }

    /**
     * Constructor to use when creating a new object.
     * @param createdBy
     * @param context
     */
    public PermOnSubjWClassifDTO(@NotNull final SubjectDTO createdBy, @NotNull final ContextKeyDTO context) {
        super(createdBy, context);
    }

}
