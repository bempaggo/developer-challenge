export class DOM {
    constructor(oldDOM) {
        this.oldDOM = oldDOM.cloneNode(true).textContent;
    }
    revalidate(currentDOMContent) {
        return this.oldDOM.trim() === currentDOMContent.trim();
    }
}
