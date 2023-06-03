package chat.gpt.domain.actions;

import chat.gpt.domain.interfaces.ActionEventDelegate;

class NotifyAction implements ActionEventDelegate<String> {
    @Override
    public void doAction(String dataEvent) {
        throw new UnsupportedOperationException("Unimplemented method 'doAction'");
    }

}