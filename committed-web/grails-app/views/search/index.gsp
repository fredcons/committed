<html>
    <head>
        <title>Svn Digger</title>
		<meta name="layout" content="main" />
		<style type="text/css" media="screen">

			#nav {
				margin-top:20px;
				margin-left:30px;
				width:228px;
				float:left;

			}
			.homePagePanel * {
				margin:0px;
			}
			.homePagePanel .panelBody ul {
				list-style-type:none;
				margin-bottom:10px;
			}
			.homePagePanel .panelBody h1 {
				text-transform:uppercase;
				font-size:1.1em;
				margin-bottom:10px;
			}
			.homePagePanel .panelBody {
			    background: url(images/leftnav_midstretch.png) repeat-y top;
				margin:0px;
				padding:15px;
			}
			.homePagePanel .panelBtm {
			    background: url(images/leftnav_btm.png) no-repeat top;
				height:20px;
				margin:0px;
			}

			.homePagePanel .panelTop {
			    background: url(images/leftnav_top.png) no-repeat top;
				height:11px;
				margin:0px;
			}
			h2 {
				margin-top:15px;
				margin-bottom:15px;
				font-size:1.2em;
			}
			#pageBody {
				margin-left:150px;
				margin-right:150px;
			}
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
	        <h1>Svn Digger</h1>
	        
	        <g:form name="searchForm" controller="search" action="search" method="get">
	            <g:hiddenField name="go" value="true" />
	            <g:hiddenField name="pageNumber" value="1" /> 
	        	<table>
	        		<tr>
	        			<td>Texte libre : </td>
	        			<td><g:textField name="text" value="${svnSearch?.text}" /> </td>
	        		</tr>
	        		<tr>
	        			<td>Auteur : </td>
	        			<td><g:textField name="author" value="${svnSearch?.author}" /></td>
	        		</tr>
	        		<tr>
	        			<td>Dernière modification : </td>
	        			<td>
	        			Entre le <g:datePicker name="lastModifiedAfter" value="${svnSearch?.lastModifiedAfter}" precision="day" years="${2015..2000}" noSelection="['':'Choisir...']"/> 
	        			et le <g:datePicker name="lastModifiedBefore" value="${svnSearch?.lastModifiedBefore}" precision="day" years="${2015..2000}" noSelection="['':'Choisir...']"/></td>
	        		</tr>	        		
	        		<tr>
	        			<td></td>
	        			<td><input type="submit" value="Chercher"</td>
	        		</tr>
	        	</table>	        
	        </g:form>
	        
	        <g:if test="${svnSearchResult}">
	        	        
	        	Temps de recherche : ${svnSearchResult.queryTime} ms <br/>
	        	Nombre total de docs : ${svnSearchResult.totalCommits} <br/><br/>	    
	        		        	
	        	<g:each status="i" var="commit" in="${svnSearchResult.commits}">
   						<b>${commit.revision}</b> : ${commit.comment}<br/>  			
   						Le ${commit.formattedDate} par ${commit.author}<br/> <br/>   								
				</g:each>
	        	
	        	<p style="text-align:center">
	        		<g:if test="${svnSearchResult.totalPages > 0}">
	        			<g:each var="i" in="${(1..svnSearchResult.totalPages)}">
							<g:if test="${i == svnSearch.pageNumber}">${i}</g:if>
							<g:else><a href="javascript:setPageNumber(${i})">${i}</a></g:else>
							&nbsp;
						</g:each>
					</g:if>
				</p>
				<br/><br/>
	        </g:if>
	        
		</div>
    </body>
</html>