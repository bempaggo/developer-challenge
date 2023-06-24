export class Render {
    renderElementsNumber() {
        const elements = [1, 2, 3, 4, 5, 6, 7, 8];
        const container = document.querySelector(".container__spaceRight");
        elements.forEach((element, index) => {
            container === null || container === void 0 ? void 0 : container.appendChild(this.makeElementNumber(index + 1, element));
        });
    }
    makeElementNumber(ID, text) {
        const element = document.createElement("span");
        element.setAttribute("draggable", "true");
        element.setAttribute("id", ID.toString());
        element.setAttribute("class", "spaceRight__draggableItem");
        element.innerText = text.toString();
        return element;
    }
}
