package com.AlumnosOB.Alumnos.open.bootcamp.Spec;

import com.AlumnosOB.Alumnos.open.bootcamp.entity.Remote;
import com.AlumnosOB.Alumnos.open.bootcamp.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class UserSpec {
    EntityManager em;
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<User> query = builder.createQuery(User.class);
    Root<User> root = query.from(User.class);

    public static Specification<User> isCountry(String country) {
        return (root, query, cb) -> cb.equal(root.get("country"), country);
    }

    public static Specification<User> isCity(String city)  {
        return (root, query, cb) -> cb.equal(root.get("city"), city);
    }


    public static Specification<User> isTransfer(boolean cityTransfer)  {
        return (root, query, cb) -> cb.equal(root.get("cityTransfer"), cityTransfer);
    }

    public static Specification<User> isRemote(Remote remote)   {
        return (root, query, cb) -> cb.equal(root.get("remote"), remote);
    }
}

