package com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.develop.jcant.JDate;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkException;
import com.gmail.gm.jcant.JLinkManagement.Statistics.JStatistics;

@Service
public class JLinkClickServiceImpl implements JLinkClickService{

	@Autowired
    private JLinkClickRepository lcRep;

	@Override
	@Transactional
	public void SaveLinkClick(JLink link, HttpServletRequest request) throws JLinkException {
		if ((link == null)||(request == null)) {
			throw new JLinkException("Can't save LinkClick info about null Link!");
		}
		JLinkClick lc = new JLinkClick(link, new Date(), request.getRemoteAddr());
		lcRep.save(lc);
	}

	@Override
	@Transactional(readOnly = true)
	public JStatistics getStatsForLink(JLink link) {
		Date day = JDate.setTime(new Date(), "00:00:00");
		int allCnt = lcRep.countLink(link);
		int dayCnt = lcRep.countLinkToDate(link, day);
		int weekCnt = lcRep.countLinkToDate(link, JDate.incDay(day, -7));
		int monthCnt = lcRep.countLinkToDate(link, JDate.incMonth(day, -1));
		int yearCnt = lcRep.countLinkToDate(link, JDate.incYear(day, -1));
		JStatistics stats = new JStatistics(allCnt, dayCnt, weekCnt, monthCnt, yearCnt, JDate.getDifferenceDays(new Date(), link.getFinishDate()));
		return stats;
	}
}
