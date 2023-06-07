package chat.gpt.domain.listeners;

import chat.gpt.domain.interfaces.EventListener;

public class ManagerListener {
    
    private final NotificationListener notificationListener = new NotificationListener();
    private final MouseListener mouseListener = new MouseListener();
    private final KeyboardListener keyboardListener = new KeyboardListener();
    
    @SuppressWarnings("unchecked")
    public <T extends EventListener> T getListener(Class<T> class1){
        if(class1.equals(NotificationListener.class)){
            return (T) notificationListener;
        }
        else if(class1.equals(MouseListener.class)){
            return (T) mouseListener;
        }
        else if(class1.equals(KeyboardListener.class)){
            return (T) keyboardListener;
        }
        return null;
    }

    public void setEnabledEvents(Boolean cond){
        notificationListener.setEnabled(cond);
        mouseListener.setEnabled(cond);
        keyboardListener.setEnabled(cond);
    }

}
