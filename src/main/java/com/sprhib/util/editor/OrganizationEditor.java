package com.sprhib.util.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sprhib.model.Organization;
import com.sprhib.service.OrganizationService;

@Component
public class OrganizationEditor extends PropertyEditorSupport {

	@Autowired
	private OrganizationService organizationService;

	@Override
	public void setAsText(String text) {
		Organization organization = organizationService.getOrganization(Integer.valueOf(text));
		this.setValue(organization);
	}

}
