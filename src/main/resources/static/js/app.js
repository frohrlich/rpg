var stompClient = null;

function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	}
	else {
		$("#conversation").hide();
	}
	$("#greetings").html("");
}

function connect() {
	var socket = new SockJS('/gs-guide-websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		stompClient.subscribe('/client/vue', function(greeting) {
			showGreeting(greeting.body);
			update(JSON.parse(greeting.body));
		});
	});
}

function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendName() {
	stompClient.send("/game/hello", {}, JSON.stringify({ 'name': $("#name").val() }));
}

// __________CUSTOM METHODS__________

// Tell server game is started
function startGameServer() {
	stompClient.send("/game/initialize", {}, JSON.stringify({ 'message': 'startGame' }));
}

// Send click info to server
function sendClick(clickInfo) {
	stompClient.send("/game/update", {}, JSON.stringify({ 'click': clickInfo }));
}

function showGreeting(message) {
	$("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#connect").click(function() { connect(); });
	$("#disconnect").click(function() { disconnect(); });
	$("#send").click(function() { sendName(); });
});