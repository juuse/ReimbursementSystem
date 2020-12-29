/**
 * File that implements the functionality in the employee home page
 */
 
 //The variable used to convert the user ID on reimbursements into the user's name
 var mapForNames;

 //Does all the loading work for the page and attaches all listeners
 window.onload = function(){
   getUserName();
   getUserInfo();
   getNameIDMap();
   getUserReimbursements();

   document
        .getElementById('reimbSubmit')
		.addEventListener('click', getUserReimbursements);
	
	document
		.getElementById('createInp')
		.addEventListener('click', createReimbursement);
	
	document
		.getElementById('logout')
		.addEventListener('click', logout);
}

window.onunload = function(){
	invalidateSession();
}

//Returns the user to the login screen and removes history
function logout(){
	window.location.replace("http://localhost:9001/project1/");
}

function invalidateSession(){
	console.log("made it aaaaa  aaaaaaaaaaa");
	fetch('http://localhost:9001/project1/api/forwarding/invalidateSession');
}

//Sends an ajax request that gets the name of the User logged in for the top right element
function getUserName(){
    
   fetch('http://localhost:9001/project1/api/ajax/userInfo')
        .then(
            function(daResponse){
                const convertedResponse = daResponse.json();
                return convertedResponse;
            }
        ).then(
            function(daSecondResponse){
                loginDOMManipulation(daSecondResponse);
            }
        )
   
}

//Gets all of the Users info for the account
function getUserInfo(){
    
	fetch('http://localhost:9001/project1/api/ajax/userInfo')
		 .then(
			 function(daResponse){
				 const convertedResponse = daResponse.json();
				 return convertedResponse;
			 }
		 ).then(
			 function(daSecondResponse){
				 userInfoTableDOMManip(daSecondResponse);
			 }
		 )
 }

 //Gets all reimbursements authored by the current yser logged in
function getUserReimbursements(){
	fetch('http://localhost:9001/project1/api/ajax/userReimbursements')
        .then(
            function(daResponse){
                const convertedResponse = daResponse.json();
                return convertedResponse;
            }
        ).then(
            function(daSecondResponse){
                tableDOMManipulation(daSecondResponse);
            }
        )
}

//Creates a reimbursement through a post request to the servlet 
function createReimbursement(){
	let amount = document.getElementById('amount1').value;
	let description = document.getElementById('descrip1').value;
	let typing = document.getElementById('typing').value;
			
	fetch(`http://localhost:9001/project1/api/ajax/newReimbursement?amount=${amount}&description=${description}&typing=${typing}`,
						{"method": "post"})
		 .then(
			 function(daResponse){
				 const convertedResponse = daResponse.json();
				 return convertedResponse;
			 }
		 ).then(
			 function(daSecondResponse){
				 creationDOM(daSecondResponse);
			 }
		 )

}

//Takes the string from the creation request and shows the user as an alert
function creationDOM(ourJSON){
	alert(ourJSON);
}

//Attaches the name of the user to the top right dropdown
function loginDOMManipulation(ourJSON){
	let newSelect = document.querySelector("#loginTitle");
    newSelect.textContent = `${ourJSON.firstName} ${ourJSON.lastName}`;
}

//populates the table for the user information on the page
function userInfoTableDOMManip(ourJSON){
		///////////////table time
	    
	 // ///////////CREATE ELEMENTS DYNAMICALLY//////////////
		// all creations
		let newTR = document.createElement("tr");
		let newTH = document.createElement("th");
        
        let newTD0 = document.createElement("td");
		let newTD1 = document.createElement("td");
		let newTD2 = document.createElement("td");
		let newTD3 = document.createElement("td");
		let newTD4 = document.createElement("td");
		
		// population creations
        newTH.setAttribute("scope", "row")
        let myText0 = document.createTextNode(ourJSON.userId);
        let myText1 = document.createTextNode(ourJSON.firstName+ " "+ourJSON.lastName);
		let myText2 = document.createTextNode(ourJSON.uUsername);
		let myText3 = document.createTextNode(ourJSON.uPassword);
		let myText4 = document.createTextNode(ourJSON.emailAddress);
		let myText5 = document.createTextNode(ourJSON.role);
		
		
		///all appendings
        newTH.appendChild(myText0);
        newTD0.appendChild(myText1);
		newTD1.appendChild(myText2);
		newTD2.appendChild(myText3);
		newTD3.appendChild(myText4);
		newTD4.appendChild(myText5);
		
        newTR.appendChild(newTH);
        newTR.appendChild(newTD0);
		newTR.appendChild(newTD1);
		newTR.appendChild(newTD2);
		newTR.appendChild(newTD3);
		newTR.appendChild(newTD4);

		let newSelectionTwo = document.querySelector("#infoTableBody");
        newSelectionTwo.appendChild(newTR);
}

//Used to populate the reimbursement table
function tableDOMManipulation(ourJSON){
	deleteTable();

	for (let i = 0; i < ourJSON.length; i++) {
		///////////////table time
	    
	 // ///////////CREATE ELEMENTS DYNAMICALLY//////////////
		// all creations
		let newTR = document.createElement("tr");
		let newTH = document.createElement("th");
		
		let newTD1 = document.createElement("td");
		let newTD2 = document.createElement("td");
		let newTD3 = document.createElement("td");
		let newTD4 = document.createElement("td");
		let newTD5 = document.createElement("td");
		let newTD6 = document.createElement("td");
		let newTD7 = document.createElement("td");
		
		// population creations
		newTH.setAttribute("scope", "row")
		let myText1 = document.createTextNode(ourJSON[i].reimbID);
		let myText2 = document.createTextNode(ourJSON[i].reimbAmount);
		let myText3 = document.createTextNode(ourJSON[i].reimbSubTime);
		let myText4 = document.createTextNode(ourJSON[i].reimbResolvTime);
		let myText5 = document.createTextNode(ourJSON[i].reimbDescription);
		let myText6;
		if(ourJSON[i].reimbResolver == 0){
			myText6 = document.createTextNode("None");
		}else{
			myText6 = document.createTextNode(mapForNames[ourJSON[i].reimbResolver]);
		}
		let myText7 = document.createTextNode(ourJSON[i].rStatus);
		let myText8 = document.createTextNode(ourJSON[i].rType);
		
		///all appendings
		newTH.appendChild(myText1);
		newTD1.appendChild(myText2);
		newTD2.appendChild(myText3);
		newTD3.appendChild(myText4);
		newTD4.appendChild(myText5);
		newTD5.appendChild(myText6);
		newTD6.appendChild(myText7);
		newTD7.appendChild(myText8);
		
		newTR.appendChild(newTH);
		newTR.appendChild(newTD1);
		newTR.appendChild(newTD2);
		newTR.appendChild(newTD3);
		newTR.appendChild(newTD4);
		newTR.appendChild(newTD5);
		newTR.appendChild(newTD6);
		newTR.appendChild(newTD7);
		let newSelectionTwo = document.querySelector("#reimbTableBody");
		newSelectionTwo.appendChild(newTR);
	}
}

//Clears the reimbursement table before repopulating to stop repeat values
function deleteTable(){
    var Parent = document.getElementById("reimbTableBody");
    while(Parent.hasChildNodes())
    {
        Parent.removeChild(Parent.firstChild);
    }
}

//Gets an object with the ids of the users mapped to their names for display purposes
function getNameIDMap(){

    fetch('http://localhost:9001/project1/api/ajax/mapNamesToID')
    .then(
        function(daResponse){
            const convertedResponse = daResponse.json();
            return convertedResponse;
        }
    ).then(
        function(daSecondResponse){
            mapForNames = daSecondResponse;
        }
    )                         
}