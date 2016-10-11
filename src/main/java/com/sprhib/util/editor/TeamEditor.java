package com.sprhib.util.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sprhib.model.Team;
import com.sprhib.service.TeamService;

@Component
public class TeamEditor extends PropertyEditorSupport {

	@Autowired
	private TeamService teamService;

	@Override
	public void setAsText(String text) {
		Team team = teamService.getTeam(Integer.valueOf(text));
		this.setValue(team);
	}

}
