package com.fullsix.committed.web

import com.fullsix.committed.core.model.SvnSearch
import com.fullsix.committed.core.model.SvnSearchResult
import com.fullsix.committed.core.model.SvnSearchFormData

class SearchController {

	def svnSearcher;

	def index = {
		def svnSearch = new SvnSearch()
		
		svnSearch.setSortDirection(SvnSearch.Sort.DESC);
    	def svnSearchFormData = svnSearcher.getFormData()

    	render(view: '/search/index', model: [svnSearchFormData: svnSearchFormData, svnSearch : svnSearch])    
    }
    
    def search = {
    
    	def svnSearch = new SvnSearch()
    	bindData(svnSearch, params, ['action'])
    	def svnSearchFormData = svnSearcher.getFormData()
    	
    	def svnSearchResult = null
    	if (svnSearch.go) {
    		svnSearchResult = svnSearcher.search(svnSearch);
    	}    	
    	
    	render(view: '/search/index', model: [svnSearchFormData: svnSearchFormData, svnSearchResult : svnSearchResult, svnSearch : svnSearch])    
    }
}
