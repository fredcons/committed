package com.fullsix.committed.web

import com.fullsix.committed.core.model.SvnSearch
import com.fullsix.committed.core.model.SvnSearchResult
import com.fullsix.committed.core.model.SvnSearchFormData

class SearchController {

	def svnSearcher;

	def index = {
		def svnSearch = new SvnSearch()
		
		svnSearch.setModifiedBefore(new Date());
		def c = new GregorianCalendar(2000, Calendar.JANUARY, 1)
		svnSearch.setModifiedAfter(c.time);
		
		
		svnSearch.setSortDirection(SvnSearch.Sort.DESC);
    	def svnSearchFormData = svnSearcher.initSearchData()
    	
    	def svnSearchResult = svnSearcher.findLast()

    	render(view: '/search/index', model: [svnSearchFormData: svnSearchFormData, svnSearchResult : svnSearchResult, svnSearch : svnSearch, firstVisit : true])    
    }
    
    def search = {
    
    	def svnSearch = new SvnSearch()
    	bindData(svnSearch, params, ['action'])
    	def svnSearchFormData = svnSearcher.initSearchData()
    	
    	def svnSearchResult = null
    	if (svnSearch.go) {
    		svnSearchResult = svnSearcher.search(svnSearch);
    	}    	
    	
    	render(view: '/search/index', model: [svnSearchFormData: svnSearchFormData, svnSearchResult : svnSearchResult, svnSearch : svnSearch])    
    }
}
