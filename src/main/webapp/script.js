/**
 * Canvas game script
 */

let canvasWidth = 600;
let canvasHeight = 400;
let textfontSize = 20; // font size for main text box and options

let start, previousTimeStamp, lastFrameTimeStamp;

let previousVue = null;

let interfacePosY = 300; // y position of interface

let charStartPosX = 130; // starting x position for character
let charStartPosY = 130; // starting y position for character
let pnjStartPosX = canvasWidth - 140; // starting x position for pnj
let pnjStartPosY = 130; // starting y position for pnj
let limitMovementX = 10; // moving space for character animation
let directionChar = 1,
	directionPnj = -1; // starting moving direction 1 right, -1 left
let charactersAnimationFrameRate = 75;

function startGame() {

	myCharacter = new component(
		0,
		0,
		"",
		charStartPosX,
		charStartPosY,
		"image"
	);

	myPnj = new component(
		0,
		0,
		"",
		pnjStartPosX,
		pnjStartPosY,
		"image"
	);

	myMainTextBox = new component(
		360,
		96,
		"black",
		5,
		interfacePosY,
		"textBox",
		""
	);

	myOption1 = new component(
		225,
		30,
		"black",
		370,
		interfacePosY,
		"textBox",
		""
	);
	myOption2 = new component(
		225,
		30,
		"black",
		370,
		interfacePosY + 33,
		"textBox",
		""
	);
	myOption3 = new component(
		225,
		30,
		"black",
		370,
		interfacePosY + 66,
		"textBox",
		""
	);
	myBackground = new component(
		canvasWidth,
		canvasHeight,
		"",
		0,
		0,
		"background"
	);

	myGameArea.start();
}

let myGameArea = {
	canvas: document.createElement("canvas"),
	start: function() {
		this.canvas.width = canvasWidth;
		this.canvas.height = canvasHeight;
		this.context = this.canvas.getContext("2d");
		document.body.insertBefore(this.canvas, document.body.childNodes[0]);
		window.addEventListener('click', function(e) {
			myGameArea.x = e.pageX;
			myGameArea.y = e.pageY;
		})
	},
	clear: function() {
		this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);
	},
};

// any component of the game (sprite, dialog box...)
function component(width, height, colorImage, x, y, type, text = null) {
	this.type = type;

	if (type == "image" || type == "background") {
		this.image = new Image();
		this.image.src = colorImage;
		if (type == "image") {
			this.image.addEventListener("load", () => {
				this.width = this.image.naturalWidth / 2;
				this.height = this.image.naturalHeight / 2;
			})
		}
	}
	if (type != "image") {
		this.width = width;
		this.height = height;
	}

	this.color = colorImage;
	this.speedX = 0;
	this.speedY = 0;
	this.x = x;
	this.y = y;
	this.text = text;
	this.update = function() {
		ctx = myGameArea.context;
		if (this.type == "image" || this.type == "background") {
			ctx.drawImage(this.image, this.x, this.y, this.width, this.height);
		} else if (this.type == "text") {
			ctx.font = this.width + " " + this.height;
			ctx.fillStyle = colorImage;
			ctx.fillText(this.text, this.x, this.y);
		} else if (this.type == "textBox") {
			drawTextBox(ctx, this.text, this.x, this.y, this.width, this.height, this.color);
		} else {
			ctx.fillStyle = colorImage;
			ctx.fillRect(this.x, this.y, this.width, this.height);
		}
	};
	this.clicked = function() {
		var myleft = this.x;
		var myright = this.x + (this.width);
		var mytop = this.y;
		var mybottom = this.y + (this.height);
		var clicked = true;
		if ((mybottom < myGameArea.y) || (mytop > myGameArea.y) || (myright < myGameArea.x) || (myleft > myGameArea.x)) {
			clicked = false;
		}
		return clicked;
	};
	this.newPos = function() {
		this.x += this.speedX;
		this.y += this.speedY;
	};
}

// __________MAIN LOOP__________
(() => {
	function updateGameArea(timestamp) {
		if (start === undefined) {
			start = timestamp;
			lastFrameTimeStamp = timestamp;
		}
		//const elapsed = timestamp - start;

		myGameArea.stopMain = window.requestAnimationFrame(updateGameArea);

		myGameArea.clear();

		myCharacter.speedX = 0;
		myCharacter.speedY = 0;
		myPnj.speedX = 0;
		myPnj.speedY = 0;

		// CHARACTERS ANIMATION
		if (timestamp - lastFrameTimeStamp >= charactersAnimationFrameRate) {
			if (
				myCharacter.x > charStartPosX + limitMovementX ||
				myCharacter.x < charStartPosX
			) {
				directionChar *= -1; // change direction if out of bound
			}

			// moving animation pnj
			if (myPnj.x < pnjStartPosX - limitMovementX || myPnj.x > pnjStartPosX) {
				directionPnj *= -1; // change direction if out of bound
			}

			myCharacter.speedX += directionChar;
			myPnj.speedX += directionPnj;
			lastFrameTimeStamp = timestamp; // time of last animation frame
		}

		// checks if user clicked somewhere on game area
		if (myGameArea.x && myGameArea.y) {
			if (myOption1.clicked()) {
				wsSendMessage("option1"); // sends "option clicked" message to server
			}
			else if (myOption2.clicked()) {
				wsSendMessage("option2");
			}
			else if (myOption3.clicked()) {
				wsSendMessage("option3");
			}
			myGameArea.x = 0; // reset click position
			myGameArea.y = 0;
		}

		myBackground.update();
		myMainTextBox.update();
		myOption1.update();
		myOption2.update();
		myOption3.update();
		myCharacter.newPos();
		myCharacter.update();
		myPnj.newPos();
		myPnj.update();
	}

	// ______Game startup______

	window.addEventListener("DOMContentLoaded", () => {
		// checks if webSocket connexion is open
		webSocket.addEventListener("open", (event) => {
			startGame(); // initialize game client-side
			updateGameArea(); // starts loop
			wsSendMessage("startGame"); // tells server to start game
		})
	})

})();

// this event listener manages incoming messages from server
webSocket.addEventListener("message", (event) => {
	const vueInfo = JSON.parse(event.data); // parse json response from server

	// if player present on current view and different from previous view
	if (previousVue === null || (previousVue.joueur != vueInfo.joueur && Object.hasOwn(vueInfo, 'joueur'))) {
		myCharacter = new component(
			0,
			0,
			vueInfo.joueur,
			myCharacter.x,
			myCharacter.y,
			"image"
		);
	}

	// if pnj present on current view and different from previous view
	if (previousVue === null || (previousVue.pnj != vueInfo.pnj && Object.hasOwn(vueInfo, 'pnj'))) {
		myPnj = new component(
			0,
			0,
			vueInfo.pnj,
			myPnj.x,
			myPnj.y,
			"image"
		);
	}

	// if background present on current view and different from previous view
	if (previousVue === null || (previousVue.background != vueInfo.background && Object.hasOwn(vueInfo, 'background'))) {
		myBackground = new component(
			canvasWidth,
			canvasHeight,
			vueInfo.background,
			0,
			0,
			"background"
		);
	}

	myMainTextBox.text = vueInfo.texte;

	myOption1.text = vueInfo.option1;
	myOption2.text = vueInfo.option2;
	myOption3.text = vueInfo.option3;

	previousVue = vueInfo;
})

// draw a simple textbox
function drawTextBox(ctx, text, posX, posY, tailleX, tailleY, color) {
	ctx.fillStyle = "white";
	rectArrondi(
		ctx,
		posX,
		posY,
		tailleX,
		tailleY,
		5,
		(fill = true)
	);
	ctx.fillStyle = "black";
	rectArrondi(ctx, posX, posY, tailleX, tailleY, 5);
	ctx.font = textfontSize + "px serif";
	let lines = getLines(ctx, text, tailleX);
	ctx.fillStyle = color; // text color
	for (let i = 0; i < lines.length; i++) {
		ctx.fillText(lines[i], posX + 5, posY + textfontSize * (i + 1));
	}
}

// Une fonction utilitaire pour tracer des rectangles avec des coins arrondis
function rectArrondi(ctx, x, y, largeur, hauteur, rayon, fill = false) {
	ctx.beginPath();
	ctx.moveTo(x, y + rayon);
	ctx.lineTo(x, y + hauteur - rayon);
	ctx.quadraticCurveTo(x, y + hauteur, x + rayon, y + hauteur);
	ctx.lineTo(x + largeur - rayon, y + hauteur);
	ctx.quadraticCurveTo(
		x + largeur,
		y + hauteur,
		x + largeur,
		y + hauteur - rayon
	);
	ctx.lineTo(x + largeur, y + rayon);
	ctx.quadraticCurveTo(x + largeur, y, x + largeur - rayon, y);
	ctx.lineTo(x + rayon, y);
	ctx.quadraticCurveTo(x, y, x, y + rayon);
	if (fill) {
		ctx.fill();
	} else {
		ctx.stroke();
	}
}

// line split to fit in text box
function getLines(ctx, text, maxWidth) {
	var words = text.split(" ");
	var lines = [];
	var currentLine = words[0];

	for (var i = 1; i < words.length; i++) {
		var word = words[i];
		var width = ctx.measureText(currentLine + " " + word).width;
		if (width < maxWidth) {
			currentLine += " " + word;
		} else {
			lines.push(currentLine);
			currentLine = word;
		}
	}
	lines.push(currentLine);
	return lines;
}

