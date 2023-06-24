// ! Add the .js extension to the ts file so when tsc converts the file, the .js extension stays on the import
// Game
import { Game } from "./scripts/game.js";
const game = new Game();
game.Start();
