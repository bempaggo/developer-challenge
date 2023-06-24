export class Render {
  renderElementsNumber() {
    const elements = [1, 2, 3, 4, 5, 6, 7, 8];
    const container = document.querySelector(".container__spaceRight");

    elements.forEach((element, index) => {
      container?.appendChild(this.makeElementNumber(index + 1, element));
    });
  }

  private makeElementNumber(
    ID: number,
    text: string | number
  ): HTMLSpanElement {
    const element = document.createElement("span");

    element.setAttribute("draggable", "true");
    element.setAttribute("id", ID.toString());
    element.setAttribute("class", "spaceRight__draggableItem");
    element.innerText = text.toString();

    return element;
  }
}
