package chat.gpt.model;

import java.util.List;

public interface GridInterface extends ListInfoInterface {

    List<Integer> getGameIsCompleteGridPattern();

    void reset();

}