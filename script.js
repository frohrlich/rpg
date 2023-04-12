let canvasWidth = 600;
let canvasHeight = 400;

let myCharacter;
let myBackground;

let start, previousTimeStamp, lastFrameTimeStamp;

let charStartPosX = 130; // starting x position for character
let charStartPosY = 150; // starting y position for character
let pnjStartPosX = canvasWidth - 200; // starting x position for pnj
let pnjStartPosY = 150; // starting y position for pnj
let limitMovementX = 10; // moving space for character animation
let directionChar = 1,
  directionPnj = -1; // starting moving direction 1 right, -1 left
let charactersAnimationFrameRate = 75;

function startGame() {
  myCharacter = new component(
    50,
    131,
    "img/epeisteM.png",
    charStartPosX,
    charStartPosY,
    "image"
  );
  myPnj = new component(
    161,
    131,
    "img/le_ours.png",
    pnjStartPosX,
    pnjStartPosY,
    "image"
  );
  myOption = new component(
    200,
    100,
    "black",
    10,
    290,
    "option",
    "Texte option"
  );
  myBackground = new component(
    canvasWidth,
    canvasHeight,
    "img/bg_foret.png",
    0,
    0,
    "image"
  );
  myGameArea.start();
}

let myGameArea = {
  canvas: document.createElement("canvas"),
  start: function () {
    this.canvas.width = canvasWidth;
    this.canvas.height = canvasHeight;
    this.context = this.canvas.getContext("2d");
    document.body.insertBefore(this.canvas, document.body.childNodes[0]);
  },
  clear: function () {
    this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);
  },
};

// any component of the game (sprite, dialog box...)
function component(width, height, color, x, y, type, text = null) {
  this.type = type;
  if (type == "image") {
    this.image = new Image();
    this.image.src = color;
  }
  this.width = width;
  this.height = height;
  this.speedX = 0;
  this.speedY = 0;
  this.x = x;
  this.y = y;
  this.text = text;
  this.update = function () {
    ctx = myGameArea.context;
    if (this.type == "image") {
      ctx.drawImage(this.image, this.x, this.y, this.width, this.height);
    } else if (this.type == "text") {
      ctx.font = this.width + " " + this.height;
      ctx.fillStyle = color;
      ctx.fillText(this.text, this.x, this.y);
    } else if (this.type == "option") {
      drawOption(ctx, this.text);
    } else {
      ctx.fillStyle = color;
      ctx.fillRect(this.x, this.y, this.width, this.height);
    }
  };
  this.newPos = function () {
    this.x += this.speedX;
    this.y += this.speedY;
  };
}

// __________MAIN LOOP_____________________________________
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

    //previousTimeStamp = timestamp;

    myBackground.newPos();
    myBackground.update();
    myOption.newPos();
    myOption.update();
    myCharacter.newPos();
    myCharacter.update();
    myPnj.newPos();
    myPnj.update();
  }

  startGame(); // initialize game
  updateGameArea(); // starts loop
  //window.cancelAnimationFrame(myGameArea.stopMain); // stop loop
})();

// draws an option with its text
function drawOption(ctx, text) {
  let posOptionX = 10;
  let posOptionY = 315;
  let tailleOptionX = 200;
  let tailleOptionY = 75;
  ctx.fillStyle = "white";
  rectArrondi(
    ctx,
    posOptionX,
    posOptionY,
    tailleOptionX,
    tailleOptionY,
    5,
    (fill = true)
  );
  ctx.fillStyle = "black";
  rectArrondi(ctx, posOptionX, posOptionY, tailleOptionX, tailleOptionY, 5);
  ctx.font = "30px serif";
  ctx.fillText(text, posOptionX + 10, posOptionY + 50);
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
