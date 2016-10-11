package com.sprhib.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sprhib.model.Organization;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addOrganization(Organization organization) {
		getCurrentSession().save(organization);
	}

	public void updateOrganization(Organization organization) {
		Organization organizationToUpdate = getOrganization(organization.getId());
		organizationToUpdate.setName(organization.getName());
		getCurrentSession().update(organizationToUpdate);
	}

	public Organization getOrganization(int id) {
		return (Organization) getCurrentSession().get(Organization.class, id);
	}

	public void deleteOrganization(int id) {
		Organization organization = getOrganization(id);
		if (organization != null) {
			getCurrentSession().delete(organization);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Organization> getOrganizations() {
		return getCurrentSession().createQuery("from Organization").list();
	}

	public boolean hasTeams(int id) {
		boolean result = false;
		BigInteger teams = (BigInteger) getCurrentSession()
										.createSQLQuery("select count(1) from teams where organization_id=:organizationId")
										.setInteger("organizationId", id)
										.uniqueResult();
		if (teams.intValue() > 0) {
			result = true;
		}
		return result;
	}

}
