/**
 * Websocket configuration
 */
var webSocket = new WebSocket("ws://localhost:8080/RPGWebSocketServer/websocketendpoint");
var echoText = document.getElementById("echoText");
echoText.value = "";
var message = document.getElementById("message");

// Assign the functions to the webSocket we created in Java
webSocket.onopen = (message) => { wsOpen(message);};
webSocket.onmessage = (message) => { wsGetMessage(message);};
webSocket.onclose = (message) => { wsClose(message);};
webSocket.onerror = (message) => { wsError(message);};

function wsOpen(message){
    echoText.value += "Connected ... \n";
}

function wsSendMessage(message){
    webSocket.send(message);
    echoText.value += "Message sent to the server : " + message + "\n";
}

function wsCloseConnection(){
    webSocket.close();
}

function wsGetMessage(message){
    echoText.value += "Message received from the server : " + message.data + "\n";
}

function wsClose(message){
    echoText.value += "Disconnect ... \n";
}

function wsError(message){
    echoText.value += "Error ... \n";
}