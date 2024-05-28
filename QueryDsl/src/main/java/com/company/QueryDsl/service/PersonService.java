package com.company.QueryDsl.service;

import com.company.QueryDsl.entity.Person;
import com.company.QueryDsl.entity.QPerson;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Person> getUsers() {
        return jpaQueryFactory.selectFrom(QPerson.person).fetch();
    }

    @Transactional
    public Person createUser(Person person) {
        QPerson qPerson = QPerson.person;

        jpaQueryFactory.insert(qPerson)
                .columns(qPerson.name, qPerson.surname)
                .values(person.getName(), person.getSurname())
                .execute();

        return person;
    }

    @Transactional
    public Person updateUser(Person person, Integer id) {
        QPerson qPerson = QPerson.person;

        Person existPerson = jpaQueryFactory.selectFrom(qPerson).where(qPerson.id.eq(id)).fetchOne();

        if(existPerson == null) {
            throw new IllegalArgumentException("Person not found with id " + id);
        }

        if(person.getName() != null) {
            existPerson.setName(person.getName());
        }

        if(person.getSurname() != null) {
            existPerson.setSurname(person.getSurname());
        }

        jpaQueryFactory.update(qPerson)
                .set(qPerson.name, existPerson.getName())
                .set(qPerson.surname, existPerson.getSurname())
                .where(qPerson.id.eq(id))
                .execute();

        return existPerson;
    }

    @Transactional
    public Long deletePerson(Integer id) {
        QPerson qPerson = QPerson.person;

        return jpaQueryFactory.delete(qPerson).where(qPerson.id.eq(id)).execute();
    }
}
