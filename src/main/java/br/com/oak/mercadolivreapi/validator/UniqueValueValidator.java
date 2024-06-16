package br.com.oak.mercadolivreapi.validator;

import br.com.oak.mercadolivreapi.annotation.UniqueValue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import org.springframework.util.Assert;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

  private String domainAttribute;

  private Class<?> clazz;

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void initialize(UniqueValue params) {
    domainAttribute = params.fieldName();
    clazz = params.domainClass();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    Query query = entityManager.createQuery(
        "select 1 from " + clazz.getName() + " where "
            + domainAttribute + "=:value");
    query.setParameter("value", value);

    List<?> list = query.getResultList();

    Assert.state(list.size() <= 1,
        "Encontrado mais de um registro de um " + clazz + " com o atributo " + domainAttribute
            + " = " + value);

    return list.isEmpty();
  }
}
