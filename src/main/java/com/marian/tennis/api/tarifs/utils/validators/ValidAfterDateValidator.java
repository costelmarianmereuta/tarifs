
package com.marian.tennis.api.tarifs.utils.validators;

import com.marian.tennis.api.tarifs.entity.TarifsEntity;
import com.marian.tennis.api.tarifs.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValidAfterDateValidator implements ConstraintValidator<ValidAfterDate, TarifsEntity> {


    @Override
    public boolean isValid(TarifsEntity tarifsEntity, ConstraintValidatorContext context) {
        if (tarifsEntity == null || tarifsEntity.getStartDate().isBefore(tarifsEntity.getEndDate()) || tarifsEntity.getStartDate().isEqual(tarifsEntity.getEndDate())) {
            return true;
        }
        throw new ResponseStatusException(HttpStatus.valueOf(422), Constants.INVALID_DATE);

    }
}
