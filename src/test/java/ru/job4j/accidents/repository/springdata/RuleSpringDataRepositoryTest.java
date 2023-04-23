package ru.job4j.accidents.repository.springdata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.job4j.accidents.model.Rule;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * RuleSpringDataRepository TEST
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 23.04.2023
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest()
class RuleSpringDataRepositoryTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private RuleSpringDataRepository rulesJPA;

    @BeforeEach
    public void initTest() {
        entityManager.createQuery("delete from Accident").executeUpdate();
        entityManager.createQuery("delete from Rule").executeUpdate();
    }

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(entityManager).isNotNull();
        assertThat(rulesJPA).isNotNull();
    }

    @Test
    public void whenFindAllRulesThenReturnCollections() {
        var rule1 = new Rule(0, "rule1");
        var rule2 = new Rule(0, "rule2");
        entityManager.persist(rule1);
        entityManager.persist(rule2);

        var expectedRules = List.of(rule1, rule2);
        var actualRules = rulesJPA.findAll();

        assertThat(actualRules).isEqualTo(expectedRules);
    }

    @Test
    public void whenFindByIdRuleThenReturnRuleOptional() {
        var rule = new Rule(0, "rule1");
        entityManager.persist(rule);

        var expectedRule = Optional.of(rule);
        var actualRule = rulesJPA.findById(rule.getId());

        assertThat(actualRule).isEqualTo(expectedRule);
    }
}