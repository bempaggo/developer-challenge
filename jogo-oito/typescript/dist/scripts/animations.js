export class Animations {
    init() {
        this.configAnimationsOnDrag();
    }
    // Animations when drag element
    configAnimationsOnDrag() {
        document.addEventListener("dragstart", (event) => {
            if (event.target instanceof HTMLSpanElement)
                event.target.classList.add("dragging");
        });
        document.addEventListener("dragend", (event) => {
            if (event.target instanceof HTMLSpanElement)
                event.target.classList.remove("dragging");
        });
    }
}
