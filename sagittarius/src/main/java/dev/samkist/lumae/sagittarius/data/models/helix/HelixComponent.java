package dev.samkist.lumae.sagittarius.data.models.helix;


import dev.samkist.lumae.sagittarius.data.models.MilkyModel;

public class HelixComponent extends MilkyModel<HelixComponent> {
    private String legacyText;
    private HelixClickContext clickContext;
    private HelixHoverContext hoverContext;
    public static final String scope = "helixComponent";

    public HelixComponent() {

    }

    public HelixComponent(String uid,
                          String legacyText,
                          HelixClickContext clickContext,
                          HelixHoverContext hoverContext) {
        super(uid, HelixComponent.scope);
        this.legacyText = legacyText;
        this.clickContext = clickContext;
        this.hoverContext = hoverContext;
    }

    public String legacyText() {
        return legacyText;
    }

    public HelixComponent legacyText(String text) {
        this.legacyText = text;
        return this;
    }

    public HelixClickContext clickContext() {
        return clickContext;
    }

    public HelixComponent clickContext(HelixClickContext openUrl) {
        this.clickContext = openUrl;
        return this;
    }

    public HelixHoverContext hoverContext() {
        return hoverContext;
    }

    public HelixComponent hoverContext(HelixHoverContext showText) {
        this.hoverContext = showText;
        return this;
    }
}
