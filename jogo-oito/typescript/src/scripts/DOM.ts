export class DOM {
  private oldDOM: string;

  constructor(oldDOM: Element) {
    this.oldDOM = oldDOM.cloneNode(true).textContent;
  }
  revalidate(currentDOMContent: string) {
    return this.oldDOM.trim() === currentDOMContent.trim();
  }
}
