function read(urlString, elementName)
{
	var xmlHttp = new XMLHttpRequest();
	if(typeof spanName !== 'undefined')
	{
		xmlHttp.onreadystatechange = function()
		{ 
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200)
			{
				document.getElementById(elementName).innerHTML = xmlHttp.responseText;
			}
		}
	}
	xmlHttp.open("GET", "C:\Users\Jamie\Documents\NetBeansProjects\JTK\Framework\JTK\console\data.txt", true);
	xmlHttp.send(null);
}