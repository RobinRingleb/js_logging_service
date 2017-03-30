<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JSLogging Service - Table Data View</title>
    <style> 
    	table, th, td {border: 1px solid black; border-collapse: collapse; text-align: center;} 
    </style>
</head>
<body>
	<h2>Robins browser table</h2>
	<table style="width:50%">
	  <tr>
	    <th></th>
	    <th>Gesamt</th>
	    <th>start</th>
	    <th>suche</th>
	    <th>static</th>
	    <th>pop</th>
	    <th>pdp</th>
	  </tr>
	  <tr>
		<td style="text-align: left"><b>Chrome</b></td>
	    <td>${countChromeAll}</td>
	    <td>${countChromeStart}</td>
	    <td>${countChromeSuche}</td>
	    <td>${countChromeStatic}</td>
	    <td>${countChromePop}</td>
	    <td>${countChromePdp}</td>
	  </tr>
	  <tr>
	  	<td style="text-align: left"><b>Firefox</b></td>
	    <td>${countFirefoxAll}</td>
	    <td>${countFirefoxStart}</td>
	    <td>${countFirefoxSuche}</td>
	    <td>${countFirefoxStatic}</td>
	    <td>${countFirefoxPop}</td>
	    <td>${countFirefoxPdp}</td>
	  </tr>
	  <tr>
		<td style="text-align: left"><b>IE</b></td>
	    <td>${countIEAll}</td>
	    <td>${countIEStart}</td>
	    <td>${countIESuche}</td>
	    <td>${countIEStatic}</td>
	    <td>${countIEPop}</td>
	    <td>${countIEPdp}</td>
	  </tr>
	  <tr>
		<td style="text-align: left"><b>unknown Browser</b></td>
	    <td>${countUnknownBrowserAll}</td>
	    <td>${countUnknownBrowserStart}</td>
	    <td>${countUnknownBrowserSuche}</td>
	    <td>${countUnknownBrowserStatic}</td>
	    <td>${countUnknownBrowserPop}</td>
	    <td>${countUnknownBrowserPdp}</td>
	  </tr>
	  
	</table>
</body>
</html>