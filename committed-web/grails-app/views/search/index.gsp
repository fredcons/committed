<html>
    <head>
        <title>Svn Digger</title>
		<meta name="layout" content="main" />
		<style type="text/css" media="screen">			
			
		</style>
		<script>
			function setPageNumber(i) {
				document.searchForm.pageNumber.value = i;
				document.searchForm.submit();
			}
		</script>
    </head>
    <body>
		<div id="pageBody">
	        <div class="title">Svn Digger</div>
	        <table class="main">
	        	<tr>
	        		<td class="search bordered">
	        			<div class="subtitle">Recherche</div>
	        			<g:form name="searchForm" controller="search" action="search" method="get">
	                    <g:hiddenField name="go" value="true" />
	                    <g:hiddenField name="pageNumber" value="1" /> 
	        	        <table>
	        	    		<tr>
                        		<td class="formlabel">Repository : </td>
                        		<td class="formfield"><g:select name="rootPath" from="${svnSearchFormData?.rootPaths}" value="${svnSearch?.rootPath}" noSelection="${['':'Tous']}" /> </td>
                    		</tr>	
                    		<tr>
                        		<td class="formlabel">Auteur : </td>
                        		<td class="formfield"><g:select name="author" from="${svnSearchFormData?.authors}" value="${svnSearch?.author}" noSelection="${['':'Tous']}"/> </td>
                    		</tr>           	
	        				<tr>
	        					<td class="formlabel">Commentaire : </td>
	        					<td class="formfield"><g:textField name="text" class="largefield" placeholder="Ex : RENAULT-2000, c2g" value="${svnSearch?.text}" /> </td>
	        				</tr>
	        				<tr>
	        					<td class="formlabel">Chemin de fichier : </td>
	        					<td class="formfield"><g:textField name="filePath" class="largefield" placeholder="Ex : configurator, pom.xml" value="${svnSearch?.filePath}"/> </td>
	        				</tr>
	        				<tr>
	        					<td class="formlabel">Entre le : </td>
	        					<td class="formfield"><g:datePicker name="modifiedAfter" value="${svnSearch?.modifiedAfter}" precision="day" years="${2015..2000}" noSelection="['':'Choisir...']"/></td>
	        				</tr>	
	        				<tr>
	        					<td class="formlabel">Et le : </td>
	        					<td class="formfield"><g:datePicker name="modifiedBefore" value="${svnSearch?.modifiedBefore}" precision="day" years="${2015..2000}" noSelection="['':'Choisir...']"/></td>
	        				</tr>	        		
	        				<tr>
	        					<td class="formlabel"></td>
	        					<td class="formfield"><input type="submit" value="Chercher"</td>
	        				</tr>
	        			</table>	        
	       				</g:form>
	        		</td>
	        		<td class="results bordered">
	        		    <div class="subtitle">Résultats</div>
	        			<g:if test="${svnSearchResult}">
	        	
	        	            <div class="metadata">        					
	        					<g:if test="${firstVisit}">
	        						Les derniers commits : <br/><br/>
	        					</g:if>
	        					<g:else>
	        						${svnSearchResult.totalCommits} commits trouvés en ${svnSearchResult.queryTime} ms<br/><br/>	   
	        					</g:else>
	        		        </div>	
	        		        
	        		        <div class="resultlist">   				
	        	
	        					<g:each status="i" var="commit" in="${svnSearchResult.commits}">
   									<div class="commitdescription">
   										<b>${commit.revision}</b> le ${commit.formattedDate} par ${commit.author} sur ${commit.repositoryPath} 
   									</div>		
   									<div class="filepath">${commit.comment}</div>
   									<g:each status="j" var="commitItem" in="${commit.commitItems}">
   										<div class="filepath">${commitItem.type} ${commitItem.path}</div>
   									</g:each><br/>															
								</g:each>
							
							</div>
	        	
	        	            <g:if test="${!firstVisit}"> 
	        					<div class="pagination">
	        						<g:if test="${svnSearchResult.totalPages > 0}">
	        					    	<g:set var="paginationEllipseBefore" value="${false}" />
	        					    	<g:set var="paginationEllipseAfter" value="${false}" />
	        							<g:each var="i" in="${(1..svnSearchResult.totalPages)}">
											<g:if test="${i == svnSearch.pageNumber}">${i}&nbsp;</g:if>
											<g:elseif test="${(i > svnSearch.pageNumber && i < svnSearch.pageNumber + 5) || (i < svnSearch.pageNumber && i > svnSearch.pageNumber - 5)}"><a href="javascript:setPageNumber(${i})">${i}</a>&nbsp;</g:elseif>
											<g:elseif test="${i == 1 || i == svnSearchResult.totalPages}"><a href="javascript:setPageNumber(${i})">${i}</a>&nbsp;</g:elseif>
											<g:elseif test="${i > 1 && i < svnSearch.pageNumber && paginationEllipseBefore == false}">
											  ...
											  <g:set var="paginationEllipseBefore" value="${true}" />
											</g:elseif>
											<g:elseif test="${i > svnSearch.pageNumber && i < svnSearchResult.totalPages && paginationEllipseAfter == false}">
                                     	     ...
                                     	     <g:set var="paginationEllipseAfter" value="${true}" />
                                     	   </g:elseif>
										</g:each>
									</g:if>
								</div>
							</g:if>
	       				 </g:if>
	        		</td>
	        	</tr>
	        </table>
	        
		</div>
    </body>
</html>