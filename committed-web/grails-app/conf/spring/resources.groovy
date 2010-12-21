// Place your Spring DSL code here
beans = {

  svnConfiguration(com.fullsix.committed.core.model.SvnConfiguration) {
  	svnUrl = "http://subversion.fullsix.com"
  	viewSvnUrl = "http://viewsvn.fullsix.com/viewvc"
  }
   
  
  servletContextExporter(org.springframework.web.context.support.ServletContextAttributeExporter) {
    attributes = ['svnConfiguration': ref("svnConfiguration")]
  }

}
