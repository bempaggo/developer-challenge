// ! Add the .js extension to the ts file so when tsc converts the file, the .js extension stays on the import

// Render
import { Render } from "./render.js";

// Animations
import { Animations } from "./animations.js";

// DOM
import { DOM } from "./DOM.js";

interface IGame {
  readonly emptySpace: HTMLElement;
  readonly numbers: NodeListOf<Element>;
  readonly DOM: DOM;
  readonly container: Element;
}

export class Game implements IGame {
  readonly emptySpace: HTMLElement;
  readonly numbers: NodeListOf<Element>;
  readonly DOM: DOM;
  readonly container: Element;

  constructor() {
    new Render().renderElementsNumber();

    this.container = document.querySelector(".container__spaceRight");

    // Load DOM
    this.DOM = new DOM(this.container);

    const emptySpace = document.getElementById(
      "emptySpace"
    ) as HTMLElement | null;

    const numbers: NodeListOf<Element> = document.querySelectorAll(
      ".spaceRight__draggableItem"
    );

    this.emptySpace = emptySpace as HTMLElement;
    this.numbers = numbers as NodeListOf<Element>;
  }
  Start() {
    new Animations().init();
    this.configEventsActions();

    console.log("%cGame is running...", "color: green;");
  }

  private configEventsActions() {
    this.numbers.forEach((element) =>
      element.addEventListener("dragstart", (event: any) => {
        event.dataTransfer.setData("text/plain", event.target.textContent);
      })
    );

    this.emptySpace.addEventListener("dragover", (event) =>
      event.preventDefault()
    );

    this.actionDrop();
  }

  private actionDrop() {
    this.emptySpace.addEventListener("drop", (event: any) => {
      event.preventDefault();

      const contentDropedNumber = event.dataTransfer.getData(
        "text/plain"
      ) as String;

      if (event.target.id === "emptySpace") {
        this.numbers.forEach((number) => {
          this.replaceNumberOnDrop(number, contentDropedNumber);
        });
      }
    });
  }
  private replaceNumberOnDrop(number: Element, contentDropedNumber: String) {
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
