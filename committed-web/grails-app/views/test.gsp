<html>
    <head>
        <title>Welcome to Grails</title>
        <meta name="layout" content="grid" />
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
            margin-left:280px;
            margin-right:20px;
        }
        
        body { background-color: #fff; }
	.container { background-color:#333; }
	.container div p { background-color:#fff; }
	div p { border:1px solid #333; padding:10px; }
	h2 { color:#fff; }
        
        </style>
    </head>
    <body>
    
       <div class="container container_12">
           
       <div id="header" class="grid_12">
       	   <p>header</p>       
       </div> 
       
       <div id="body">
       		<div class='grid_3'>
               <p>nav</p>
            </div>
            <div class='grid_9'>
               <p>body</p>
            </div>
       </div>
    
       </div>
        
    </body>
</html>
