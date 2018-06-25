package com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.gm.jcant.JDate;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.Statistics.JStatistics;

@Service
public class JLinkClickServiceImpl implements JLinkClickService{

	@Autowired
    private JLinkClickRepository lcRep;

	@Override
	public void SaveLinkClick(JLink link, HttpServletRequest request) {
		if ((link == null)||(request == null)) {
			//throw new exception...
		}
		JLinkClick lc = new JLinkClick(link, new Date(), request.getRemoteAddr());
		lcRep.save(lc);
	}

	@Override
	public JStatistics getStatsForLink(JLink link) {
		Date day = JDate.setTime(new Date(), "00:00:00");
		int allCnt = lcRep.countLinkToDate(link, null);
		int dayCnt = lcRep.countLinkToDate(link, day);
		int weekCnt = lcRep.countLinkToDate(link, JDate.incDay(day, -7));
		int monthCnt = lcRep.countLinkToDate(link, JDate.incMonth(day, -1));
		int yearCnt = lcRep.countLinkToDate(link, JDate.incYear(day, -1));
		JStatistics stats = new JStatistics(allCnt, dayCnt, weekCnt, monthCnt, yearCnt, JDate.getDifferenceDays(new Date(), link.getFinishDate()));
		return stats;
	}
}
