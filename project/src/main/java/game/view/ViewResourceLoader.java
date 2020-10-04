package game.view;

import game.App;

// Used to ensure we only load resources once
public class ViewResourceLoader {
    public final static String stylesheet = App.class.getResource("/styles/style.css").toString();
}
