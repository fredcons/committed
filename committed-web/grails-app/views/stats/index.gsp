<html>
    <head>
        <title>Svn Digger</title>
		<meta name="layout" content="main" />
		<style type="text/css" media="screen">			
			
		</style>
		<script>
			function setPageNumber(i) {
				document.statsForm.pageNumber.value = i;
				document.statsForm.submit();
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
	        			<g:form name="statsForm" controller="stats" action="search" method="get">
	                    <g:hiddenField name="go" value="true" />
	                    <g:hiddenField name="pageNumber" value="1" /> 
	        	        <table>
	        	    		<tr>
                        		<td class="formlabel">Repository : </td>
                        		<td class="formfield"><g:select name="rootPath" from="${svnSearchFormData?.rootPaths}" value="${statsSearch?.rootPath}" noSelection="${['':'Tous']}" /> </td>
                    		</tr>	
                    		<tr>
                        		<td class="formlabel">Auteur : </td>
                        		<td class="formfield"><g:select name="author" from="${svnSearchFormData?.authors}" value="${statsSearch?.author}" noSelection="${['':'Tous']}"/> </td>
                    		</tr>           	
	        				<tr>
	        					<td class="formlabel">Entre le : </td>
	        					<td class="formfield"><g:datePicker name="modifiedAfter" value="${statsSearch?.modifiedAfter}" precision="day" years="${2015..2000}" noSelection="['':'Choisir...']"/></td>
	        				</tr>	
	        				<tr>
	        					<td class="formlabel">Et le : </td>
	        					<td class="formfield"><g:datePicker name="modifiedBefore" value="${statsSearch?.modifiedBefore}" precision="day" years="${2015..2000}" noSelection="['':'Choisir...']"/></td>
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
	        		    <div>
	        				<g:if test="${statsSearchResult}">
	        	
	        	            	<div class="metadata">        					
	        						Statistiques calculées en ${statsSearchResult.queryTime} ms<br/><br/>	   
	        					</div>	
	        		        
	        		            <div class="resultlist">   				
	        					
	        					   Par jour  : <br/>
	        					   <g:each status="i" var="aggregation" in="${statsSearchResult.sortedDateAggregations}">
	        					       ${aggregation.key} : ${aggregation.count}&nbsp;
	        						   <g:if test="${aggregation.author}">${aggregation.author}&nbsp;</g:if>
	        						   <g:if test="${aggregation.repositoryPath}">${aggregation.repositoryPath}&nbsp;</g:if>	
	        						   <br/>        						
	        					   </g:each>
	        					
	        					   Par heure : <br/>
	        					   <g:each status="i" var="aggregation" in="${statsSearchResult.sortedHourAggregations}">
	        					       ${aggregation.key} : ${aggregation.count}&nbsp;
	        						   <g:if test="${aggregation.author}">${aggregation.author}&nbsp;</g:if>
	        						   <g:if test="${aggregation.repositoryPath}">${aggregation.repositoryPath}&nbsp;</g:if>	 
	        						   <br/>       						
	        					   </g:each>
	        					
	        				   </div>	
	        				
	        				</g:if>
	        				
	        			</div>
	        		</td>
	        	</tr>
	        </table>
	        
		</div>
    </body>
</html>