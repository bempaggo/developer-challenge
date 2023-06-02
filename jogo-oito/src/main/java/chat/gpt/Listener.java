package chat.gpt;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;


public class Listener  implements KeyListener {

    private HashMap<Integer, HashSet<ActionEventDelegate>> map = new HashMap<>();

    public Listener subscribe(Integer keyCodeEvent, HashSet<ActionEventDelegate> set){
        if(map.containsKey(keyCodeEvent)){
            map.get(keyCodeEvent).addAll(set);
        }
        else {
            map.put(keyCodeEvent, set);
        }
        return this;
    }
    public Listener subscribe(Integer keyCodeEvent, ActionEventDelegate item){
        subscribe(keyCodeEvent, new HashSet<ActionEventDelegate>(){{
            add(item);
        }});

        return this;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        // System.out.println(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        Integer keyCode = e.getKeyCode();
        map.get(keyCode).forEach(x -> x.doAction(keyCode));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        // System.out.println(e);
    }
    
}
