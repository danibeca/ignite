package com.sprhib.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sprhib.dao.MemberDAO;
import com.sprhib.model.Member;
import com.sprhib.util.DataCreator;

public class MemberServiceImplTest {

	@InjectMocks
	private MemberServiceImpl memberService;

	@Mock
	private MemberDAO memberDAO;

	@Before
	public void initialize() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetMember() {
		Integer id = 1;
		String name = "Member #1";

		when(memberDAO.getMember(id)).thenReturn(DataCreator.createMember(id, name));

		Member result = memberService.getMember(id);

		assertEquals(id, result.getId());
		assertEquals(name, result.getName());

	}

	@Test
	public void testGetMembers() {
		List<Member> members = new ArrayList<Member>();
		members.add(DataCreator.createMember(1, "Member 1"));
		members.add(DataCreator.createMember(2, "Member 2"));

		when(memberDAO.getMembers()).thenReturn(members);

		List<Member> result = memberService.getMembers();
		assertTrue(result.size() == 2);
	}
}
