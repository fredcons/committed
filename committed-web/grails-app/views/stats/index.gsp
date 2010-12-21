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
	        			
	        			</div>
	        		</td>
	        	</tr>
	        </table>
	        
		</div>
    </body>
</html>