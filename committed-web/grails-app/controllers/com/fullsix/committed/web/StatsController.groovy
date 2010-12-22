package com.fullsix.committed.web

import com.fullsix.committed.core.model.StatsSearch
import com.fullsix.committed.core.model.SvnSearchFormData

class StatsController {

	def svnSearcher;

	def index = {
	
	    def statsSearch = new StatsSearch()
		
		statsSearch.setModifiedBefore(new Date());
		def c = new GregorianCalendar(2000, Calendar.JANUARY, 1)
		statsSearch.setModifiedAfter(c.time);
		
		def svnSearchFormData = svnSearcher.initSearchData()
		
    	render(view: '/stats/index', model: [svnSearchFormData: svnSearchFormData, statsSearch : statsSearch, firstVisit : true])    
    }
    
    def search = {
    
        def statsSearch = new StatsSearch()
    	bindData(statsSearch, params, ['action'])
    	def svnSearchFormData = svnSearcher.initSearchData()
    	
    	def statsSearchResult = svnSearcher.findStats(statsSearch);	
      	
    	render(view: '/stats/index', model: [svnSearchFormData: svnSearchFormData, statsSearch : statsSearch, statsSearchResult : statsSearchResult])    
    }
}
