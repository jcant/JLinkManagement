package com.gmail.gm.jcant.JLinkManagement.JPA.RootLink;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomainList;

@Component("JRootLinkDomainListImpl")
public class JRootLinkDomainListImpl implements JDomainList{
	
	@Autowired
    private JRootLinkRepository rlinkRepository;

	public JRootLinkDomainListImpl() {
	}

	@Override
	public List<String> getDomainList() {
		//Java8! :-))
//		List<String> list = rlinkRepository.findAll().stream().map(r -> r.getUrl()).collect(Collectors.toCollection(ArrayList::new));
//		return list;

		return rlinkRepository.findAll().stream().map(r -> r.getUrl()).collect(Collectors.toCollection(ArrayList::new));
	}
	
	
	
}
