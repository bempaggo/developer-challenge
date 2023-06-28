function JogoOito(){
     this.arrayButton = Array.from(document.querySelectorAll(".style-Button"))
     this.idbuttonStart = document.getElementById("idbuttonStart")
}
JogoOito.prototype.start = function(){
      this.checkclick()
}
JogoOito.prototype.checkclick = function() {
      document.addEventListener("click",(event)=>{            
            const buttonClick = event.target
            if(this.arrayButton.indexOf(buttonClick) !== -1) return this.swapList(buttonClick.textContent)
            if(this.idbuttonStart === buttonClick) return this.shuffleArray()     
      })
}
JogoOito.prototype.shuffleArray = function(){
      let newList = [0]
      let count = 0
      while(newList.length !== 9){
            let valueNewRandom = this.randomNumber(9)
            if(newList.indexOf(valueNewRandom) === -1){
                  this.arrayButton[count].textContent = valueNewRandom
                  newList.push(valueNewRandom)
                  count += 1
            }
      }
      this.arrayButton[count].textContent = ` `
}     

JogoOito.prototype.randomNumber = function(max){
      return Math.floor(Math.random() * max)   
}  

JogoOito.prototype.swapList = function(numberMatrix){
      let buttonclickd = this.arrayButton.find((element)=> {return element.textContent === numberMatrix})
      let buttonVoid = this.arrayButton.find((element)=> {return element.textContent === ` `})
      let indexVoid = this.arrayButton.findIndex((element) => element.textContent === ` `)
      let buttonInddex = this.arrayButton.findIndex((element) => element.textContent === numberMatrix)
     
      if(buttonInddex === (indexVoid-1) || buttonInddex === (indexVoid+1)){
            buttonVoid.textContent = numberMatrix
            buttonclickd.textContent = ` `
      }else if(buttonInddex === (indexVoid+3) || buttonInddex === (indexVoid-3)){
            buttonVoid.textContent = numberMatrix
            buttonclickd.textContent = ` `
      }
}
const jogoOito = new JogoOito()
jogoOito.start()
