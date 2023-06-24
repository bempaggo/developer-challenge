export class Animations {
  init() {
    this.configAnimationsOnDrag();
  }

  // Animations when drag element
  private configAnimationsOnDrag() {
    document.addEventListener("dragstart", (event: MouseEvent) => {
      if (event.target instanceof HTMLSpanElement)
        event.target.classList.add("dragging");
    });

    document.addEventListener("dragend", (event: MouseEvent) => {
      if (event.target instanceof HTMLSpanElement)
        event.target.classList.remove("dragging");
    });
  }
}
