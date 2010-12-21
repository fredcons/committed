package com.fullsix.committed.web

import com.fullsix.committed.core.model.SvnSearch
import com.fullsix.committed.core.model.SvnSearchResult
import com.fullsix.committed.core.model.SvnSearchFormData

class StatsController {

	def svnSearcher;

	def index = {
		
    	render(view: '/stats/index', model: [firstVisit : true])    
    }
    
    def search = {
      	
    	render(view: '/stats/index', model: [])    
    }
}
