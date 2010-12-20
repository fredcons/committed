<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <g:javascript library="less-1.0.40.min" />        
        <link rel="stylesheet" href="${resource(dir:'css',file:'grid-1140.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'jquery.snippet.min.css')}" />
        <link href='http://fonts.googleapis.com/css?family=Arvo' rel='stylesheet' type='text/css'>       
        <link href='http://fonts.googleapis.com/css?family=Inconsolata' rel='stylesheet' type='text/css'>       
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="jquery-1.4.4.min" />        
        <g:javascript library="jquery.snippet.min" />        
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="grailsLogo"><a href="http://grails.org"><img src="${resource(dir:'images',file:'grails_logo.png')}" alt="Grails" border="0" /></a></div>
        <g:layoutBody />
    </body>
</html>