// ! Add the .js extension to the ts file so when tsc converts the file, the .js extension stays on the import
// Render
import { Render } from "./render.js";
// Animations
import { Animations } from "./animations.js";
// DOM
import { DOM } from "./DOM.js";
export class Game {
    constructor() {
        new Render().renderElementsNumber();
        this.container = document.querySelector(".container__spaceRight");
        // Load DOM
        this.DOM = new DOM(this.container);
        const emptySpace = document.getElementById("emptySpace");
        const numbers = document.querySelectorAll(".spaceRight__draggableItem");
        this.emptySpace = emptySpace;
        this.numbers = numbers;
    }
    Start() {
        new Animations().init();
        this.configEventsActions();
        console.log("%cGame is running...", "color: green;");
    }
    configEventsActions() {
        this.numbers.forEach((element) => element.addEventListener("dragstart", (event) => {
            event.dataTransfer.setData("text/plain", event.target.textContent);
        }));
        this.emptySpace.addEventListener("dragover", (event) => event.preventDefault());
        this.actionDrop();
    }
    actionDrop() {
        this.emptySpace.addEventListener("drop", (event) => {
            event.preventDefault();
            const contentDropedNumber = event.dataTransfer.getData("text/plain");
            if (event.target.id === "emptySpace") {
                this.numbers.forEach((number) => {
                    this.replaceNumberOnDrop(number, contentDropedNumber);
                });
            }
        });
    }
    replaceNumberOnDrop(number, contentDropedNumber) {
        if (number.textContent == contentDropedNumber) {
            const nextAfterElement = number.nextElementSibling;
            // Insert element(number) before empty space
            this.container.insertBefore(number, this.emptySpace);
            // Remove empty space
            this.container.removeChild(this.emptySpace);
            // Insert empty space in old number location
            this.container.insertBefore(this.emptySpace, nextAfterElement);
            // ? After replacing the number, did the player win the game ?
            if (this.DOM.revalidate(this.container.textContent)) {
                document.querySelector(".spaceLeft__title").textContent = "Você ganhou";
            }
        }
    }
}
